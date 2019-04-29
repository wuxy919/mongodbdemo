package com.wuxy.test;

import com.wuxy.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: wuxy
 * @create: 2019-04-28 18:52
 **/
public class Test {

    private static MongoTemplate mongoTemplate;
    static {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        mongoTemplate = (MongoTemplate)applicationContext.getBean("mongoTemplate");
    }

    public static void main(String[] args) {
//        insert();
//        deleteByCondition();
//        deleteReturn();
//        deleteList();
//        deleteCollection();
//        dropDb();
//        update();
        upsert();
    }

    /**
     * 添加
     */
    public static void insert() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setName("tom" + i);
            student.setAge(18);
            student.setAddTime(new Date());
            list.add(student);
        }
        mongoTemplate.insert(list, Student.class);
    }

    /**
     * 按条件删除
     */
    public static void deleteByCondition() {
        mongoTemplate.remove(Query.query(new Criteria("name").is("tom0")), Student.class);
    }

    /**
     * 删除并返回结果
     */
    public static void deleteReturn() {
        Student student = mongoTemplate.findAndRemove(Query.query(new Criteria("name").is("tom1")), Student.class);
        System.out.println(student);
    }

    /**
     * 删除多条并返回集合
     */
    public static void deleteList() {
        List<Student> list = mongoTemplate.findAllAndRemove(Query.query(new Criteria("age").is(18)), Student.class);
        for (Student student : list) {
            System.out.println(student);
        }
    }

    /**
     * 删除集合
     */
    public static void deleteCollection() {
//        mongoTemplate.dropCollection(Student.class);
        mongoTemplate.dropCollection("student");
    }

    /**
     * 删除数据库
     */
    public static void dropDb() {
        mongoTemplate.getDb().drop();
    }

    /**
     * 修改?????
     */
    public static void update() {
        mongoTemplate.updateFirst(Query.query(new Criteria("name").is("tom0")), Update.update("age", 33), Student.class);
//        mongoTemplate.updateFirst(Query.query(new Criteria("name").is("tom1")), Update.update("a_time", new Date()), Update.update("age", 22), Student.class);
    }

    /**
     * upsert
     */
    public static void upsert() {
        mongoTemplate.upsert(Query.query(new Criteria("name").is("mic")), Update.update("age", 26), Student.class);
    }

}
