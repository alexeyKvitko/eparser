package tech.madest.eparser.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.madest.eparser.model.ApiResponse;
import tech.madest.eparser.model.EPBootstrap;
import tech.madest.eparser.service.impl.BootstrapServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/bootstrap")
public class BootstrapController {

    private static final Logger LOG = LoggerFactory.getLogger( BootstrapController.class);

    @Autowired
    BootstrapServiceImpl bootstrapService;

    @RequestMapping(value = "/initialize", method = RequestMethod.GET)
    public ApiResponse< EPBootstrap > getEPBootstrap(){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( bootstrapService.getBootstrapModel() );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            response.setMessage( "Can't get companies ..." );
            LOG.error( "Can't get companies: "+e.getMessage() );
            e.printStackTrace();
        }

        return response;
    }

}
