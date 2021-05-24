package com.yzm.base._object.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 克隆，深拷贝
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person2 implements Cloneable {
    private String name;
    private Integer age;
    private Address2 address;

    @Override
    public Person2 clone() throws CloneNotSupportedException {
        Person2 person = (Person2) super.clone();
        Address2 address = person.getAddress();
        if (address != null) person.setAddress(address.clone());
        return person;
    }
}

