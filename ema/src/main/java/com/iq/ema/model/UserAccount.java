package com.iq.ema.model;

import com.iq.ema.validators.UniqueUserEmail;
import com.iq.ema.validators.UniqueUserName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
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

}
