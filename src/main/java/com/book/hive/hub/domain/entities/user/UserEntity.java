package com.book.hive.hub.domain.entities.user;

import com.book.hive.hub.domain.entities.common.BaseEntity;
import com.book.hive.hub.domain.enums.UserRole;
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
import java.util.List;

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

    @Column(name = "role", nullable = false)
    @NotNull
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER"));

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
