package tech.madest.eparser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

    private String token;
    private String username;
    private String userRole;

}
