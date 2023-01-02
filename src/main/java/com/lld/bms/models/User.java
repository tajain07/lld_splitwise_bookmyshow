package com.lld.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import com.lld.bms.services.password.PasswordEncoder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "USERS")
@NoArgsConstructor
public class User extends AuditTable {
    private String email;
    private String username;
    private String hashedSaltedPassword;

    public User(String username) {
        this.username = username;
    }

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        if (password.length() < 8) {
            throw new RuntimeException("must have at least 8 characters");
        }
        String salt = "salt"; // from some service
        this.hashedSaltedPassword = passwordEncoder.encode(password + salt);
        this.hashedSaltedPassword += ";" + salt;
    }

    public boolean checkPassword(String password, PasswordEncoder passwordEncoder) {
        // this method checks whether the given password
        // matches the actual password
        String salt = this.hashedSaltedPassword.split(";")[1];
        // password-encoder is a dependency that we need
        String encoded = passwordEncoder.encode(password + salt) + salt;
        return encoded.equals(this.getHashedSaltedPassword());
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}

// Password - Auth0 -- Salt + Hash (abc:password) BCrypt encoder