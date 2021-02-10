package tech.madest.eparser.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "company_page")
@Setter
@Getter
public class CompanyPageEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "page_name" )
    private String pageName;

    @Column( name = "company_id" )
    private Integer companyId;

    @Column( name = "parse_url" )
    private String parseUrl;

    @Column( name = "page_attr" )
    private String pageAttr;

    @Column( name = "page_count" )
    private Integer pageCount;

    @Column( name = "processed" )
    private Integer processed;

    @Column( name = "last_update" )
    private Timestamp lastUpdate;

    @Column( name = "update_result" )
    private String updateResult;

    @Column( name = "description" )
    private String description;

    @Column( name = "tag_less_info")
    private String tagLessInfo;

    @Column( name = "tag_section_start")
    private String tagSectionStart;

}
