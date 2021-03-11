package tech.madest.eparser.entity.shopizer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table( name = "CATEGORY_DESCRIPTION" )
public class CategoryDescription {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "DESCRIPTION_ID" )
    private Integer id;

    @Column( name = "CATEGORY_ID" )
    private Integer categoryId;

    @Column( name = "DATE_CREATED" )
    private Timestamp dateCreated;

    @Column( name = "DATE_MODIFIED" )
    private Timestamp dateModified;

    @Column( name = "UPDT_ID" )
    private String updtId;

    @Column( name = "DESCRIPTION" )
    private String description;

    @Column( name = "NAME" )
    private String name;

    @Column( name = "TITLE" )
    private String title;

    @Column( name = "CATEGORY_HIGHLIGHT" )
    private String categoryHighlight;

    @Column( name = "META_DESCRIPTION" )
    private String metaDescription;

    @Column( name = "META_KEYWORDS" )
    private String metaKeyword;

    @Column( name = "META_TITLE" )
    private String metaTitle;

    @Column( name = "SEF_URL" )
    private String sefURL;

    @Column( name = "LANGUAGE_ID" )
    private Integer languageId;

}
