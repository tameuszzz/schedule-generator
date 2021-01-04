package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Document(collection = "User")
public class User {
    @Id
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Set<Role> roles = new HashSet<>();
}
