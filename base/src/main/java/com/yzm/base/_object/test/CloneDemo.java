package com.yzm.base._object.test;

import com.alibaba.fastjson.JSON;
import com.yzm.base._object.entity.*;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.BeanUtils;

public class CloneDemo {

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
//        demo04();
//        demo05();
        demo06();
    }

    /**
     * Object 的 clone()方法，浅拷贝
     */
    private static void demo01() {
        try {
            Person person = new Person("aaa", 18, new Address("广东", "深圳"));
            Person clone = person.clone();

            // 浅拷贝后，原始对象跟克隆对象不是同一个对象
            // 原始对象跟克隆对象里的基本数据类型是独立的，修改彼此不影响
            // 原始对象跟克隆对象里的引用数据类型是共享的，修改则统一修改
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("原始对象跟克隆对象是否是同一对象：" + (person == clone));
            System.out.println("原始对象跟克隆对象里的引用数据类型对象是否是同一对象：" + (person.getAddress() == clone.getAddress()));
            System.out.println("==================================================");

            System.out.println("修改原始对象");
            person.setName("bbb");
            person.setAge(19);
            Address address = person.getAddress();
            address.setProvince("云南");
            address.setCity("丽江");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("==================================================");

            System.out.println("修改克隆对象");
            clone.setName("ccc");
            clone.setAge(20);
            Address cloneAdd = clone.getAddress();
            cloneAdd.setProvince("湖北");
            cloneAdd.setCity("武汉");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Object 的 clone()方法，深拷贝
     */
    private static void demo02() {
        try {
            Person2 person = new Person2("aaa", 18, new Address2("广东", "深圳"));
            Person2 clone = person.clone();

            // 深拷贝后，原始对象跟克隆对象不是同一个对象
            // 原始对象跟克隆对象里的基本数据类型是独立的，修改彼此不影响
            // 原始对象跟克隆对象里的引用数据类型是独立的，修改彼此不影响
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("原始对象跟克隆对象是否是同一对象：" + (person == clone));
            System.out.println("原始对象跟克隆对象里的引用数据类型对象是否是同一对象：" + (person.getAddress() == clone.getAddress()));
            System.out.println("==================================================");

            System.out.println("修改原始对象");
            person.setName("bbb");
            person.setAge(19);
            Address2 address = person.getAddress();
            address.setProvince("云南");
            address.setCity("丽江");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("==================================================");

            System.out.println("修改克隆对象");
            clone.setName("ccc");
            clone.setAge(20);
            Address2 cloneAdd = clone.getAddress();
            cloneAdd.setProvince("湖北");
            cloneAdd.setCity("武汉");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化实现深拷贝
     */
    private static void demo03() {
        try {
            Person3 person = new Person3("aaa", 18, new Address("广东", "深圳"));
            Person3 clone = person.deepClone();

            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("原始对象跟克隆对象是否是同一对象：" + (person == clone));
            System.out.println("原始对象跟克隆对象里的引用数据类型对象是否是同一对象：" + (person.getAddress() == clone.getAddress()));
            System.out.println("==================================================");

            System.out.println("修改原始对象");
            person.setName("bbb");
            person.setAge(19);
            Address address = person.getAddress();
            address.setProvince("云南");
            address.setCity("丽江");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("==================================================");

            System.out.println("修改克隆对象");
            clone.setName("ccc");
            clone.setAge(20);
            Address cloneAdd = clone.getAddress();
            cloneAdd.setProvince("湖北");
            cloneAdd.setCity("武汉");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * BeanUtils.copyProperties(Object source, Object target)实现浅拷贝
     */
    private static void demo04() {
        try {
            Person person = new Person("aaa", 18, new Address("广东", "广州"));
            Person clone = new Person();
            // 把person的属性赋值给clone
            BeanUtils.copyProperties(person, clone);

            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("原始对象跟克隆对象是否是同一对象：" + (person == clone));
            System.out.println("原始对象跟克隆对象里的引用数据类型对象是否是同一对象：" + (person.getAddress() == clone.getAddress()));
            System.out.println("==================================================");

            System.out.println("修改原始对象");
            person.setName("bbb");
            person.setAge(19);
            Address address = person.getAddress();
            address.setProvince("云南");
            address.setCity("丽江");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("==================================================");

            System.out.println("修改克隆对象");
            clone.setName("ccc");
            clone.setAge(20);
            Address cloneAdd = clone.getAddress();
            cloneAdd.setProvince("湖北");
            cloneAdd.setCity("武汉");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * org.apache.commons.lang3.SerializationUtils.clone(Object source) 实现深拷贝
     * 对象需实现序列化Serializable，引用类型属性对象也需要实现序列化
     */
    private static void demo05() {
        try {
            Person person = new Person("aaa", 18, new Address("广东", "广州"));
            Person clone = SerializationUtils.clone(person);

            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("原始对象跟克隆对象是否是同一对象：" + (person == clone));
            System.out.println("原始对象跟克隆对象里的引用数据类型对象是否是同一对象：" + (person.getAddress() == clone.getAddress()));
            System.out.println("==================================================");

            System.out.println("修改原始对象");
            person.setName("bbb");
            person.setAge(19);
            Address address = person.getAddress();
            address.setProvince("云南");
            address.setCity("丽江");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("==================================================");

            System.out.println("修改克隆对象");
            clone.setName("ccc");
            clone.setAge(20);
            Address cloneAdd = clone.getAddress();
            cloneAdd.setProvince("湖北");
            cloneAdd.setCity("武汉");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JSON实现深拷贝
     */
    private static void demo06() {
        try {
            Person person = new Person("aaa", 18, new Address("广东", "广州"));
            Person clone = JSON.parseObject(JSON.toJSONString(person), Person.class);

            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("原始对象跟克隆对象是否是同一对象：" + (person == clone));
            System.out.println("原始对象跟克隆对象里的引用数据类型对象是否是同一对象：" + (person.getAddress() == clone.getAddress()));
            System.out.println("==================================================");

            System.out.println("修改原始对象");
            person.setName("bbb");
            person.setAge(19);
            Address address = person.getAddress();
            address.setProvince("云南");
            address.setCity("丽江");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
            System.out.println("==================================================");

            System.out.println("修改克隆对象");
            clone.setName("ccc");
            clone.setAge(20);
            Address cloneAdd = clone.getAddress();
            cloneAdd.setProvince("湖北");
            cloneAdd.setCity("武汉");
            System.out.println("原始对象：" + person);
            System.out.println("克隆对象：" + clone);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
