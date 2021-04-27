package com.yzm.base.transient_learm;

import lombok.Data;

import java.io.Serializable;

@Data
public class User2 implements Serializable {
    private static final long serialVersionUID = -5346506031424184297L;
    public static String username;
    private transient String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        User2.username = username;
    }
}
