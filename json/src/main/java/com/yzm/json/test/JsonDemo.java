package com.yzm.json.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzm.json.entity.Student;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDemo {

    public static void main(String[] args) {
//        demo01();
        demo02();
    }

    private static void demo01() {
//        method01();
        method01_2();
    }

    private static void method01() {
        JSONObject object = (JSONObject) JSON.parse("{\"id\":110,\"username\":\"警察\"}");
        JSONObject object2 = JSON.parseObject("{\"id\":110,\"username\":\"警察\"}");
        Student object3 = JSON.parseObject("{\"id\":110,\"username\":\"警察\"}", Student.class);
        System.out.println("object = " + object);
        System.out.println("object2 = " + object2);
        System.out.println("object3 = " + object3);
        System.out.println("==================================");

        JSONArray array = (JSONArray) JSON.parse("['游泳','下棋','马拉松']");
        JSONArray array2 = JSON.parseArray("['游泳','下棋','马拉松']");
        List<String> array3 = JSON.parseArray("['游泳','下棋','马拉松']", String.class);
        System.out.println("array = " + array);
        System.out.println("array2 = " + array2);
        System.out.println("array3 = " + array3);
    }

    private static void method01_2() {
        Student student = new Student();
        student.setId(2);
        student.setUsername("小二郎");
        student.setEnable(true);
        student.setLocalDateTime(LocalDateTime.now());

        String jsonString = JSON.toJSONString(student);
        System.out.println("jsonString = " + jsonString);
        String jsonFormat = JSON.toJSONString(student, true);
        System.out.println("jsonFormat = " + jsonFormat);
        JSONObject object = (JSONObject) JSON.toJSON(student);
        System.out.println("object = " + object);
    }

    private static void demo02() {

//        method02();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");

        String mapJson = JSON.toJSONString(map);
        System.out.println("mapJson = " + mapJson);

    }

    private static void method02() {
        JSONObject object = new JSONObject();
        object.put("id", 2);
        object.put("username", "无所谓");
        object.put("enable", true);
        List<Integer> integers = Arrays.asList(1, 2, 3);
        object.put("hobby", integers);
        object.put("null", null);
//        System.out.println(object);

        String s = JSON.toJSONString(object, true);
        System.out.println("s = " + s);
    }

    private static void method02_2() {
        JSONObject object = JSONObject.parseObject("{\"boolean\":true,\"string\":\"string\",\"list\":[1,2,3],\"int\":2}");
        System.out.println(object.getString("string"));
        System.out.println(object.getIntValue("int"));
        System.out.println(object.getBooleanValue("boolean"));
        List<Integer> integers = JSON.parseArray(object.getJSONArray("list").toJSONString(), Integer.class);
        integers.forEach(System.out::println);
        System.out.println(object.getString("null"));
    }

}
