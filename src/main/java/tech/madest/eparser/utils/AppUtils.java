package tech.madest.eparser.utils;

import tech.madest.eparser.model.ParseItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public abstract class AppUtils {

    public static String getFieldValueByName( String fieldName, List< ParseItem > parseItems ) {
        Optional< ParseItem > optional = parseItems.stream().filter( item -> fieldName.equals( item.getDisplayName() ) ).findFirst();
        return optional.isPresent() ? optional.get().getParseValue() : null;
    }

    public static final boolean isNullOrEmpty( List objects ) {
        boolean nullOrEmpty = objects == null || objects.size() == 0;
        if ( !nullOrEmpty ) {
            nullOrEmpty = true;
            for ( Object o : objects ) {
                if ( o != null ) {
                    nullOrEmpty = false;
                    break;
                }
            }
        }
        return nullOrEmpty;
    }

    public static final boolean isNullOrEmpty( String value ) {
        return value == null || value.trim().length() == 0;
    }

    public static BigDecimal toBigDecimal( String value ) {
        BigDecimal bigDecimal = new BigDecimal( 0 );
        if ( value != null ) {
            try {
                bigDecimal = BigDecimal.valueOf( Double.valueOf( value ) );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        return bigDecimal;
    }

    public static Integer toInt( String value ) {
        Integer result = -1;
        if ( value != null ) {
            try {
                result = Integer.valueOf( value );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
