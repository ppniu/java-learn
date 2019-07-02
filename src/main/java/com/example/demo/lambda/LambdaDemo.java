package com.example.demo.lambda;


import org.hibernate.validator.internal.util.StringHelper;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 * @date 2017/10/23 0023
 */

public class LambdaDemo {

    public void testFilter1() {
        // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
        //例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        List<String> names = new ArrayList<>();
        names.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
    }
    

    private List<Person> peoples;


    public void init() {
        peoples = new ArrayList<>();
        peoples.add(new Person("a", 10));
        peoples.add(new Person("a", 20));
        peoples.add(new Person("b", 15));
        peoples.add(new Person("c", 25));

    }


   
    public void testGroup() {
        Map<String, List<Person>> personMap = peoples.stream().collect(Collectors.groupingBy(Person::getName));
      //  Assert.assertEquals(personMap.get("b").get(0), peoples.get(2));
    }

   
    public void tesReduce() {
        int data = peoples.stream().map(person -> person.age * 2)
                .reduce((item, result) -> {
                    System.out.println("item is " + item);
                    if (result > 40) {
                        return result;
                    }
                    return item;
                }).get();

        System.out.println("result is " + data);

    }

   
    public void testMin() {
        Person person = peoples.stream().min((p1, p2) -> (p1.age - p2.age)).get();
        System.out.println("min age is " + person.age);
    }

   
    public void testSort() {
        peoples.stream().sorted((p1, p2) -> (p2.age - p1.age)).collect(Collectors.toList())
                .forEach(person -> {
                    System.out.println("name is " + person.name + " and age is " + person.age);
                });
    }

   
    public void testFilter() {
        Predicate<Person> ageFilter = (p) -> (p.age > 20);
        peoples.stream().filter((ageFilter)).collect(Collectors.toList()).forEach(person -> {
            System.out.println("name is " + person.name + " and age is " + person.age);
        });

    }

    
    public void testStat() {
        DoubleSummaryStatistics doubleSummaryStatistics = peoples.stream()
                .mapToDouble((person) -> person.age).summaryStatistics();
        System.out.println("List中最大的数字 : " + doubleSummaryStatistics.getMax());
        System.out.println("List中最小的数字 : " + doubleSummaryStatistics.getMin());
        System.out.println("所有数字的总和   : " + doubleSummaryStatistics.getSum());
        System.out.println("所有数字的平均值 : " + doubleSummaryStatistics.getAverage());
    }

    class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String getName() {
            return this.name;
        }
    }

  
    public void optional() throws Exception {
        Person person = null;
        //Optional.ofNullable(person).orElseThrow(() -> new Exception("用户不存在"));
        person = Optional.ofNullable(person).orElse(new Person("a", 11));
        String name = Optional.ofNullable(person).map(p -> p.name).get();
    
        System.out.println("name is " + name);
    }
}
