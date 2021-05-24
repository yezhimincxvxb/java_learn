package com.yzm.base._object.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address2 implements Cloneable {
    private String province;
    private String city;

    @Override
    public Address2 clone() throws CloneNotSupportedException {
        return (Address2) super.clone();
    }
}
