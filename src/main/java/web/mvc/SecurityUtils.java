package web.mvc;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import web.mvc.security.CustomUserDetails;

public final class SecurityUtils {
    private SecurityUtils() {}

    public static CustomUserDetails currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            throw new IllegalStateException("인증되지 않은 사용자입니다.");
        }
        Object principal = auth.getPrincipal();
        if(!(principal instanceof CustomUserDetails)) {
            throw new IllegalStateException("인증된 사용자의 정보가 CustomUserDetails 타입이 아닙니다.");
        }
        return (CustomUserDetails) principal;
    }

    public static Long currentUserId() {
        return currentUser().getUser().getUserId();
    }

    public static String currentUsername() {
        return currentUser().getUsername();
    }
}
