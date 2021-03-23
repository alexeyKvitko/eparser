package tech.madest.eparser.model;

public enum PageType {

    PAGE_MANUFACTURER("page_manufacturer"),
    PAGE_CATEGORY("page_category");

    private String value;

    PageType( String value ) {
        this.value = value;
    }

    public static PageType fromValue( String value ){
        for (PageType pageType : PageType.values()) {
            if (pageType.value.equals(value)) {
                return pageType;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public String getValue() {
        return value;
    }
}
