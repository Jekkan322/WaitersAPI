package com.server.demo.pojo;

import java.util.List;

public class JwtResponse {

    private String token;
    private Long idWaiter;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;

    public JwtResponse(String token, Long id, String username, List<String> roles,Long idWaiter) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.idWaiter=idWaiter;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getIdWaiter() {
        return idWaiter;
    }

    public void setIdWaiter(Long idWaiter) {
        this.idWaiter = idWaiter;
    }
}
