package pl.edu.pk.schedulegenerator.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
@Document(collection = "Role")
public class Role {

    @Id
    private String id;
    private RoleName name;

}
