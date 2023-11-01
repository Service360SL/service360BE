package com.service360.group50.service;


import com.service360.group50.auth.AuthenticationRequest;
import com.service360.group50.auth.AuthenticationResponse;
import com.service360.group50.auth.UserRegisterRequest;
import com.service360.group50.config.JwtService;
import com.service360.group50.dto.UsersDTO;
import com.service360.group50.entity.*;
import com.service360.group50.repo.*;
import com.service360.group50.validator.EmailValidatotor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final AdvertiserRepository advertiserRepository;
    private final AdvertiserFileRepository advertiserFileRepository;
    private final ServiceProviderFilesRepository serviceProviderFileRepository;
    private final ServiceProviderServicesRepository serviceProviderServicesRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ServiceRepository serviceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailValidatotor emailValidatotor;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;
    private final EmailSender emailSender;

    public AuthenticationResponse customerRegister ( UserRegisterRequest request ) {

        boolean isEmailValid = emailValidatotor.test ( request.getEmail ( ) );

        if ( !isEmailValid ) {
            throw new IllegalStateException ( "Email is not valid" );
        } else {
            boolean userExists = userRepository.findByEmailIgnoreCase ( request.getEmail ( ) ).isPresent ();

            if ( userExists ) {
                throw new IllegalStateException ( "Email already exists" );
            } else {

                var customer = Users.builder ( )
                        .firstname ( request.getFirstname () )
                        .lastname ( request.getLastname ( ) )
                        .phonenumber ( request.getPhonenumber ( ) )
                        .address ( request.getAddress ( ) )
                        .email ( request.getEmail ( ) )
                        .nic ( request.getNic ( ) )
                        .password ( passwordEncoder.encode ( request.getPassword ( ) ) )
                        .role ( Role.CUSTOMER )
                        .build ( );

                userRepository.save ( customer );

                var jwtToken = jwtService.generateToken ( customer );

                ConfirmationToken confirmationToken = new ConfirmationToken (
                        jwtToken,
                        LocalDateTime.now ( ),
                        LocalDateTime.now ( ).plusMinutes ( 15 ),
                        customer
                );

                confirmationTokenService.saveConfirmationToken ( confirmationToken );

                String link = "http://localhost:8080/auth/confirm?token=" + jwtToken;
                emailSender.send ( request.getEmail ( ), buildEmail ( request.getFirstname ( ), link ) );

                return AuthenticationResponse.builder ( )
                        .token ( jwtToken )
                        .build ( );

            }
        }
    }

    public AuthenticationResponse advertiserRegister(UserRegisterRequest request, MultipartFile[] files) throws IOException {

        boolean isEmailValid = emailValidatotor.test ( request.getEmail ( ) );

        if ( !isEmailValid ) {
            throw new IllegalStateException ( "Email is not valid" );
        } else {
            boolean userExists = userRepository.findByEmailIgnoreCase ( request.getEmail ( ) ).isPresent ();

            if ( userExists ) {
                throw new IllegalStateException ( "Email already exists" );
            } else {

                var advertiserUser = Users.builder ( )
                        .firstname ( request.getFirstname ( ) )
                        .lastname ( request.getLastname ( ) )
                        .phonenumber ( request.getPhonenumber ( ) )
                        .email ( request.getEmail ( ) )
                        .address ( request.getAddress ( ) )
                        .nic ( request.getNic ( ) )
                        .password ( passwordEncoder.encode ( request.getPassword ( ) ) )
                        .role ( Role.ADVERTISER )
                        .build ( );

                userRepository.save(advertiserUser);

                advertiserUser.setLocked ( true );

                userRepository.save(advertiserUser);

                // Create a new Advertiser instance
                var advertiser = Advertiser.builder()
                        .shopname(request.getShopname())
                        .shopaddress(request.getShopaddress())
                        .users(advertiserUser) // Associate the Advertiser with the Users
                        .build();

                advertiserRepository.save(advertiser);

                for (MultipartFile file : files) {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    AdvertiserFiles advertiserFile = new AdvertiserFiles (fileName, file.getContentType(), file.getBytes(), advertiserUser);

                    advertiserFileRepository.save(advertiserFile);
                }

                var jwtToken = jwtService.generateToken (advertiserUser);

                ConfirmationToken confirmationToken = new ConfirmationToken (
                        jwtToken,
                        LocalDateTime.now ( ),
                        LocalDateTime.now ( ).plusMinutes ( 15 ),
                        advertiserUser
                );

                confirmationTokenService.saveConfirmationToken ( confirmationToken );

                String link = "http://localhost:8080/auth/confirm?token=" + jwtToken;
                emailSender.send ( request.getEmail ( ), buildEmailAdvertiserAndServiceProvider ( request.getFirstname ( ), link ) );

                return AuthenticationResponse.builder ( )
                        .token ( jwtToken )
                        .build ( );

            }
        }
    }

    public AuthenticationResponse serviceProviderRegister(UserRegisterRequest request, MultipartFile[] files) throws IOException {

        boolean isEmailValid = emailValidatotor.test ( request.getEmail ( ) );

        if ( !isEmailValid ) {
            throw new IllegalStateException ( "Email is not valid" );
        } else {
            boolean userExists = userRepository.findByEmailIgnoreCase ( request.getEmail ( ) ).isPresent ();

            if ( userExists ) {
                throw new IllegalStateException ( "Email already exists" );
            } else {

                String[] selectedCategories = request.getCategories();
                String[] selectedServices = request.getServices();
                Set<String> uniqueCategories = new HashSet<> ();

                var serviceProviderUser = Users.builder ( )
                        .firstname ( request.getFirstname ( ) )
                        .lastname ( request.getLastname ( ) )
                        .phonenumber ( request.getPhonenumber ( ) )
                        .email ( request.getEmail ( ) )
                        .address ( request.getAddress ( ) )
                        .nic ( request.getNic ( ) )
                        .district ( request.getDistrict ( ) )
                        .city ( request.getCity ( ) )
                        .password ( passwordEncoder.encode ( request.getPassword ( ) ) )
                        .role ( Role.SERVICEPROVIDER )
                        .build ( );

                userRepository.save(serviceProviderUser);

                serviceProviderUser.setLocked ( true );

                userRepository.save(serviceProviderUser);

                // Create a Set to keep track of services associated with each category
                Map<String, Set<String>> categoryServiceMap = new HashMap<> ();

                for (int i = 0; i < selectedCategories.length; i++) {
                    Optional<ServiceCategory> serviceCategory = serviceCategoryRepository.findByServiceCategoryName(selectedCategories[i]);

                    if (serviceCategory.isPresent()) {
                        ServiceCategory serviceCategoryEntity = serviceCategory.get();

                        for (int j = 0; j < selectedServices.length; j++) {
                            Services service = serviceRepository.findByServiceNameAndServiceCategory(selectedServices[j], serviceCategoryEntity);

                            if (service != null) {
                                // Get the set of associated services for this category
                                Set<String> associatedServices = categoryServiceMap.computeIfAbsent(serviceCategoryEntity.getServiceCategoryName(), k -> new HashSet<>());

                                // Check if the service is already associated with the category
                                if (!associatedServices.contains(service.getServiceName())) {
                                    // Associate the service with the service category
                                    service.setServiceCategory(serviceCategoryEntity);

                                    ServiceProviderServices serviceProviderService = ServiceProviderServices.builder()
                                            .users(serviceProviderUser)
                                            .services(service)
                                            .serviceCategory(serviceCategoryEntity)
                                            .build();

                                    serviceProviderServicesRepository.save(serviceProviderService);

                                    // Add the service to the set of associated services for this category
                                    associatedServices.add(service.getServiceName());
                                }
                            } else {
                                System.out.println("Service not found in the specified category");
                            }
                        }
                    } else {
                        System.out.println("Service Category not found");
                    }
                }


                for (MultipartFile file : files) {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    ServiceProviderFiles serviceProviderFiles = new ServiceProviderFiles (fileName, file.getContentType(), file.getBytes(), serviceProviderUser);

                    serviceProviderFileRepository.save(serviceProviderFiles);
                }

                var jwtToken = jwtService.generateToken ( serviceProviderUser);

                ConfirmationToken confirmationToken = new ConfirmationToken (
                        jwtToken,
                        LocalDateTime.now ( ),
                        LocalDateTime.now ( ).plusMinutes ( 15 ),
                        serviceProviderUser
                );

                confirmationTokenService.saveConfirmationToken ( confirmationToken );

                String link = "http://localhost:8080/auth/confirm?token=" + jwtToken;
                emailSender.send ( request.getEmail ( ), buildEmail ( request.getFirstname ( ), link ) );

                return AuthenticationResponse.builder ( )
                        .token ( jwtToken )
                        .build ( );

            }
        }
    }

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            System.out.println("Authentication successful");

        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(AuthenticationResponse.builder()
                            .message("Authentication failed: Invalid credentials")
                            .build());
        }

        // Authentication was successful, generate and return a token
        var user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Authentication successful")
                .build());
    }

    public UsersDTO getUserDetails ( String email ) {
        var a = userRepository.findByEmailIgnoreCase (email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UsersDTO usersDTO = new UsersDTO ();

        usersDTO.setUserid ( a.getUserid () );
        usersDTO.setFirstname ( a.getFirstname () );
        usersDTO.setLastname ( a.getLastname () );
        usersDTO.setEmail ( a.getEmail () );
        usersDTO.setNic ( a.getNic () );
        usersDTO.setPhonenumber ( a.getPhonenumber () );
        usersDTO.setAddress ( a.getAddress () );
        usersDTO.setRegistrationdate ( a.getRegistrationdate () );
        usersDTO.setPassword ( a.getPassword () );
        usersDTO.setRole ( String.valueOf ( a.getRole () ) );
        usersDTO.setStatus ( a.getStatus () );
        usersDTO.setProfilePic (  a.getProfilePic () );

        return usersDTO;

    }

    @Transactional
    public RedirectView confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        userService.enableUser( confirmationToken.getUsers().getEmail());

        confirmationTokenService.setConfirmedAt(token);

        return new RedirectView ("http://localhost:3000/activateUser");
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
    private String buildEmailAdvertiserAndServiceProvider(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p><p>After activating your account, it will be verified within 2 days</p><p> Thanks for your Patience</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}