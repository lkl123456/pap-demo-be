package com.yonyou.base.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArrowFunc {
	public static void main(String[] args) {
		// 1. 不需要参数,返回值为 5  
		//() -> 5  
		  
		// 2. 接收一个参数(数字类型),返回其2倍的值  
		//x -> 2 * x  
		  
		// 3. 接受2个参数(数字),并返回他们的差值  
		//(x, y) -> x – y  
		  
		// 4. 接收2个int型整数,返回他们的和  
		//(int x, int y) -> x + y  
		  
		// 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)  
		//(String s) -> System.out.print(s)

		String[] atp = {"Rafael Nadal", "Novak Djokovic",  
		       "Stanislas Wawrinka",  
		       "David Ferrer","Roger Federer",  
		       "Andy Murray","Tomas Berdych",  
		       "Juan Martin Del Potro"};  
		List<String> players =  Arrays.asList(atp);  		  
		// 以前的循环方式  
		for (String player : players) {  
		     System.out.print(player + "; ");  
		}  		  
		// 使用 lambda 表达式以及函数操作(functional operation)  
		players.forEach((player) -> System.out.print(player + "; "));  
		// 在 Java 8 中使用双冒号操作符(double colon operator)  
		players.forEach(System.out::println);
		
		
		String[] players2 = {"Rafael Nadal", "Novak Djokovic",   
		    "Stanislas Wawrinka", "David Ferrer",  
		    "Roger Federer", "Andy Murray",  
		    "Tomas Berdych", "Juan Martin Del Potro",  
		    "Richard Gasquet", "John Isner"};  
		   
		// 1.1 使用匿名内部类根据 name 排序 players  
		Arrays.sort(players2, new Comparator<String>() {  
		    @Override  
		    public int compare(String s1, String s2) {  
		        return (s1.compareTo(s2));  
		    }  
		});
		// 1.2 使用 lambda expression 排序 players  
		Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));  
		Arrays.sort(players2, sortByName);  
		  
		// 1.3 也可以采用如下形式:  
		Arrays.sort(players2, (String s1, String s2) -> (s1.compareTo(s2)));
	}
	
}
