package com.iq.ema.model;

import com.iq.ema.validators.UniqueUserEmail;
import com.iq.ema.validators.UniqueUserName;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    @NotBlank(message="*Must give a unique username")
    @UniqueUserName
    private String userName;

    @NotBlank
    @Email(message="*Must be a valid email address")
    @UniqueUserEmail
    private String email;

    @NotBlank(message="*Must give a password")
    @Size(min=2, max=50)
    private String password;

    @NotBlank(message="*Must give a role")
    private String role="ADMIN";

    private boolean enabled = true;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserAccount() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
