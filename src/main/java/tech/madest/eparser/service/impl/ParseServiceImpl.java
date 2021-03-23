package tech.madest.eparser.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.madest.eparser.AppConstants;
import tech.madest.eparser.entity.ParsingPageEntity;
import tech.madest.eparser.entity.PageTagEntity;
import tech.madest.eparser.exception.AppHttpException;
import tech.madest.eparser.model.*;
import tech.madest.eparser.repository.ParsingPageRepository;
import tech.madest.eparser.repository.PageTagRepository;
import tech.madest.eparser.utils.AppHttpUtils;
import tech.madest.eparser.utils.AppUtils;
import tech.madest.eparser.utils.ParseUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class ParseServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger( ParseServiceImpl.class );

    @Autowired
    PageTagRepository pageTagRepo;

    @Autowired
    ParsingPageRepository parsingPageRepo;

    @Autowired
    ManufacturerServiceImpl manufacturerService;

    @Autowired
    ProductServiceImpl productService;

    public TestResult testParsing( Integer pageId, boolean addToScheduler ) {
        ParsingPageEntity parsingPage = parsingPageRepo.findById( pageId ).get();
        parsingPage.setProcessed( addToScheduler ? AppConstants.INT_YES : AppConstants.INT_NO );
        parsingPageRepo.save( parsingPage );
        List< PageTagEntity > tags = pageTagRepo.findAllByPageId( pageId );
        System.out.println( " START PARSE PAGE: " + parsingPage.getPageName() );
        TestResult testResult = new TestResult();
        testResult.setResult( AppConstants.SUCCESS );
        testResult.setPageType( parsingPage.getPageType() );
        String htmlResponse = null;
        try {
            htmlResponse = AppHttpUtils.getHtmlResponse( parsingPage.getParseUrl(), parsingPage.getTagLessInfo() );
        } catch ( AppHttpException e ) {
            LOG.error( e.getMessage() );
            e.printStackTrace();
        }
        List< Block > parsedData = new LinkedList<>();
        boolean proceed = true;
        boolean atFirst = true;
        int idx = 0;
        boolean isCategoryPage = PageType.PAGE_CATEGORY.getValue().equals( parsingPage.getPageType() );
        while ( proceed ) {
            idx++;
            try {
                int endSectionIdx = htmlResponse.indexOf( parsingPage.getTagSectionStart() );
                String section = null;
                if ( endSectionIdx > -1 ) {
                    section = ParseUtils.getSection( htmlResponse, parsingPage.getTagSectionStart() );
                    htmlResponse = (endSectionIdx + section.length()) > htmlResponse.length() ? ""
                            : htmlResponse.substring( endSectionIdx + section.length() );
                    atFirst = false;
                } else {
                    section = htmlResponse;
                }
                if ( testResult.getSection() == null ) {
                    testResult.setSection( section );
                }
                boolean firstTag = true;
                List< ParseItem > parseItems = new LinkedList<>();
                String innerHtml = null;
                for ( PageTagEntity tag : tags ) {
                    if ( AppUtils.isNullOrEmpty( tag.getStartTag() ) ) {
                        continue;
                    }
                    SearchParam searchParam = new SearchParam( tag.getStartTag(), tag.getEndTag(), tag.getReversed(), tag.getEntryNumber() );
                    String searchContainer = innerHtml != null && tag.getInnerSearch() == 1 ? innerHtml : section;
                    String value = ParseUtils.getFieldValue( searchContainer, searchParam );
                    if ( isCategoryPage && AppConstants.PRODUCT_INNER_URL.equals( tag.getTagName() ) ) {
                        try {
                            innerHtml = AppHttpUtils.getHtmlResponse( parsingPage.getPrefixUrl() + value );
                        } catch ( Exception e ) {
                            LOG.error( "Can't get inner html, from url: " + parsingPage.getPrefixUrl() + value );
                        }
                    }
                    if ( firstTag && value == null || atFirst ) {
                        proceed = false;
                    }
                    firstTag = false;
                    if ( proceed ) {
                        ParseItem result = new ParseItem();
                        result.setDisplayName( tag.getTagName() );
                        result.setParseValue( value );
                        result.setImage( tag.getIsImage() == 1 );
                        parseItems.add( result );
                    }
                }
                parsedData.add( new Block( idx, parseItems ) );
            } catch ( Exception e ) {
                proceed = false;
                String error = "Parsing break, " + parsingPage.getPageName() + "], reason: " + e.getMessage();
                LOG.error( error );
                System.out.println( "-------------------------------------" );
                System.out.println( error );
                System.out.println( "-------------------------------------" );
                e.printStackTrace();
                testResult.setResult( AppConstants.FAILURE );
                testResult.setMessage( error );
            }
        }
        testResult.setData( parsedData );
        System.out.println( " END PARSE PAGE: " + parsingPage.getPageName() );
        return testResult;
    }

    public String setScheduller( Integer pageId, Integer categoryId ) {
        TestResult data = testParsing( pageId, true );
        String result = AppConstants.SUCCESS;
        try {
            if ( PageType.PAGE_MANUFACTURER.getValue().equals( data.getPageType() ) ) {
                manufacturerService.addManufacturers( pageId, data.getData() );
            } else if ( PageType.PAGE_CATEGORY.getValue().equals( data.getPageType() ) ) {
                productService.addProducts( Long.valueOf( pageId ), Long.valueOf( categoryId ), data.getData() );
            }
        } catch ( Exception e ) {
            result = AppConstants.FAILURE;
        }
        return result;
    }

}
