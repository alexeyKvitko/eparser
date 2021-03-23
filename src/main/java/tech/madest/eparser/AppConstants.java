package tech.madest.eparser;

import tech.madest.eparser.dto.PageTagDto;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AppConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "remd_admin";
    public static final String E_PARSER = "e_parser";

    public static final String SPACE_SPLIT = "&nbsp;";

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    public static final int FAKE_ID = -1;
    public static final int INT_YES = 1;
    public static final int INT_NO = 0;

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

    public static final String MANUFACTURER_TABLE = "manufacturer";
    public static final String MANUFACTURER_FIELD = "manufacturer_field";
    public static final String MANUFACTURED_NAME = "Наименование производителя";
    public static final String MANUFACTURED_DESC = "Описание производителя";
    public static final String MANUFACTURED_LOGO = "Логотип производителя";
    public static final List< PageTagDto > MANUFACTURER_TAG_NAMES = new LinkedList<PageTagDto>(){{
        add( new PageTagDto( MANUFACTURED_NAME, 0, MANUFACTURER_TABLE, MANUFACTURER_FIELD ) );
        add( new PageTagDto( MANUFACTURED_DESC, 0, MANUFACTURER_TABLE, MANUFACTURER_FIELD ) );
        add( new PageTagDto( MANUFACTURED_LOGO, 1, MANUFACTURER_TABLE, MANUFACTURER_FIELD ) );
    }};

    public static final String PRODUCT_TABLE = "product";
    public static final String PRODUCT_FIELD = "product_field";
    public static final String PRODUCT_INNER_URL = "Url точного описания";
    public static final String PRODUCT_NAME = "Название продукта";
    public static final String PRODUCT_MAIN_DESC = "Основные моменты о продукте";
    public static final String PRODUCT_DESCRIPTION = "Описание продукта";
    public static final String PRODUCT_META_TAG = "(Мета теги) Описание";
    public static final String PRODUCT_PRICE = "Цена продукта";
    public static final String PRODUCT_QUANTITY_AVAIL = "Доступное Количество";
    public static final String PRODUCT_MIN_ORDER = "Минимум колличества заказа";
    public static final String PRODUCT_MAX_ORDER = "Максимум колличества заказа";
    public static final String PRODUCT_WEIGHT = "Вес";
    public static final String PRODUCT_HEIGHT = "Высота";
    public static final String PRODUCT_WIDTH = "Ширина";
    public static final String PRODUCT_LENGTH = "Длина";
    public static final String PRODUCT_IMAGE = "Изображение";

    public static final List< PageTagDto > PRODUCT_TAG_NAMES = new LinkedList<PageTagDto>(){{
        add( new PageTagDto( PRODUCT_INNER_URL, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_NAME, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_MAIN_DESC, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_DESCRIPTION, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_META_TAG, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_PRICE, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_QUANTITY_AVAIL, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_MIN_ORDER, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_MAX_ORDER, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_WEIGHT, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_HEIGHT, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_WIDTH, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_LENGTH, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
        add( new PageTagDto( PRODUCT_IMAGE, 0,PRODUCT_TABLE, PRODUCT_FIELD ) );
    }};




}
