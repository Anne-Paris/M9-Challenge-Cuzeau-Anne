package com.company.M9Challenge.repository;

import com.company.M9Challenge.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepo customerRepository;

    @Before
    public void setUp() throws Exception {
        customerRepository.deleteAll();
    }

    @Test
    public void addGetDeleteCustomer() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Mike");
        customerTest.setLastName("RockStart");
        customerTest.setState("DC");

        customerTest = customerRepository.save(customerTest);

        Optional<Customer> c1 = customerRepository.findById(customerTest.getId());

        assertEquals(c1.get(), customerTest);

        customerRepository.deleteById(customerTest.getId());

        c1 = customerRepository.findById(customerTest.getId());

        assertFalse(c1.isPresent());
    }

    @Test
    public void updateCustomer() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Mike");
        customerTest.setLastName("RockStart");
        customerTest.setState("DC");

        customerTest = customerRepository.save(customerTest);

        customerTest.setFirstName("Mike2");
        customerTest.setLastName("RockStart2");
        customerTest.setState("PA");

        customerRepository.save(customerTest);

        Optional<Customer> c1 = customerRepository.findById(customerTest.getId());
        assertEquals(c1.get(), customerTest);
    }


    @Test
    public void getCustomerByState() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Mike");
        customerTest.setLastName("RockStart");
        customerTest.setState("DC");

        customerTest = customerRepository.save(customerTest);

        Customer customerTest2 = new Customer();
        customerTest2.setFirstName("Mike2");
        customerTest2.setLastName("RockStart2");
        customerTest2.setState("PA");

        customerTest2 = customerRepository.save(customerTest);

        List<Customer> customersList = customerRepository.findByState("DC");
        assertEquals(customersList.size(), 1);
        assertTrue(customersList.contains(customerTest));
    }

}
