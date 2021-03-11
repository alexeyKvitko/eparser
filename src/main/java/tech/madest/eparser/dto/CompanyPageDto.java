package tech.madest.eparser.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class CompanyPageDto {

    private Integer id;
    private String pageName;
    private String pageType;
    private Integer companyId;
    private String parseUrl;
    private String pageAttr;
    private Integer pageCount;
    private Integer processed;
    private Timestamp lastUpdate;
    private String updateResult;
    private String description;
    private String tagLessInfo;
    private String tagSectionStart;

}
