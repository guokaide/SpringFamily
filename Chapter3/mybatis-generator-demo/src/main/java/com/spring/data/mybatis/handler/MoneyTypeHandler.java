package com.spring.data.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 在 Money 与 Long 之间转换，处理CNY人民币
 */
public class MoneyTypeHandler extends BaseTypeHandler<Money> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money money, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, money.getAmountMinorLong()); // Money -> Long
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return parseMoney(resultSet.getLong(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return parseMoney(resultSet.getLong(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return parseMoney(callableStatement.getLong(columnIndex));
    }

    private Money parseMoney(Long value) {
        //return Money.of(CurrencyUnit.of("CNY"), value / 100.0);
        return Money.ofMinor(CurrencyUnit.of("CNY"), value);
    }
}
