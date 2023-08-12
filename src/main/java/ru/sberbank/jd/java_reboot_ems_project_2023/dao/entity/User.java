package ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min=2, max=50, message = "Не меньше 2 знаков")
    private String username;
    @NotBlank
    @Size(min=2, max=50, message = "Не меньше 2 знаков")
    private String password;
    @Transient
    private String passwordConfirm;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="group_id")
    private Group groupUser;

    @Transient
    private String firstName;
    @Transient
    private String lastName;

    public User(String username, String password, Role role, Group group) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.groupUser  = group;
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

    public Role getRoles() {
        return role;
    }

    public void setRoles(Role role) {
        this.role = role;
    }

    public Group getGroup() {
        return groupUser;
    }

    public void setGroup(Group group) {
        this.groupUser = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}