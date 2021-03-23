package tech.madest.eparser.utils;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.io.IOUtils;
import tech.madest.eparser.exception.AppHttpException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class AppHttpUtils {

    public static synchronized String getHtmlResponse( String url ) throws AppHttpException {
        return getHtmlResponse( url, null );
    }

    public static synchronized String getHtmlResponse( String url, String trashTag ) throws AppHttpException {
        String htmlResponse = null;
        HttpTransport httpTransport = null;
        try {
            httpTransport = new NetHttpTransport();
            HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
            HttpRequest request = null;
            try {
                request = requestFactory.buildGetRequest( new GenericUrl( url ) );
                htmlResponse = request.execute().parseAsString();
            } catch ( Exception e ) {
                htmlResponse = getByConnection( url );
                if ( htmlResponse == null ) {
                    return null;
                }
            }
            htmlResponse = htmlResponse.replace( "\n", "" ).replace( "\r", "" ).replace( "\t", "" );
            // Убираем заголовок
            if ( trashTag != null ){
                int trashIdx = htmlResponse.indexOf( trashTag ) + trashTag.length();
                htmlResponse = htmlResponse.substring( trashIdx );
            }
            // Убираем комментарии
            htmlResponse = ParseUtils.polish( htmlResponse );
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new AppHttpException( "Can't parse url: " + url + ", reason: " + e.getMessage() );
        } finally {
            if ( httpTransport != null ){
                try {
                    httpTransport.shutdown();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }

        return htmlResponse;
    }

    private static String getByConnection( String path ) {
        URL url = null;
        InputStream fis = null;
        HttpURLConnection conn = null;
        String htmlResponse = null;
        try {
            url = new URL( path );
            conn = ( HttpURLConnection ) url.openConnection();
            conn.setRequestMethod( "GET" );
            conn.setRequestProperty( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36 OPR/55.0.2994.61" );
            conn.setRequestProperty( "Accept-Charset", "UTF-8" );
            conn.setRequestProperty( "cookie", "\"PHPSESSID=67ec6f35bb02319ecfa1ed39ac8a9dc7; BITRIX_SM_GUEST_ID=1139945; _ym_uid=1541056815363914308; _ym_d=1541056815; _ym_isad=2; BX_USER_ID=7166c070211ba99c47b371acf9547c0d; BITRIX_SM_LAST_VISIT=01.11.2018+11%3A28%3A14; BITRIX_SM_LAST_ADV=5; _ym_visorc_3081571=w\"" );
            conn.connect();
            fis = conn.getInputStream();
            htmlResponse = IOUtils.toString( fis, "UTF-8" );
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            if ( fis != null ) {
                try {
                    fis.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
        return htmlResponse;
    }
}
