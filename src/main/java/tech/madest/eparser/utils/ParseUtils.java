package tech.madest.eparser.utils;

import org.apache.commons.lang.StringUtils;
import tech.madest.eparser.AppConstants;
import tech.madest.eparser.model.SearchParam;

public abstract class ParseUtils {

    private static final String START_COMMENT= "<!--";
    private static final String END_COMMENT= "-->";
    private static final String LEFT_QUOT= "&#8220;";
    private static final String RIGHT_QUOT= "&#8221;";
    private static final String LEFT_QUOT_LIT= "«";
    private static final String RIGHT_QUOT_LIT= "»";
    private static final String BACK_SLASH_TRIM= " / ";
    private static final String BACK_SLASH= "/";


    public static synchronized String getValueFromHtml( String html, String start, String end,int entry ) {
        if ( start == null || end == null || start.trim().length() == 0 || end.trim().length() == 0 ){
            return null;
        }
        StringBuilder sb = new StringBuilder( );
        int entryIndex = StringUtils.ordinalIndexOf( html,start,entry);
        int startIndex = entryIndex > -1 ? entryIndex + start.length( ) : -1;
        if ( startIndex == -1 ) {
            return null;
        }
        for ( int i = startIndex; i < html.length( ); i++ ) {
            if ( html.charAt( i ) == end.charAt( 0 ) ) {
                break;
            }
            sb.append( html.charAt( i ) );
        }
        return sb.toString( ).trim( );
    }

    public static synchronized String getBackValueFromHtml( String html, String end, String start ) {
        if ( start == null || end == null || start.trim().length() == 0 || end.trim().length() == 0 ){
            return null;
        }
        StringBuilder sb = new StringBuilder( );
        int endIndex = html.indexOf( end ) - 1;
        if ( endIndex == -1 ) {
            return null;
        }
        int startIndex = endIndex;
        for ( int i = endIndex; i >= 0; i-- ) {
            if ( html.charAt( i ) == start.charAt( 0 ) ) {
                startIndex = i;
                break;
            }
        }
        for ( int i = startIndex + 1; i <= endIndex; i++ ) {
            sb.append( html.charAt( i ) );
        }
        return sb.toString( ).trim( ).length() > 0 ? sb.toString( ).trim( ) : null;
    }


    public static String getBeetwenRange(String html, String startTag, String endTag){
        String result = "";
        int startIdx = html.indexOf(startTag);
        int endIdx = html.indexOf(endTag);
        result =html.substring(startIdx+startTag.length(), endIdx);
        return result;
    }



    public static synchronized String getFieldValue( String html, SearchParam param ) {
        if ( AppConstants.SPACE_SPLIT.equals( param.getEndTag( ) ) ) {
            param.setEndTag( " " );
        }
        String value = null;
        switch( param.getDirection() ){
            case AppConstants.DIRECT_FORWARD:
                value = getValueFromHtml( html, param.getStartTag(), param.getEndTag(), param.getEntry() );
                break;
            case AppConstants.DIRECT_BACKWARD:
                value = getBackValueFromHtml( html, param.getStartTag(), param.getEndTag() );
                break;
            case AppConstants.INLINE_VALUE:
                value = param.getStartTag();
                break;
            default: break;
        }
        if ( value != null ) {
            for ( String htmlTag : AppConstants.HTML_TAGS.keySet() ) {
                value = value.replace( htmlTag, AppConstants.HTML_TAGS.get( htmlTag ) );
            }
            value = value.trim( );
        }
        return value;
    }

    public static String getSection( String html, String sectionTag ) {
        int endIdx = html.indexOf( sectionTag );
        String section = html.substring( endIdx + sectionTag.length( ) );
        endIdx = section.indexOf( sectionTag );
        if ( endIdx > -1 ) {
            section = section.substring( 0, endIdx );
        }
        return section;
    }

    public static String getInlineSection( String html, String sectionTag ) {
        int startIdx = html.indexOf( sectionTag );
        String subSection = html.substring( startIdx + sectionTag.length( ) );
        String section = null;
        int endIdx = subSection.indexOf( sectionTag );
        if ( endIdx > -1 ) {
            section = html.substring( startIdx, startIdx+endIdx + sectionTag.length( ) );
        }
        return section;
    }

    public static synchronized String polish( String html ) {
        boolean processed = true;
        while ( processed ) {
            int startIdx = html.indexOf( START_COMMENT );
            processed = startIdx > -1;
            if ( processed ) {
                int endIdx = html.indexOf( END_COMMENT ) + 3;
                String remove = html.substring( startIdx, endIdx );
                html = html.replace( remove, "" );
            }

        }
        html = html.replaceAll( LEFT_QUOT, LEFT_QUOT_LIT );
        html = html.replaceAll( RIGHT_QUOT, RIGHT_QUOT_LIT );
        html = html.replaceAll( BACK_SLASH_TRIM, BACK_SLASH );
        //уберем множественные пробелы
        StringBuilder sb = new StringBuilder( );
        char prev = 65;
        for ( int i = 0; i < html.length( ); i++ ) {
            if ( html.charAt( i ) != 32 || ( html.charAt( i ) == 32 && prev != 32 ) ) {
                sb.append( html.charAt( i ) );
            }
            prev = html.charAt( i );
        }
        return sb.toString( );
    }

}
