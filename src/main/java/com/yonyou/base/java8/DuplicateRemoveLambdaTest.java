package com.yonyou.base.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yonyou.base.java8.model.Student;

public class DuplicateRemoveLambdaTest {
    public static void main(String[] args) {
        
    }
        public void listDistinctByStreamDistinct() {
            // 1. 对于 String 列表去重
            List<String> stringList = new ArrayList<String>() {{
                add("A");
                add("A");
                add("B");
                add("B");
                add("C");
            }};
            System.out.println("去重前：");
            for (String s : stringList) {
                System.out.println(s);
            }
            System.out.println();
            stringList = stringList.stream().distinct().collect(Collectors.toList());
            System.out.println("去重后：");
            for (String s : stringList) {
                System.out.println(s);
            }
            System.out.println();
          }
        
        
        public void listDistinctByStreamDistinct2() throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            // 1. 对于 Student 列表去重
            List<Student> studentList = new ArrayList<Student>(){{
                add(new Student("zs", 1, 1));
                add(new Student("ls", 2, 2));
                add(new Student("zs", 3, 3));
                add(new Student("ls", 2, 2));
            }};
            
            System.out.println("去重前：");
            System.out.println(objectMapper.writeValueAsString(studentList));
            studentList = studentList.stream().distinct().collect(Collectors.toList());
            System.out.println("去重后：");
            System.out.println(objectMapper.writeValueAsString(studentList));
            
            
            // 这里我们引入了两个静态方法，以及通过 TreeSet<> 来达到获取不同元素的效果
            // 1. import static java.util.stream.Collectors.collectingAndThen;
            // 2. import static java.util.stream.Collectors.toCollection;
            studentList = studentList.stream().collect(
                Collectors.collectingAndThen(
                  Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new)
            );
            System.out.println("根据名字去重后 :");
            System.out.println(objectMapper.writeValueAsString(studentList));
          }
        
        
        public void distinctByProperty2() throws JsonProcessingException {
            // 这里第二种方法我们通过过滤来实现根据对象某个属性去重
            ObjectMapper objectMapper = new ObjectMapper();
            List<Student> studentList = new ArrayList<Student>(){{
                add(new Student("zs", 1, 1));
                add(new Student("ls", 2, 2));
                add(new Student("zs", 3, 3));
                add(new Student("ls", 2, 2));
            }};
         
            System.out.println("去重前        :");
            System.out.println(objectMapper.writeValueAsString(studentList));
            studentList = studentList.stream().distinct().collect(Collectors.toList());
            System.out.println("distinct去重后:");
            System.out.println(objectMapper.writeValueAsString(studentList));
            // 这里我们将 distinctByKey() 方法作为 filter() 的参数，过滤掉那些不能加入到 set 的元素
            studentList = studentList.stream().filter(distinctByKey(Student::getName)).collect(Collectors.toList());
            System.out.println("根据名字去重后 :");
            System.out.println(objectMapper.writeValueAsString(studentList));
          }
        
        private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
            Set<Object> seen = ConcurrentHashMap.newKeySet();
            return t -> seen.add(keyExtractor.apply(t));
        }

}   
