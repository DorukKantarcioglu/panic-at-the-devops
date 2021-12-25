package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import javax.persistence.*;

@Inheritance
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails  {
    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
    private String hesCode;
    private String phoneNumber;
    private boolean allowedOnCampus;
    private boolean vaccinated;
    private boolean tested;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Notification> notificationList;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Set<Reservation> reservations;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

