package tech.madest.eparser.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
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

}
