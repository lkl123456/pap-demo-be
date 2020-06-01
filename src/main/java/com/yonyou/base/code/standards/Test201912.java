package com.yonyou.base.code.standards;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class Test201912 {
	public static void main(String[] args) {
		//1.MyBatis 不要为了多个查询条件而写 1 = 1 ===> <where></where>
		
		//2.迭代entrySet()获取Map 的key 和value:for (String key : map.keySet()) ===> for (Map.Entry<String, String> entry : map.entrySet())
		Map<String, String> map = new HashMap<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String k = entry.getKey();
			String v = entry.getValue();
		}
		
		//3.使用Collection.isEmpty() 检测空,使用CollectionUtils.isEmpty(collect) 检测null和空
		List<Object> collect = new ArrayList<>();
		if (collect.isEmpty()){
		    System.out.println("collection is empty.");
		}
		if (CollectionUtils.isEmpty(collect)){//检测是否为null或empty 可以使用CollectionUtils.isEmpty()
		    System.out.println("collection is null.");

		}
		
		//4.初始化集合时尽量指定其大小
		int[] arr = new int[]{1,2,3,4};
		List<Integer> listInt = new ArrayList<>(arr.length);//指定集合list 的容量大小
		for (int i : arr){
			listInt.add(i);
		}
		
		//5.使用StringBuilder 拼接字符串
		String str1 = "Love";
		String str2 = "Courage";
		String strConcat = str1 + str2;  //Java 编译器会对该普通模式的字符串拼接进行优化
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++){
		   //在循环中，Java 编译器无法进行优化，所以要手动使用StringBuilder
		    sb.append(i);
		}
		//6.若需频繁调用Collection.contains(collect)方法，则使用Set
		List<Object> listObj = new ArrayList<>();
		listObj.add("1");
		listObj.add(1);
		listObj.add(1);
		Set<Object> set = new HashSet<>(listObj);//自动去重
		for (int i = 0; i <= Integer.MAX_VALUE; i++){
		    //时间复杂度为O(1)
		    if (set.contains(i)){
		        System.out.println("list contains "+ i);
		    }
		}
		
		//7.使用静态代码块实现赋值静态成员变量
		
		//8.工具类中屏蔽构造函数
		//定义私有构造函数来屏蔽这个隐式公有构造函数
		//private PasswordUtils(){}
		
		//9.字符串转化使用String.valueOf(value) 代替 " " + value
		
		//10.避免使用BigDecimal(double)
		BigDecimal bigDecimal = new BigDecimal(0.11D);//反例
		BigDecimal bigDecimal1 = BigDecimal.valueOf(0.11D);//正例
		
		//11.返回空数组和集合而非 null
//		public static Result[] getResults() {
//		    return new Result[0];
//		}
//		public static List<Result> getResultList() {
//		    return Collections.emptyList();
//		}
//		public static Map<String, Result> getResultMap() {
//		    return Collections.emptyMap();
//		}
		
		//12.优先使用常量或确定值调用equals方法 ,"Charming".equals(fileName);
		
		//13.枚举的属性字段必须是私有且不可变
		//枚举通常被当做常量使用，如果枚举中存在公共属性字段或设置字段方法，那么这些枚举常量的属性很容易被修改；理想情况下，枚举中的属性字段是私有的，并在私有构造函数中赋值，没有对应的Setter 方法，最好加上final 修饰符。
		
		
		//14.String.split(String regex)部分关键字需要转译
		String[] split2 = "a.ab.abc".split("\\.");
		System.out.println(Arrays.toString(split2));  //结果为["a", "ab", "abc"]
		String[] split3 = "a|ab|abc".split("\\|");
		System.out.println(Arrays.toString(split3));  //结果为["a", "ab", "abc"]

	}
}
