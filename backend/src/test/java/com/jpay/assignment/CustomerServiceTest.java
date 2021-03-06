package com.jpay.assignment;

import com.jpay.assignment.dtos.CustomerDTO;
import com.jpay.assignment.models.Customer;
import com.jpay.assignment.repos.CustomerDAO;
import com.jpay.assignment.services.CustomerService;
import config.DatabaseConfig;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = { DatabaseConfig.class },
    loader = AnnotationConfigContextLoader.class)
public class CustomerServiceTest {

  @Autowired
  private CustomerService customerService;
  @Autowired
  private CustomerDAO customerDAO;

  @Before
  public void setup() {
    // Initializing some customers data to test all functions
    Customer customer1 = new Customer("Walid-Morocco-invalid", "(212) 6007989253");
    Customer customer2 = new Customer("Yosaf-Morocco-valid", "(212) 698054317");
    Customer customer3 = new Customer("Younes-Morocco-invalid", "(212) 6546545369");
    Customer customer4 = new Customer("Houda-Morocco-invalid", "(212) 6617344445");
    Customer customer5 = new Customer("Edunildo-Mozambique-valid", "(258) 847651504");
    Customer customer6 = new Customer("Walla-Mozambique-valid", "(258) 846565883");
    Customer customer7 = new Customer("Ogwal-Uganda-invalid", "(256) 7771031454");
    Customer customer8 = new Customer("LOUIS-Cameroon-valid", "(237) 673122155");
    Customer customer9 = new Customer("Frehiwot-Ethiopia-valid", "(251) 988200000");
    List<Customer> customers = Arrays.asList(customer1, customer2, customer3,
        customer4, customer5, customer6, customer7, customer8, customer9);
    customerDAO.saveAll(customers);
  }

  @After
  public void destroy() {
    customerDAO.deleteAll();
  }

  @Test
  public void testGetAllCustomers() {

    List<CustomerDTO> customerPage1 = customerService.getAllCustomer(0, 5);
    Assert.assertEquals(5, customerPage1.size());

    List<CustomerDTO> customerPage2 = customerService.getAllCustomer(1, 5);
    Assert.assertEquals(4, customerPage2.size());
  }

  @Test
  public void testGetAllCustomersByCountry() {

    List<CustomerDTO> customerPage1 = customerService.getAllCustomerByCountryAndState(0, 3, "Morocco", null);
    Assert.assertEquals(3, customerPage1.size());

    List<CustomerDTO> customerPage2 = customerService.getAllCustomerByCountryAndState(1, 3, "Morocco", null);
    Assert.assertEquals(1, customerPage2.size());
  }

  @Test
  public void testGetAllCustomersByValidState() {

    List<CustomerDTO> customerPage1 = customerService.getAllCustomerByState(0, 3, "valid");
    Assert.assertEquals(3, customerPage1.size());

    List<CustomerDTO> customerPage2 = customerService.getAllCustomerByState(1, 3, "valid");
    Assert.assertEquals(2, customerPage2.size());
  }

  @Test
  public void testGetAllCustomersByNotValidState() {

    List<CustomerDTO> customerPage1 = customerService.getAllCustomerByState(0, 3, "not_valid");
    Assert.assertEquals(3, customerPage1.size());

    List<CustomerDTO> customerPage2 = customerService.getAllCustomerByState(1, 3, "not_valid");
    Assert.assertEquals(1, customerPage2.size());
  }

  @Test
  public void testGetAllCustomersByCountryAndInvalidState() {

    List<CustomerDTO> customerPage1 = customerService.getAllCustomerByCountryAndState(0, 3, "Morocco", "not_valid");
    Assert.assertEquals(3, customerPage1.size());

    List<CustomerDTO> customerPage2 = customerService.getAllCustomerByCountryAndState(1, 3, "Morocco", "not_valid");
    Assert.assertEquals(0, customerPage2.size());
  }

  @Test
  public void testGetAllCustomersByCountryAndValidState() {

    List<CustomerDTO> customerPage1 = customerService.getAllCustomerByCountryAndState(0, 3, "Mozambique", "valid");
    Assert.assertEquals(2, customerPage1.size());
  }
}
