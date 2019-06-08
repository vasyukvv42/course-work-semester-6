package edu.kpi.hotel.controller.dto;


import javax.validation.constraints.*;

public class UserDto {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-Z_0-9]+$", message = "must consist of latin letters, numbers or underscores")
    private String username;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 30)
    private String password;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
