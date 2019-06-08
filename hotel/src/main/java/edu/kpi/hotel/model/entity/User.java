package edu.kpi.hotel.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity("users")
public class User {
    @Id
    private ObjectId id;

    @NotNull
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-Z_0-9]+$")
    @Indexed(options = @IndexOptions(unique = true))
    private String username;

    @Email
    @Indexed(options = @IndexOptions(unique = true))
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    @PositiveOrZero
    private BigDecimal balance = new BigDecimal(0);

    @NotNull
    private UserRole role = UserRole.CLIENT;

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isClient() {
        return role == UserRole.CLIENT;
    }

    public boolean isAdministrator() {
        return role == UserRole.ADMINISTRATOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(passwordHash, user.passwordHash)) return false;
        if (!Objects.equals(balance, user.balance)) return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
