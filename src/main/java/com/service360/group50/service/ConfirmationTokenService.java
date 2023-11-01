package com.service360.group50.service;

import com.service360.group50.entity.ConfirmationToken;
import com.service360.group50.repo.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

        private final ConfirmationTokenRepository confirmationTokenRepository;

        public void saveConfirmationToken( ConfirmationToken token) {
            confirmationTokenRepository.save(token);
        }

        public Optional<ConfirmationToken> getToken( String token) {
            return confirmationTokenRepository.findByToken(token);
        }

        public int setConfirmedAt(String token) {
            return confirmationTokenRepository.updateConfirmedAt(
                    token, LocalDateTime.now ( ));
        }
}
