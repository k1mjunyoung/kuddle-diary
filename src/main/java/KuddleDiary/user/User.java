package KuddleDiary.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.StringJoiner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(unique = true)
    private String id;

    private String password;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    private LocalDateTime createDateTime;

    public User(String id, String password, String name, String email, LocalDateTime createDateTime) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("uid=" + uid)
                .add("id='" + id + "'")
                .add("password='" + password + "'")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .add("createDateTime=" + createDateTime)
                .toString();
    }
}
