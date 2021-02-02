package tech.madest.eparser.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginUser implements Serializable {

    private String username;
    private String password;
}
