package tech.madest.eparser.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CompanyDto implements Serializable {

    private Integer id;
    private String companyName;
    private String thumb;
    private String url;
    private String country;
    private String language;
    private List<CompanyPageDto> companyPages;

}
