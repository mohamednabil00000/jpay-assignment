package com.jpay.assignment.dtos;


import com.jpay.assignment.helpers.CountryCodeHelper;
import com.jpay.assignment.helpers.PhoneNumberStateHelper;

public class CustomerDTO {

  private String name;
  private String phone;
  private String country;
  private String state;

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
    this.country = CountryCodeHelper.getCountry(phone);
    this.state = PhoneNumberStateHelper.isValid(phone) ? "valid" : "not valid";
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

}
