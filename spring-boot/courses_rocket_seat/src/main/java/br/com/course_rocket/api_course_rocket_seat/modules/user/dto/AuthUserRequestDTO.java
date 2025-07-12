package br.com.course_rocket.api_course_rocket_seat.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthUserRequestDTO {

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and be at least 6 characters long.")
    private String password;
}
