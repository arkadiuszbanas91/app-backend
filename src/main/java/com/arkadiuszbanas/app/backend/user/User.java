package com.arkadiuszbanas.app.backend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private int enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty(value = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled > 0;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled ? 1 : 0;
    }
}
