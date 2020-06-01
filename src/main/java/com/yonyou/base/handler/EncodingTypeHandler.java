package com.yonyou.base.handler;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 1.setNonNullParameter：这个方法是用来将javaType转换成jdbcTpe
   2.getNullableResult：这个方法用来将从结果集根据列名称获取到的数据的jdbcType转换成javaType
   3.getNullableResult：这个方法用来将从结果集根据列索引获取到的数据的jdbcType转换成javaType
   4.getNullableResult：这个方法用在存储过程中
* @Description:  
* @author: lkl 
* @date: 2019年5月7日 下午4:46:54
 */

public class EncodingTypeHandler extends BaseTypeHandler<String>{
	
	private Logger logger = LoggerFactory.getLogger(EncodingTypeHandler.class);
	
	private static final String OLD_CHARSET_ENCODING = "iso8859-1";  
	  
    private static final String NEW_CHARSET_ENCODING = "big5";  
  
    //private static final String DEFAULT_CHARSET_ENCODING = "UTF-8";  
    
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		 ps.setString(i, parameter);
	}
	
	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		/*try {
			System.out.println("進入方法1");
			String value="載具交換";
			String str = "¸ü¨ã¥æ´«";//new String(value.getBytes("big5"),"iso8859-1");
			System.out.println("iso8859-1："+new String(value.getBytes("big5"),"iso8859-1")
					+" big5："+new String(str.getBytes("iso8859-1"),"big5"));
			System.out.println("当前的columnName=="+columnName);
			if(!org.apache.commons.lang3.StringUtils.isEmpty(rs.getString(columnName))){
				return convertCharset(rs.getString(columnName));
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}		*/
		return rs.getString(columnName);
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		try {
			return convertCharset(rs.getString(columnIndex));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} 
		return null;
		
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		try {
			return convertCharset(cs.getString(columnIndex));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} 
		return null; 
	}
	
	/**
	 * 查询数据编码修改  
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
    private String convertCharset(String value) throws UnsupportedEncodingException {
    	return new String(value.getBytes(OLD_CHARSET_ENCODING), NEW_CHARSET_ENCODING);
    }



}
