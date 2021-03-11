package tech.madest.eparser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageTagDto {

    private Integer id;
    private Integer pageId;
    private String tagName;
    private String mapTable;
    private String mapField;
    private String startTag;
    private String endTag;
    private Integer reversed;
    private Integer entryNumber;
    private Integer isImage;
    private Integer needTranslate;

    public PageTagDto( String tagName, Integer isImage, String mapTable, String mapField ) {
        this.tagName = tagName;
        this.isImage = isImage;
        this.mapTable = mapTable;
        this.mapField = mapField;
    }
}
