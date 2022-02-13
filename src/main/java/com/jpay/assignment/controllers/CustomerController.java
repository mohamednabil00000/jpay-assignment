package com.jpay.assignment.controllers;

import com.jpay.assignment.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public ResponseEntity<?> getCustomer(@RequestParam int page, @RequestParam int size){
    return ResponseEntity.ok(customerService.getAllCustomer(page, size));
  }
}
