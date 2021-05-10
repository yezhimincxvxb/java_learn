package com.yzm.io.stream.object_stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectDemo implements Serializable {
    private static final long serialVersionUID = 4632035312337249942L;
    private Integer id;
    private String username;
    private String password;
}