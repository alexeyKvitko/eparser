package tech.madest.eparser.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.madest.eparser.AppConstants;
import tech.madest.eparser.entity.CompanyPageEntity;
import tech.madest.eparser.entity.PageTagEntity;
import tech.madest.eparser.exception.AppHttpException;
import tech.madest.eparser.model.Block;
import tech.madest.eparser.model.ParseItem;
import tech.madest.eparser.model.SearchParam;
import tech.madest.eparser.model.TestResult;
import tech.madest.eparser.repository.CompanyPageRepository;
import tech.madest.eparser.repository.PageTagRepository;
import tech.madest.eparser.utils.AppHttpUtils;
import tech.madest.eparser.utils.ParseUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class ParseServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger( ParseServiceImpl.class);

    @Autowired
    PageTagRepository pageTagRepo;

    @Autowired
    CompanyPageRepository companyPageRepo;

    public TestResult testParsing(Integer pageId){
        CompanyPageEntity  companyPage = companyPageRepo.findById( pageId ).get();
        List< PageTagEntity > tags = pageTagRepo.findAllByPageId( pageId );
        System.out.println(" START PARSE PAGE: "+companyPage.getPageName());
        TestResult testResult = new TestResult();
        testResult.setResult( AppConstants.SUCCESS );
        String htmlResponse = null;
        try {
            htmlResponse = AppHttpUtils.getHtmlResponse( companyPage.getParseUrl(), companyPage.getTagLessInfo() );
        } catch ( AppHttpException e ) {
            LOG.error( e.getMessage() );
            e.printStackTrace();
        }
        List< Block > parsedData = new LinkedList<>();
        boolean proceed = true;
        boolean atFirst = true;
        int idx = 0;
        while ( proceed ){
            idx++;
            try {
                int endSectionIdx = htmlResponse.indexOf( companyPage.getTagSectionStart() );
                String section = null;
                if ( endSectionIdx > -1 ) {
                    section = ParseUtils.getSection( htmlResponse, companyPage.getTagSectionStart() );
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
                for ( PageTagEntity tag : tags ) {
                    SearchParam searchParam = new SearchParam( tag.getStartTag(), tag.getEndTag(), tag.getReversed(), tag.getEntryNumber() );
                    String value = ParseUtils.getFieldValue( section, searchParam );
                    if ( firstTag && value == null || atFirst ) {
                        proceed = false;
                    }
                    if ( proceed ) {
                        ParseItem result = new ParseItem();
                        result.setDisplayName( tag.getTagName() );
                        result.setParseValue( value );
                        result.setImage( tag.getIsImage() == 1 );
                        parseItems.add( result );
                    }
                }
                parsedData.add( new Block( idx, parseItems) );
            } catch ( Exception e ){
                proceed = false;
                String error = "Parsing break, " + companyPage.getPageName()  + "], reason: " + e.getMessage();
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
        return testResult;
    }

}
