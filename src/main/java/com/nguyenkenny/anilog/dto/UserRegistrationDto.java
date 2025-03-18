package com.nguyenkenny.anilog.dto;

import com.nguyenkenny.anilog.validation.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@PasswordMatches
public class UserRegistrationDto {

    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 5, message = "Minimum password length is 5 characters")
    private String password;

    @NotNull(message = "Confirm Password is required")
    @NotEmpty(message = "Confirm Password is required")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
