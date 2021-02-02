package tech.madest.eparser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Integer id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private String role;

}
