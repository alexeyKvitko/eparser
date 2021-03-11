package tech.madest.eparser.entity.shopizer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table( name = "MANUFACTURER_DESCRIPTION" )
public class ManufacturerDescription {

    @Id
    @Column( name = "DESCRIPTION_ID" )
    private Integer id;

    @Column( name = "MANUFACTURER_ID" )
    private Integer manufacturedId;

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

    @Column( name = "DATE_LAST_CLICK" )
    private Timestamp dateLastClick;

    @Column( name = "MANUFACTURERS_URL" )
    private String manufacturersURL;

    @Column( name = "URL_CLICKED" )
    private Integer urlClicked;

    @Column( name = "LANGUAGE_ID" )
    private Integer languageId;

}
