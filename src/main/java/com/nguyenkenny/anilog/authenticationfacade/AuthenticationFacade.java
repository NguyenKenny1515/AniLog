package com.nguyenkenny.anilog.authenticationfacade;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthenticatedUser();
}
