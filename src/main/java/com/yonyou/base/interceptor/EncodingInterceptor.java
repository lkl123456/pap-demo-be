/*package com.yonyou.base.interceptor;

import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

public class EncodingInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object result = invocation.proceed();
		return null;
	}

	@Override
	public Object plugin(Object target) {
		return null;
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
	package com.ceabox.interceptor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }) })
public class InterceptorForQry implements Interceptor {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed(); //执行请求方法，并将所得结果保存到result中
        if (result instanceof ArrayList) {
            ArrayList resultList = (ArrayList) result;
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i) instanceof Map) {
                    Map resultMap = (Map) resultList.get(i);
                    resultMap.put("CERT_NO", "这个是加密结果"); //取出相应的字段进行加密
                }
            }
        }
        return result;
    }

    public Object plugin(Object target) {
        System.out.println("this is the proceed ===>>" + target);
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties arg0) {
        System.out.println("this is the properties ===>>" + arg0);
    }
}

}
*/