package tech.madest.eparser.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class ParsingPageDto {

    private Integer id;
    private String pageName;
    private String pageType;
    private Integer linkId;
    private String parseUrl;
    private String prefixUrl;
    private String pageAttr;
    private Integer pageCount;
    private Integer processed;
    private Timestamp lastUpdate;
    private String updateResult;
    private String description;
    private String tagLessInfo;
    private String tagSectionStart;

}
