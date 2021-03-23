package tech.madest.eparser.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "page_tag" )
@Setter
@Getter
public class PageTagEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "page_id" )
    private Integer pageId;

    @Column( name = "tag_name" )
    private String tagName;

    @Column( name = "map_table" )
    private String mapTable;

    @Column( name = "map_field" )
    private String mapField;


    @Column( name = "start_tag" )
    private String startTag;

    @Column( name = "end_tag" )
    private String endTag;

    @Column( name = "reversed" )
    private Integer reversed;

    @Column( name = "entry_number" )
    private Integer entryNumber;

    @Column( name = "is_image" )
    private Integer isImage;

    @Column( name = "need_translate" )
    private Integer needTranslate;

    @Column( name = "inner_search" )
    private Integer innerSearch;

}
