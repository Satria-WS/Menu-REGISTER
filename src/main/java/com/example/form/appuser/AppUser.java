package com.example.form.appuser;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

//lombok sntrax
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
//the reason why implementing user details is to do with security
public class AppUser implements UserDetails {

    //create the id must private

    @SequenceGenerator(
            name = "student_squence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    //as enum
    private AppUserRole appUserRole;
    //check did whether  account is locked or not
    private Boolean locked;
    private Boolean enabled;


    //create generate constructor
    //the entity with jpa in a second

    public AppUser(String name, String username, String email, String password, AppUserRole appUserRole, Boolean locked, Boolean enabled) {


        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //get authority
        SimpleGrantedAuthority authority = 
                new SimpleGrantedAuthority(appUserRole.name());
        //then pass the authority there
        return Collections.singletonList(authority);
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
        //we're not keeping track for this
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
