package com.jpay.assignment.controllers;

import com.jpay.assignment.services.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowCredentials = "false")
@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerServiceImpl customerService;

  @Autowired
  CustomerController(CustomerServiceImpl customerService){
    this.customerService = customerService;
  }

  @Operation(summary = "Get all customers using pagination")
  @GetMapping
  public ResponseEntity<?> getAllCustomers(
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "10", required = false) int size){
    return ResponseEntity.ok(customerService.getAllCustomer(page, size));
  }

  @Operation(summary = "Get all customers using pagination and filter by country and state")
  @GetMapping("/country/{country}")
  public ResponseEntity<?> getCustomersByCountry(
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "10", required = false) int size,
      @PathVariable String country,
      @RequestParam(required = false) String state){
    return ResponseEntity.ok(customerService.getAllCustomerByCountryAndState(page, size, country, state));
  }

  @Operation(summary = "Get all customers using pagination and filter by state only")
  @GetMapping("/state/{state}")
  public ResponseEntity<?> getCustomersByState(
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "10", required = false) int size,
      @PathVariable String state){
    return ResponseEntity.ok(customerService.getAllCustomerByState(page, size, state));
  }
}
