package tech.madest.eparser.rest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.madest.eparser.dto.PageTagDto;
import tech.madest.eparser.entity.PageTagEntity;
import tech.madest.eparser.entity.shopizer.product.manufacturer.Manufacturer;
import tech.madest.eparser.model.ApiResponse;
import tech.madest.eparser.model.PageData;
import tech.madest.eparser.repository.PageTagRepository;
import tech.madest.eparser.service.impl.PageTagServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping( "/api/page" )
public class PageTagController {

    private static final Logger LOG = LoggerFactory.getLogger( PageTagController.class);

    @Autowired
    PageTagServiceImpl pageTagService;


    @RequestMapping(value = "/pageData", method = RequestMethod.GET)
    public ApiResponse< PageData > getAllTags( @RequestParam( value = "pageId", required = true ) Integer pageId){
        ApiResponse<PageData> response = new ApiResponse();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( pageTagService.getAllTagByPage( pageId ) );
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
