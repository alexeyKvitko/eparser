package tech.madest.eparser.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.madest.eparser.dto.PageTagDto;
import tech.madest.eparser.dto.ParsingPageDto;
import tech.madest.eparser.model.ApiResponse;
import tech.madest.eparser.model.PageType;
import tech.madest.eparser.model.TestResult;
import tech.madest.eparser.service.impl.ParseServiceImpl;
import tech.madest.eparser.service.impl.ParsingPageServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping( "/api/parse" )
public class ParseController {

    private static final Logger LOG = LoggerFactory.getLogger( ParseController.class);

    @Autowired
    ParseServiceImpl parseService;

    @Autowired
    ParsingPageServiceImpl parsingPageService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ApiResponse< List< TestResult > > testResult( @RequestParam( value = "pageId", required = true ) Integer pageId){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( parseService.testParsing( pageId, false ) );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't parse page : "+e.getMessage();
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/scheduler", method = RequestMethod.GET)
    public ApiResponse< String > addToScheduler( @RequestParam( value = "pageId", required = true ) Integer pageId,
                                                 @RequestParam( value = "categoryId", required = false ) Integer categoryId){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( parseService.setScheduller( pageId, categoryId ) );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't parse page : "+e.getMessage();
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/getPagesByCategory", method = RequestMethod.GET)
    public ApiResponse< List< ParsingPageDto > > getPagesByCategory( @RequestParam( value = "categoryId", required = true ) Integer categoryId){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( parsingPageService.getParsingPagesByLinkIdAndPageType( categoryId, PageType.PAGE_CATEGORY ) );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't parse page : "+e.getMessage();
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();
        }
        return response;
    }

}
