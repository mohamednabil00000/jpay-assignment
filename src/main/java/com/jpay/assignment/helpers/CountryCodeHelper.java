package com.jpay.assignment.helpers;

import java.util.HashMap;

public class CountryCodeHelper {

  private static final HashMap<String, String> countryName = fillCountryNameMap();
  private static final HashMap<String, String> countryCode = fillCountryCodeMap();

  public static String getCountry(String phoneNumber){
    return countryName.getOrDefault(phoneNumber.substring(1, 4), "invalid");
  }

  public static String getCountryCode(String country){
    return countryCode.getOrDefault(country.toLowerCase(), "invalid");
  }

  private static HashMap<String, String> fillCountryNameMap(){
    HashMap<String, String> countryName = new HashMap<>();
    countryName.put("237", "Cameroon");
    countryName.put("251", "Ethiopia");
    countryName.put("212", "Morocco");
    countryName.put("258", "Mozambique");
    countryName.put("256", "Uganda");
    return countryName;
  }

  private static HashMap<String, String> fillCountryCodeMap(){
    HashMap<String, String> countryCode = new HashMap<>();
    countryCode.put("cameroon", "(237)");
    countryCode.put("ethiopia", "(251)");
    countryCode.put("morocco", "(212)");
    countryCode.put("mozambique", "(258)");
    countryCode.put("uganda", "(256)");
    return countryCode;
  }
}
