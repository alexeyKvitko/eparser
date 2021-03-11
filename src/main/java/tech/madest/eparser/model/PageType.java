package tech.madest.eparser.model;

public enum PageType {

    PAGE_MANUFACTURER("page_manufacturer");

    private String value;

    PageType( String value ) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
