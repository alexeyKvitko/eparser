package tech.madest.eparser.entity.shopizer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table( name = "MANUFACTURER" )
public class Manufacturer {

    @Id
    @Column( name = "MANUFACTURER_ID" )
    private Integer id;

    @Column( name = "DATE_CREATED" )
    private Timestamp dateCreated;

    @Column( name = "DATE_MODIFIED" )
    private Timestamp dateModified;

    @Column( name = "UPDT_ID" )
    private String updtId;

    @Column( name = "CODE" )
    private String code;

    @Column( name = "MANUFACTURER_IMAGE" )
    private String manufacturerImage;

    @Column( name = "SORT_ORDER" )
    private Integer sortOrder;

    @Column( name = "MERCHANT_ID" )
    private Integer merchantId;

    @OneToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "MANUFACTURER_ID")
    private ManufacturerDescription manufacturerDescription;

}
