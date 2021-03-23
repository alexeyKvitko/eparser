package tech.madest.eparser.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.madest.eparser.dto.CompanyDto;
import tech.madest.eparser.dto.ParsingPageDto;
import tech.madest.eparser.model.ApiResponse;
import tech.madest.eparser.service.impl.CompanyServiceImpl;
import tech.madest.eparser.service.impl.ParsingPageServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping( "/api/company" )
public class CompanyController {

    private static final Logger LOG = LoggerFactory.getLogger( CompanyController.class);

    @Autowired
    CompanyServiceImpl companyService;

    @Autowired
    ParsingPageServiceImpl parsingPageService;

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

    @RequestMapping(value = "/upsert", method = RequestMethod.POST)
    public ApiResponse upsertCompany( @RequestBody CompanyDto company ){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
             companyService.upsertCompany( company );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't upsert company: "+ company.getCompanyName();
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();

        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponse deleteCompany( @RequestBody Integer companyId ){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            companyService.deleteCompany( companyId );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't delete company: ";
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();

        }
        return response;
    }

    @RequestMapping(value = "/upsertPage", method = RequestMethod.POST)
    public ApiResponse upsertCompanyPage( @RequestBody ParsingPageDto parsingPageDto ){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            parsingPageService.upsertCompanyPage( parsingPageDto );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't upsert company: "+ parsingPageDto.getPageName();
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();

        }
        return response;
    }

    @RequestMapping(value = "/deletePage", method = RequestMethod.POST)
    public ApiResponse deleteCompanyPage( @RequestBody Integer companyPageId ){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            parsingPageService.deleteCompanyPage( companyPageId );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't delete company: ";
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();

        }
        return response;
    }
}
