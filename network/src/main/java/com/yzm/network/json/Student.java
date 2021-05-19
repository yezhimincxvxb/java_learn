package com.yzm.network.json;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    private Integer id;
    private String username;
    private boolean enable;
    private LocalDateTime localDateTime;
}
