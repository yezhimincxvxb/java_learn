package com.yzm.base._object.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * 序列化，深拷贝
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person3 implements Serializable {
    private static final long serialVersionUID = 3887934776141562072L;
    private String name;
    private Integer age;
    private Address address;

    public Person3 deepClone() throws Exception {
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        oos.flush();

        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        return (Person3) ois.readObject();
    }

}

