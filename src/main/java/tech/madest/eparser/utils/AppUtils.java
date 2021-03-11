package tech.madest.eparser.utils;

import tech.madest.eparser.model.ParseItem;

import java.util.List;

public abstract class AppUtils {

    public static String getFieldValueByName( String fieldName, List< ParseItem > parseItems ){
        return parseItems.stream().filter( item -> fieldName.equals( item.getDisplayName() ) ).findFirst().get().getParseValue();
    }

    public static final boolean isNullOrEmpty(List objects){
        boolean nullOrEmpty =  objects == null || objects.size() == 0;
        if ( !nullOrEmpty ){
            nullOrEmpty = true;
            for( Object o : objects ){
                if ( o != null ){
                    nullOrEmpty = false;
                    break;
                }
            }
        }
        return nullOrEmpty;
    }


}
