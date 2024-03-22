package com.investmentapplication.investmentapplication.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="useraccounts")
public class UserAccountsEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String firstName;

    private String lastName;

    private Date dateofBirth;

    private Long ssn;

    private String mailingAddress;

    private String email;

    private String phoneNumber;

    private String password;

    private String securityQuestion1;

    private String securityQuestion1answer;

    private String securityQuestion2;

    private String securityQuestion2answer;

    private String securityQuestion3;

    private String securityQuestion3answer;

    private Timestamp updatedAt;

    private Timestamp createdAt;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
