package com.service360.group50.service;

import com.service360.group50.entity.Users;
import com.service360.group50.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getCustomers( LocalDate startDate, LocalDate endDate) {
        List<Users> customersList = new ArrayList<> ( );
        if (startDate != null || endDate != null) {
            return userRepository.findAllByRegistrationdateBetween(startDate, endDate);
        }else {
            userRepository.findAll ( ).forEach ( customersList::add );
        }
        return customersList;
    }

    public Users updateCustomer ( Users customers ) {
        return userRepository.save(customers);
    }

    public String deleteCustomerById ( Long customerId ) {
        userRepository.deleteById(customerId);
        return "Customer deleted successfully";
    }
}