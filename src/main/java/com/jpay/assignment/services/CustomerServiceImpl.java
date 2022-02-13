package com.jpay.assignment.services;

import com.jpay.assignment.helpers.CountryCodeHelper;
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
public class CustomerServiceImpl {

  private final CustomerDAO customerDAO;
  private ModelMapper modelMapper;

  @Autowired
  public CustomerServiceImpl(CustomerDAO customerDAO, ModelMapper modelMapper){
    this.customerDAO = customerDAO;
    this.modelMapper = modelMapper;
  }

  public List<CustomerDTO> getAllCustomer(int page, int size){
    Page<Customer> customersPage = customerDAO.findAll(PageRequest.of(page, size));
    return customersPage.getContent().stream()
        .map(customer -> modelMapper.map(customer, CustomerDTO.class))
        .collect(Collectors.toList());
  }

  public List<CustomerDTO> getAllCustomerByCountry(int page, int size, String country){
    String countryCode = CountryCodeHelper.getCountryCode(country);

    Page<Customer> customersPage = customerDAO.findByPhoneStartingWith(countryCode, PageRequest.of(page, size));
    return customersPage.getContent().stream()
        .map(customer -> modelMapper.map(customer, CustomerDTO.class))
        .collect(Collectors.toList());
  }
}
