package com.investmentapplication.investmentapplication.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "useraccounts")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String mailingaddress;
    private String ssn;
    private String phonenumbeer;
    private Date dateofbirth;

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
