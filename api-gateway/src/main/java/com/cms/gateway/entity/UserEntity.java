package com.cms.gateway.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "INT(11) UNSIGNED NOT NULL")
    private Integer userId;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_uuid", columnDefinition = "VARCHAR(255) UNIQUE")
    private String userUuid = UUID.randomUUID().toString();

    @Column(name = "first_name", columnDefinition = "VARCHAR(255) NOT NULL")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(255) NOT NULL")
    private String lastName;

    @Formula(value = "CONCAT(first_name, ' ', last_name)")
    private String fullName;

    @Column(name = "username", columnDefinition = "VARCHAR(255) NOT NULL")
    private String username;

    @Column(name = "email_address", columnDefinition = "VARCHAR(255) NOT NULL")
    private String emailAddress;

    @Column(name = "password", columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;

    @Column(name = "date_registered", columnDefinition = "INT(11) NOT NULL")
    private Long dateRegistered;

    @Column(name = "last_logged_in", columnDefinition = "INT(11) DEFAULT NULL")
    private Long lastLoggedIn;

    @Column(name = "last_updated", columnDefinition = "INT(11) DEFAULT NULL")
    private Long lastUpdated;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @PrePersist
    private void prePersist(){
        LocalDateTime time = LocalDateTime.now();
        dateRegistered = time.toEpochSecond(ZoneOffset.UTC);
    }

    @PreUpdate
    public void preUpdate() {
        LocalDateTime time = LocalDateTime.now();
        lastUpdated = time.toEpochSecond(ZoneOffset.UTC);
    }
}
