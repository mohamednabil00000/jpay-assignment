package com.jpay.assignment.services;

import com.jpay.assignment.dtos.CustomerDTO;
import java.util.List;
import org.springframework.stereotype.Service;

public interface CustomerService {
  List<CustomerDTO> getAllCustomer(int page, int size);
  List<CustomerDTO> getAllCustomerByCountryAndState(int page, int size, String country, String state);
  List<CustomerDTO> getAllCustomerByState(int page, int size, String state);
}
