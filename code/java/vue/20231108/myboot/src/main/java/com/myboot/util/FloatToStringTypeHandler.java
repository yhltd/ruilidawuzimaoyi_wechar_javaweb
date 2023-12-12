package com.myboot.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;  
import org.apache.ibatis.type.TypeHandler;  
import java.sql.CallableStatement;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
  
public class FloatToStringTypeHandler extends BaseTypeHandler<String> {  
  
    @Override  
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {  
        if (parameter != null) {  
            float value = Float.parseFloat(parameter);  
            ps.setFloat(i, value);  
        } else {  
            ps.setNull(i, jdbcType.TYPE_CODE);  
        }  
    }  
  
    @Override  
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {  
        float value = rs.getFloat(columnName);  
        return rs.wasNull() ? null : Float.toString(value);  
    }  
  
    @Override  
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {  
        float value = rs.getFloat(columnIndex);  
        return rs.wasNull() ? null : Float.toString(value);  
    }  
  
    @Override  
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {  
        float value = cs.getFloat(columnIndex);  
        return cs.wasNull() ? null : Float.toString(value);  
    }  
}