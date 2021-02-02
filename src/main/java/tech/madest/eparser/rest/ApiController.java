package tech.madest.eparser.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.madest.eparser.dto.CompanyDto;
import tech.madest.eparser.model.ApiResponse;
import tech.madest.eparser.service.impl.CompanyServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping( "/api" )
public class ApiController {

    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    CompanyServiceImpl companyService;

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ApiResponse< List< CompanyDto > > getAllCompanies(){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( companyService.getAllCompanies() );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            response.setMessage( "Can't get companies ..." );
            LOG.error( "Can't get companies: "+e.getMessage() );
            e.printStackTrace();

        }
        return response;
    }
}
