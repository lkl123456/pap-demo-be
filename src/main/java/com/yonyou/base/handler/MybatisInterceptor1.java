package com.yonyou.base.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 1.Executor (update, query, flushStatements, commit, rollback, getTransaction,close, isClosed):拦截执行器的方法 
 * 2.ParameterHandler (getParameterObject,setParameters):拦截参数的处理
 * 3.ResultSetHandler (handleResultSets,handleOutputParameters):拦截结果集的处理 
 * 4.StatementHandler (prepare, parameterize,batch, update, query):拦截Sql语法构建的处理
 * @Description:
 * @author: lkl
 * @date: 2019年12月23日 下午10:21:08
 */
// @Intercepts({ @Signature(type = Executor.class, method = "query", args = {
// MappedStatement.class, Object.class,RowBounds.class, ResultHandler.class })
// })
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class MybatisInterceptor1 implements Interceptor {

	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
//		Object[] args = invocation.getArgs();
//		// 获取到当前的Statement
//		Statement stmt = (Statement) args[0];
//		// 通过Statement获得当前结果集
//		ResultSet resultSet = stmt.getResultSet();
//		List<Object> resultList = new ArrayList<Object>();
//		if(resultSet != null && resultSet.next()) {
//		// infos字段
//		String infos = resultSet.getString("infos");
//		// 判断是否为空
//		if(StringUtils.isNotBlank(infos)) {
//		// fastjson泛型反序列化
//		Map<String, Object> infMap = JSON.parseObject(infos, new TypeReference<Map>(){});
//		//account.setInfos(infMap);
//		//resultList.add(account);
//		}
//		// handleResultSets返回结果一定是一个List
//		// size为1时，Mybatis会取第一个元素作为接口的返回值。
//		return resultList;
//		}
//		return invocation.proceed();

		
//		List<String> resList = new ArrayList<String>();
//		DefaultResultSetHandler defaultResultSetHandler = (DefaultResultSetHandler) invocation.getTarget();
//		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//		// MappedStatement维护了一条<select|update|delete|insert>节点的封装
//		MappedStatement mappedStatement = defaultResultSetHandler.handleResultSets(stmt)
//		// 获取节点属性的集合
//		List<ResultMap> resultMaps = mappedStatement.getResultMaps();
//		int resultMapCount = resultMaps.size();
//		// 获取当前resutType的类型
//		Class<?> resultType = resultMaps.get(0).getType();
//		if (resultMapCount > 0 && resultType.getName().equals("java.lang.String")) {
		List<String> resList = new ArrayList<String>();
			//Object[] obj = invocation.getArgs();
			Statement statement = (Statement) invocation.getArgs()[0];
			// 获得结果集
			ResultSet resultSet = statement.getResultSet();
			if (resultSet != null) {
				// 获得对应列名
				ResultSetMetaData rsmd = resultSet.getMetaData();
				List<String> columnList = new ArrayList<String>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					columnList.add(rsmd.getColumnName(i));
				}
				while (resultSet.next()) {
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
					for (String colName : columnList) {
						
						map.put(colName, resultSet.getObject(colName));
					}
					JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";// 设置日期格式
					resList.add(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue,
							SerializerFeature.DisableCircularReferenceDetect,
							SerializerFeature.WriteDateUseDateFormat));
				}
				return resList;
			}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// 读取@Signature中的配置，判断是否需要生成代理类
		if (target instanceof ResultSetHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
