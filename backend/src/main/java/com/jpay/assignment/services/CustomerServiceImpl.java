package com.jpay.assignment.services;

import com.jpay.assignment.helpers.CountryCodeHelper;
import com.jpay.assignment.helpers.PhoneNumberStateHelper;
import com.jpay.assignment.models.Customer;
import com.jpay.assignment.dtos.CustomerDTO;
import com.jpay.assignment.repos.CustomerDAO;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerDAO customerDAO;
  private ModelMapper modelMapper;

  @Autowired
  public CustomerServiceImpl(CustomerDAO customerDAO, ModelMapper modelMapper){
    this.customerDAO = customerDAO;
    this.modelMapper = modelMapper;
  }

  /**
   * Get all customers using pagination.
   *
   * @param page number of page
   * @param size limit
   * @return list of customer dto
   */
  @Override
  public List<CustomerDTO> getAllCustomer(int page, int size){
    Page<Customer> customersPage = customerDAO.findAll(PageRequest.of(page, size));
    // map customer model into customer dto
    return customersPage.getContent().stream()
        .map(customer -> modelMapper.map(customer, CustomerDTO.class))
        .collect(Collectors.toList());
  }

  /**
   * Get all customers by country and optional state filter.
   *
   * @param page page number
   * @param size limit
   * @param country country name
   * @param state state[valid-not_valid]
   * @return list of customers dto
   */
  @Override
  public List<CustomerDTO> getAllCustomerByCountryAndState(int page, int size, String country, String state){
    String countryCode = CountryCodeHelper.getCountryCode(country);
    List<Customer> customers;
    if(state == null)
      customers = customerDAO.findByPhoneStartingWith(countryCode, PageRequest.of(page, size)).getContent();
    else if(state.equals("valid"))
      customers = customerDAO.findValidPhonesAndCountry(countryCode, PhoneNumberStateHelper.patterns, page*size, size);
    else
      customers = customerDAO.findInvalidPhonesAndCountry(countryCode, PhoneNumberStateHelper.patterns, page*size, size);
    // map customer model into customer dto
    return customers.stream()
        .map(customer -> modelMapper.map(customer, CustomerDTO.class))
        .collect(Collectors.toList());
  }

  /**
   * Get all customers by state filter.
   *
   * @param page page number
   * @param size limit
   * @param state state [valid - not_valid]
   * @return
   */
  @Override
  public List<CustomerDTO> getAllCustomerByState(int page, int size, String state){
    List<Customer> customersPage;
    int offset = page * size;
    if(state.equals("valid"))
      customersPage = customerDAO.findValidPhones(PhoneNumberStateHelper.patterns, offset, size);
    else
      customersPage = customerDAO.findInvalidPhones(PhoneNumberStateHelper.patterns, offset, size);
    // map customer model into customer dto
    return customersPage.stream()
        .map(customer -> modelMapper.map(customer, CustomerDTO.class))
        .collect(Collectors.toList());
  }
}
