package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private long user_id;
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    @Column(name="model_id")
    private long model_id;

    @Column(name="role")
    private String role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-reference")
    private List<Reviews> reviewsList;

    public long getModel_id() {
        return model_id;
    }

    public void setModel_id(long model_id) {
        this.model_id = model_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
// Data= Lombok gens getter, setters
}

