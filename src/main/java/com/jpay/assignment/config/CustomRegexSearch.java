package com.jpay.assignment.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.sqlite.Function;

/**
 * there is no direct support for regex in sqlite3, we need to implement
 * it manually.
 */
public class CustomRegexSearch extends SimpleDriverDataSource {

  @Override
  public Connection getConnection() throws SQLException {
    Connection connection = super.getConnection();
    Function.create(connection, "REGEXP", createRegexpFunction());
    return connection;
  }

  private Function createRegexpFunction() {
    return new Function() {
      @Override
      protected void xFunc() throws SQLException {
        String value = value_text(0);
        String expression = value_text(1);
        if (value == null)
          value = "";
        boolean matches = Pattern.matches(expression, value);
        result(matches ? 1 : 0);
      }
    };
  }
}