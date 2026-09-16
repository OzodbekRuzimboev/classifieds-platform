package com.ozodbek.classifieds.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @Size(min = 2, max = 50)
        @Pattern(regexp = ".*\\S.*", message = "Name cannot be blank.")
        String name,

        @Email
        @Size(max = 255)
        @Pattern(regexp = ".*\\S.*", message = "Email cannot be blank.")
        String email
) {
    public UpdateUserRequest {
        if (name != null) {
            name = name.trim();
        }

        if (email != null) {
            email = email.trim().toLowerCase();
        }
    }
}
