package tech.madest.eparser;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AppConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "remd_admin";

    public static final String SPACE_SPLIT = "&nbsp;";

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    public static final int DIRECT_FORWARD = 1;
    public static final int INLINE_VALUE = 0;
    public static final int DIRECT_BACKWARD = -1;

    public static final int MINUS_ONE = -1;

    public static final Map<String,String> HTML_TAGS = new LinkedHashMap<String,String>(){
        {
            put("<p>","");
            put("</p>","");
            put("<h3>","");
            put("</h3>","");
            put("<span>","");
            put("</span","");
            put("</div","");
            put("div","");
            put("div","");
            put("class","");
            put("&quot;","");
            put("&nbsp;"," ");
            put("руб.","");
            put("руб","");
            put("/>","");
            put("</","");
            put(">","");
            put("<","");
            put("=","");
            put("br","");
            put("&#1088;","");
            put("&#171;","«");
            put("&#187;","»");
        }
    };

}
