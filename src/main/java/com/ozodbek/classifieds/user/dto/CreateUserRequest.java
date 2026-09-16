package com.ozodbek.classifieds.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(

        @NotBlank
        @Size(min = 2, max = 50)
        String name,

        @Email
        @NotBlank
        @Size(max = 255)
        String email
) {
    public CreateUserRequest {
        if (name != null) {
            name = name.trim();
        }

        if (email != null) {
            email = email.trim().toLowerCase();
        }
    }
}
