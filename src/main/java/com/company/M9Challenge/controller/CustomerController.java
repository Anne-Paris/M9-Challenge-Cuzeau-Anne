package com.company.M9Challenge.controller;

import com.company.M9Challenge.model.Customer;
import com.company.M9Challenge.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepo repo;

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        Optional<Customer> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

//    @GetMapping("/customer/{state}")
//    public List<Customer> getCustomerByState(@PathVariable String state) {
//        List<Customer> returnVal = repo.findByState(state);
//
//        for (Customer c : returnVal) {
//            if (c.getState() != state){
//                returnVal.remove(c);
//            }
//        }
//
//        if (returnVal.size() == 0) {
//            return null;
//        }
//
//        else {
//            return returnVal;
//        }
//
//    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @PutMapping("/customer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer) {
        repo.save(customer);
    }

    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
