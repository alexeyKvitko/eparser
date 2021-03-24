package tech.madest.eparser.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.madest.eparser.dto.PageTagDto;
import tech.madest.eparser.model.ApiResponse;
import tech.madest.eparser.model.PageData;
import tech.madest.eparser.model.PageType;
import tech.madest.eparser.service.impl.PageTagServiceImpl;

@RestController
@CrossOrigin
@RequestMapping( "/api/page" )
public class PageTagController {

    private static final Logger LOG = LoggerFactory.getLogger( PageTagController.class);

    @Autowired
    PageTagServiceImpl pageTagService;


    @RequestMapping(value = "/pageData", method = RequestMethod.GET)
    public ApiResponse< PageData > getPageData( @RequestParam( value = "pageId", required = true ) Integer pageId,
                                                @RequestParam( value = "pageType", required = true ) String pageType){
        ApiResponse<PageData> response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            switch ( PageType.fromValue( pageType ) ){
                case PAGE_MANUFACTURER:
                    response.setResult( pageTagService.getManufacturerPageData( pageId ) );
                    break;
                case PAGE_CATEGORY:
                    response.setResult( pageTagService.getCategoryPageData( pageId ) );
                    break;
            }
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't get page tags: "+e.getMessage();
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/upsertTag", method = RequestMethod.POST)
    public ApiResponse upsertPageTag( @RequestBody PageTagDto pageTagDto ){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            pageTagService.upsertPageTag( pageTagDto );
        } catch ( Exception e ){
            response.setStatus( HttpStatus.BAD_REQUEST.value() );
            String errMsg = "Can't upsert company: "+ pageTagDto.getTagName();
            response.setMessage( errMsg );
            LOG.error( errMsg );
            e.printStackTrace();

        }
        return response;
    }

    @RequestMapping(value = "/deleteTag", method = RequestMethod.POST)
    public ApiResponse deletePageTag( @RequestBody Integer pageTagId ){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            pageTagService.deletePageTag( pageTagId );
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
