package com.yzm.base.transient_learm;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4663455818498495324L;
    private String username;
    private transient String password;
}
