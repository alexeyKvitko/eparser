package tech.madest.eparser.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.madest.eparser.dto.PageTagDto;
import tech.madest.eparser.model.ApiResponse;
import tech.madest.eparser.service.impl.PageTagServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping( "/api/page" )
public class PageTagController {

    private static final Logger LOG = LoggerFactory.getLogger( PageTagController.class);

    @Autowired
    PageTagServiceImpl pagetTagService;

    @RequestMapping(value = "/allTags", method = RequestMethod.GET)
    public ApiResponse< List< PageTagDto > > getAllTags(@RequestParam( value = "pageId", required = true ) Integer pageId){
        ApiResponse response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( pagetTagService.getAllTagByPage( pageId ) );
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
            pagetTagService.upsertPageTag( pageTagDto );
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
            pagetTagService.deletePageTag( pageTagId );
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
