package com.example.utils;

import java.time.format.DateTimeFormatter;

public class SomeDefaults {

  static {
    timestampFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss:SSS");
  }

  public static DateTimeFormatter timestampFormatter;


}
