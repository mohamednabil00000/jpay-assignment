package com.jpay.assignment.repos;

import com.jpay.assignment.models.Customer;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

  Page<Customer> findAll(Pageable pageable);
  Page<Customer> findByPhoneStartingWith(String prefix, Pageable pageable);

  @Query(nativeQuery=true, value="SELECT * FROM customer WHERE REGEXP(phone, ?1) limit ?3 offset ?2")
  List<Customer> findValidPhones(String regex, int offset, int limit);

  @Query(nativeQuery=true, value="SELECT * FROM customer WHERE NOT REGEXP(phone, ?1) limit ?3 offset ?2")
  List<Customer> findInvalidPhones(String regex, int offset, int limit);

  @Query(nativeQuery=true, value="SELECT * FROM customer WHERE phone LIKE ?1% AND REGEXP(phone, ?2) limit ?4 offset ?3")
  List<Customer> findValidPhonesAndCountry(String countryCode, String regex, int offset, int limit);

  @Query(nativeQuery=true, value="SELECT * FROM customer WHERE phone LIKE ?1% AND NOT REGEXP(phone, ?2) limit ?4 offset ?3")
  List<Customer> findInvalidPhonesAndCountry(String countryCode, String regex, int offset, int limit);
}
