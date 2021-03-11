package tech.madest.eparser.entity.shopizer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table( name = "CATEGORY" )
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "CATEGORY_ID" )
    private Integer id;

    @Column( name = "DATE_CREATED" )
    private Timestamp dateCreated;

    @Column( name = "DATE_MODIFIED" )
    private Timestamp dateModified;

    @Column( name = "UPDT_ID" )
    private String updtId;

    @Column( name = "CATEGORY_IMAGE" )
    private String categoryImage;

    @Column( name = "CATEGORY_STATUS" )
    private int categoryStatus;

    @Column( name = "CODE" )
    private String code;

    @Column( name = "DEPTH" )
    private Integer depth;

    @Column( name = "FEATURED" )
    private int featured;

    @Column( name = "LINEAGE" )
    private String lineAge;

    @Column( name = "SORT_ORDER" )
    private Integer sortOrder;

    @Column( name = "VISIBLE" )
    private int visible;

    @Column( name = "MERCHANT_ID" )
    private Integer merchantId;

    @Column( name = "PARENT_ID" )
    private Integer parentId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryDescription categoryDescription;

}
