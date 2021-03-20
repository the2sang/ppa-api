package io.spring.batch.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CustomerRowMapper implements RowMapper<CustomerInput> {
  @Override
  public CustomerInput mapRow(ResultSet resultSet, int i) throws SQLException {
    return new CustomerInput(resultSet.getLong("ID"),
      resultSet.getString("FIRSTNAME"),
      resultSet.getString("LASTNAME"));
  }
}