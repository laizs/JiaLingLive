package com.gzzsc.lai.entity;

public class User {
    private Long id;

    private String username;

    private String password;

    private Long cfgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getCfgId() {
        return cfgId;
    }

    public void setCfgId(Long cfgId) {
        this.cfgId = cfgId;
    }
}