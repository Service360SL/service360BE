//package com.service360.group50.controller;
//
//import com.service360.group50.entity.Customers;
//import com.service360.group50.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//public class CustomersController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    @GetMapping("auth/customers")
//    public List<Customers> getAllCustomers(
//            @RequestParam(value = "StartDate", required = false) LocalDate startDate,
//            @RequestParam(value = "EndDate", required = false) LocalDate endDate) {
//        return customerService.getCustomers(startDate, endDate);
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<String> handleMethodArgumentTypeMismatch( MethodArgumentTypeMismatchException ex) {
//        String errorMessage = "Invalid date format. Please provide a valid date in the format YYYY-MM-DD.";
//        return ResponseEntity.badRequest().body(errorMessage);
//    }
//
//    @PostMapping("auth/customers")
//    public Customers createCustomer(@RequestBody Customers customers) {
//        return customerService.createCustomer(customers);
//    }
//
//    @RequestMapping("/customers/{id}")
//    public Customers getCustomerById(@PathVariable("id") Long customerId) {
//        return customerService.getCustomerById(customerId);
//    }
//
//    @PutMapping("/customers")
//    public Customers updateCustomer(@RequestBody Customers customers) {
//        return customerService.updateCustomer(customers);
//    }
//
//    @DeleteMapping("customers/{id}")
//    public String deleteCustomerById(@PathVariable("id") Long customerId) {
//        return customerService.deleteCustomerById(customerId);
//    }
//}
