package ems_project_2023.dao.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;


@Data
@Entity
@Table(name = "user", schema = "public")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min=2, max=500, message = "Не меньше 2 знаков")
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min=2, max=500, message = "Не меньше 2 знаков")
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    @ManyToOne
    @JoinColumn (name="groupid")
    private Group groupUser;

    @Override
    public String getUsername() {
        return name;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role.getName()));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

}