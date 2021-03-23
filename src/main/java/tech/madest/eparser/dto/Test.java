package tech.madest.eparser.dto;

import ru.CryptoPro.JCP.ASN.PKIX1Explicit88._stateOrProvinceName_Type;

public class Test {

    public static void main( String[] args ) {
        String lineAge="/1//3//4//6/";
        System.out.println("Result: "+lineAge.replace( "//","," ).replace( "/","" ));
    }
}
