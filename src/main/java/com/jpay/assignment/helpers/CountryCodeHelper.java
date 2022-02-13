package com.jpay.assignment.helpers;

import java.util.HashMap;

public class CountryCodeHelper {

  private static final HashMap<String, String> countryCode = fillCountryCodeMap();

  public static String getCountry(String phoneNumber){
    return countryCode.getOrDefault(phoneNumber.substring(1, 4), "invalid");
  }

  private static HashMap<String, String> fillCountryCodeMap(){
    HashMap<String, String> countryCode = new HashMap<>();
    countryCode.put("237", "Cameroon");
    countryCode.put("251", "Ethiopia");
    countryCode.put("212", "Morocco");
    countryCode.put("258", "Mozambique");
    countryCode.put("256", "Uganda");
    return countryCode;
  }
}
