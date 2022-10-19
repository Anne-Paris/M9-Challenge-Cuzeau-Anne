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
    public void createCustomer() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Aline");
        customerTest.setLastName("Blablabsdfsdla");
        customerTest.setState("CA");

        customerTest = customerRepository.save(customerTest);

        Optional<Customer> c1 = customerRepository.findById(customerTest.getId());

        assertEquals(c1.get(), customerTest);

        assertTrue(c1.isPresent());
    }

    @Test
    public void deleteCustomer() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Mike");
        customerTest.setLastName("Blablabla");
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
        customerTest.setLastName("Blablabla");
        customerTest.setState("DC");

        customerTest = customerRepository.save(customerTest);

        customerTest.setFirstName("Mike");
        customerTest.setLastName("Blablabla");
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
        Customer customerTest3 = new Customer();
        customerTest3.setFirstName("Mike3");
        customerTest3.setLastName("RockStart3");
        customerTest3.setState("TX");
        Customer customerTest4 = new Customer();
        customerTest4.setFirstName("Lalaer");
        customerTest4.setLastName("Lala");
        customerTest4.setState("TX");

        customerTest2 = customerRepository.save(customerTest2);
        customerTest3 = customerRepository.save(customerTest3);
        customerTest4 = customerRepository.save(customerTest4);

        //All customer from DC
        List<Customer> customersList = customerRepository.findByState("DC");
        assertEquals( 1, customersList.size());
        assertTrue(customersList.contains(customerTest));

        //All customer from texas
        List<Customer> customersList2 = customerRepository.findByState("TX");
        assertEquals(2, customersList2.size());
        assertTrue(customersList2.contains(customerTest3));
        assertTrue(customersList2.contains(customerTest4));
    }

}
