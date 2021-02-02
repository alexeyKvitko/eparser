package tech.madest.eparser.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import tech.madest.eparser.config.JwtTokenUtil;
import tech.madest.eparser.model.ApiResponse;
import tech.madest.eparser.model.AuthToken;
import tech.madest.eparser.model.LoginUser;
import tech.madest.eparser.model.User;
import tech.madest.eparser.service.UserService;

import javax.naming.AuthenticationException;

@CrossOrigin
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public String helloWorld(){
        LOG.info( "HELLO WORLD" );
        final User user = userService.findOne("remd_admin");
        return "HELLO WORLD, "+(user.getLastName()+" "+user.getFirstName()+" "+user.getSecondName());
    }



    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse< AuthToken > register( @RequestBody LoginUser loginUser) throws AuthenticationException {
        LOG.info( "Start generate token"+loginUser.getUsername()+" "+loginUser.getPassword() );
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        } catch (Exception e){
            System.out.println("Exception: "+e);
            LOG.error("Can not autorize: "+loginUser.getUsername()+","+loginUser.getPassword());
            return new ApiResponse<>(401, "Unauthorized at rigistering", null);
        }
        final User user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        AuthToken authToken = new AuthToken();
        authToken.setToken( token );
        authToken.setUsername( user.getLastName()+" "+user.getFirstName()+" "+user.getSecondName() );
        authToken.setUserRole( user.getRole() );
        return new ApiResponse<>(200, "success", authToken);
    }

}
