package com.jpay.assignment.helpers;

import java.util.regex.Pattern;

public class PhoneNumberStateHelper {

  public static final String patterns = "\\(237\\)\\ ?[2368]\\d{7,8}$"
          + "|\\(251\\)\\ ?[1-59]\\d{8}$"
          + "|\\(212\\)\\ ?[5-9]\\d{8}$"
          + "|\\(258\\)\\ ?[28]\\d{7,8}$"
          + "|\\(256\\)\\ ?\\d{9}$";

  private static final Pattern pattern = Pattern.compile(patterns);
  public static boolean isValid(String phoneNumber){
    return pattern.matcher(phoneNumber).matches();
  }
}
