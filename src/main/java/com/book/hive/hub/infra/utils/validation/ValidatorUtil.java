package com.book.hive.hub.infra.utils.validation;

import com.book.hive.hub.presentation.dto.response.user.UserResponseDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class ValidatorUtil {
    public static boolean isUserAuthorized(UserResponseDto user) {

        UserDetails authenticatedUser = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        return authenticatedUser.getAuthorities().stream()
                .anyMatch(authority -> authority
                        .getAuthority().equals("ROLE_ADMIN"))
                || authenticatedUser.getUsername().equals(user.username());
    }
}
