package com.jpay.assignment.repos;

import com.jpay.assignment.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

  Page<Customer> findAll(Pageable pageable);
}
