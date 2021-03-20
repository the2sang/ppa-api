package io.spring.batch.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtsTaxMainInfoTbRowMapper implements RowMapper<EtsTaxMainInfoTbVO>  {


    @Override
    public EtsTaxMainInfoTbVO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new EtsTaxMainInfoTbVO(
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString(""),
                resultSet.getString("")

        );
    }
}
