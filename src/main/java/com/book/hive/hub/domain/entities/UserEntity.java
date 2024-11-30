package com.book.hive.hub.domain.entities;

import com.book.hive.hub.domain.entities.Common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(length = 50, unique = true, nullable = false)
    @Size(min = 2, max = 50)
    @NotNull
    private String username;

    @Column(length = 100, unique = true, nullable = false)
    @Email
    @NotNull
    private String email;

    @Column(length = 100, nullable = false)
    @NotNull
    @Size(min = 8)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false)
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    @NotNull
    private LocalDate birthDate;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (RoleEntity role : this.roles) {
            if ("ADMIN".equals(role.getName())) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                return authorities;
            }
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
