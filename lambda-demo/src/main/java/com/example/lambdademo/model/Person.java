package com.example.lambdademo.model;

import lombok.Data;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Person
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/21 17:40
 * @Version: 1.0
 */
public interface Person {

     public String name = "1";
     Integer age = 1;
}
class Program{
    public static List<Person> PersonsList()
    {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 7; i++)
        {
            Person p = new Person() {
                name = "132456";

            };
            persons.add(p);
        }
        return persons;
    }

    static void Main(string[] args)
    {
        List<Person> persons = PersonsList();
        persons = persons.Where(p => p.Age > 6).ToList();       //所有Age>6的Person的集合
        Person per = persons.SingleOrDefault(p => p.Age == 1);  //Age=1的单个people类
        persons = persons.Where(p => p.Name.Contains("儿子")).ToList();   //所有Name包含儿子的Person的集合
    }
}
