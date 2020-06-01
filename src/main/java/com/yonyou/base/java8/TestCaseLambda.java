package com.yonyou.base.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.yonyou.base.java8.model.OutstandingClass;
import com.yonyou.base.java8.model.Student;
/**
 * java8将函数式接口封装到流中如何高效的帮助我们处理集合。
 * 惰性求值：只描述Stream，操作的结果也是Stream，这样的操作称为惰性求值。惰性求值可以像建造者模式一样链式使用，最后再使用及早求值得到最终结果。
及早求值：得到最终的结果而不是Stream，这样的操作称为及早求值。
* @Description:  
* @author: lkl 
* @date: 2019年10月18日 下午7:58:48
 */
public class TestCaseLambda {
	public static void main(String[] args) {
		test3();
    }
	/**
	 * 将流转换为list。还有toSet()，toMap()等。及早求值。
	 */
	private static void test1() {
		List<Student> studentList = Stream.of(new Student("路飞", 22, 175),
                new Student("红发", 40, 180),
                new Student("白胡子", 50, 185)).collect(Collectors.toList());
        System.out.println(studentList);
	}
	/**
	 * 顾名思义，起过滤筛选的作用。内部就是Predicate接口。惰性求值
	 */
	private static void test2() {
		 List<Student> students = new ArrayList<>(3);
	        students.add(new Student("路飞", 20, 175));
	        students.add(new Student("红发", 40, 180));
	        students.add(new Student("白胡子", 50, 185));

	        List<Student> list = students.stream()
	            .filter(stu -> stu.getHeight() < 180)
	            .collect(Collectors.toList());
	        Map<Integer, List<Student>> collect = students.stream()
		            .filter(stu -> stu.getHeight() < 180)
		            .collect(Collectors.groupingBy(stu->stu.getAge()));
	        Map<Integer, List<Student>> collect2 = students.stream()
		            .collect(Collectors.groupingBy(stu->stu.getAge()));
	        System.out.println(list);
	        System.out.println(collect);
	        System.out.println(collect2);
	}
	/**
	 * 转换功能，内部就是Function接口。惰性求值
	 */
	private static void test3() {
		List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        List<String> names = students.stream().map(student -> student.getName())
                .collect(Collectors.toList());
        List<String> names2 = students.stream().filter(student -> student.getAge()>23).map(student -> student.getName())
                .collect(Collectors.toList());
        System.out.println(names);
        System.out.println(names2);
	}
	/**
	 * 将多个Stream合并为一个Stream。惰性求值
	 */
	public static void test4() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        List<Student> studentList = Stream.of(students,
                Arrays.asList(new Student("艾斯", 25, 183),
                        new Student("雷利", 48, 176)))
                .flatMap(students1 -> students1.stream()).collect(Collectors.toList());
        System.out.println(studentList);
    }
	/**
	 * 我们经常会在集合中求最大或最小值，使用流就很方便。及早求值
	 */
	 public static void test5() {
	        List<Student> students = new ArrayList<>(3);
	        students.add(new Student("路飞", 22, 175));
	        students.add(new Student("红发", 40, 180));
	        students.add(new Student("白胡子", 50, 185));

	        Optional<Student> max = students.stream()
	            .max(Comparator.comparing(stu -> stu.getAge()));
	        Optional<Student> min = students.stream()
	            .min(Comparator.comparing(stu -> stu.getAge()));
	        //判断是否有值
	        if (max.isPresent()) {
	            System.out.println(max.get());
	        }
	        if (min.isPresent()) {
	            System.out.println(min.get());
	        }
	    }
	 /**
	  * 统计功能，一般都是结合filter使用，因为先筛选出我们需要的再统计即可。及早求值
	  */
	 public static void test6() {
	        List<Student> students = new ArrayList<>(3);
	        students.add(new Student("路飞", 22, 175));
	        students.add(new Student("红发", 40, 180));
	        students.add(new Student("白胡子", 50, 185));

	        long count = students.stream().filter(s1 -> s1.getAge() < 45).count();
	        System.out.println("年龄小于45岁的人数是：" + count);
	    }
	 /**
	  * reduce 操作可以实现从一组值中生成一个值。在上述例子中用到的 count 、 min 和 max 方法，
	  * 因为常用而被纳入标准库中。事实上，这些方法都是 reduce 操作。及早求值。
	  */
	 public static void test7() {
		 Integer reduce = Stream.of(1, 2, 3, 4).reduce(0, (acc, x) -> acc+ x);
	        System.out.println(reduce);
	    }
	 
	 /**
	  * 转换成值
	  * 收集器，一种通用的、从流生成复杂值的结构。只要将它传给 collect 方法，所有的流就都可以使用它了。
	  * 标准类库已经提供了一些有用的收集器，以下示例代码中的收集器都是从 java.util.stream.Collectors 类中静态导入的
	  */
	 public static void test8() {
	        List<Student> students1 = new ArrayList<>(3);
	        students1.add(new Student("路飞", 23, 175));
	        students1.add(new Student("红发", 40, 180));
	        students1.add(new Student("白胡子", 50, 185));

	        OutstandingClass ostClass1 = new OutstandingClass("一班", students1);
	        //复制students1，并移除一个学生
	        List<Student> students2 = new ArrayList<>(students1);
	        students2.remove(1);
	        OutstandingClass ostClass2 = new OutstandingClass("二班", students2);
	        //将ostClass1、ostClass2转换为Stream
	        Stream<OutstandingClass> classStream = Stream.of(ostClass1, ostClass2);
	        OutstandingClass outstandingClass = biggestGroup(classStream);
	        System.out.println("人数最多的班级是：" + outstandingClass.getName());

	        System.out.println("一班平均年龄是：" + averageNumberOfStudent(students1));
	    }

	    /**
	     * 获取人数最多的班级
	     */
	    private static OutstandingClass biggestGroup(Stream<OutstandingClass> outstandingClasses) {
//	        return outstandingClasses.collect(
//	                maxBy(comparing(ostClass -> ostClass.getStudents().size())))
//	                .orElseGet(OutstandingClass::new);
	    	return null;
	    }


		/**
	     * 计算平均年龄
	     */
	    private static double averageNumberOfStudent(List<Student> students) {
	        return students.stream().collect(Collectors.averagingInt(Student::getAge));
	    }
	    
	    
		/**
		 * 转换成块
		 * 常用的流操作是将其分解成两个集合，Collectors.partitioningBy帮我们实现了，接收一个Predicate函数式接口。
		 */
		public static void test9() {
			List<Student> students1 = new ArrayList<>(3);
	        students1.add(new Student("路飞", 23, 175));
	        students1.add(new Student("红发", 40, 180));
	        students1.add(new Student("白胡子", 50, 185));
	        Map<Boolean, List<Student>> listMap = students1.stream().collect(
	            Collectors.partitioningBy(student -> student.getName().contains("红发")));
	        for (Object o : listMap.keySet()) {
				System.out.println("key==="+listMap.get(o));
				for (Student s : listMap.get(o)) {
					System.out.println("key="+listMap.get(o)+"的第"+(listMap.get(o).indexOf(s)+1)+"个value="+s);
					
				}
			}
	        
	    }
		
		
		/**
		 * 数据分组
		 */
		public static void test10(){
			List<Student> students = new ArrayList<>(3);
			students.add(new Student("路飞1", 22, 175));
			students.add(new Student("红发1", 40, 180));
			students.add(new Student("白胡子1", 50, 185));
			students.add(new Student("路飞2", 22, 175));
			students.add(new Student("红发2", 40, 180));
			students.add(new Student("白胡子2", 50, 185));
			students.add(new Student("路飞3", 22, 175));
			students.add(new Student("红发3", 40, 180));
			students.add(new Student("白胡子3", 50, 185));
			students.add(new Student("路飞4", 22, 175));
			students.add(new Student("红发4", 40, 180));
			students.add(new Student("白胡子4", 50, 185));
			Map<Object,List<Student>> listMap = 
		             students.stream().collect(
		             Collectors.groupingBy(student -> student.getAge()));
			for (Object o : listMap.keySet()) {
				System.out.println("key==="+listMap.get(o));
				for (Student s : listMap.get(o)) {
					System.out.println("key="+listMap.get(o)+"的第"+(listMap.get(o).indexOf(s)+1)+"个value="+s);
					
				}
			}
		}
		/**
		 * 字符串拼接
		 * 通常只能创建一个StringBuilder，循环拼接。
		 * 使用Stream，使用Collectors.joining()简单容易
		 * joining接收三个参数，第一个是分界符，第二个是前缀符，第三个是结束符。也可以不传入参数Collectors.joining()，这样就是直接拼接
		 */
		 public static void test11() {
		        List<Student> students = new ArrayList<>(3);
		        students.add(new Student("路飞", 22, 175));
		        students.add(new Student("红发", 40, 180));
		        students.add(new Student("白胡子", 50, 185));

		         String names = students.stream()
		             .map(Student::getName).collect(Collectors.joining(",","[","]"));
		        System.out.println(names);
		    }
		 
		 public static void test12(){
			 Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
		        Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );
		        Arrays.asList( "a", "b", "d" ).forEach( e -> {
		            System.out.print( e );
		            System.out.print( e );
		        } );
		        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
		        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
		            int result = e1.compareTo( e2 );
		            return result;
		        } );
		 }
}
