package com.yzm.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Account implements Serializable {
    private static final long serialVersionUID = -2209821905931217493L;
    private Integer id;
    private String name;
    private double money;
}
