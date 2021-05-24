package com.yzm.base._object.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 克隆，浅拷贝
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Cloneable, Serializable {
    private static final long serialVersionUID = -6448317997667250815L;
    private String name;
    private Integer age;
    private Address address;

    @Override
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

}

