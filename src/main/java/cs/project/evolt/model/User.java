package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.*;

import java.awt.print.Book;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private long userId;
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
    private List<Bookmark> bookmarkList;

//    @Lob
//    @Column(name = "profile_image", columnDefinition = "LONGBLOB")  // BLOB = Binary large object ex. image, audio, video
//    private byte[] profileImage;

}

