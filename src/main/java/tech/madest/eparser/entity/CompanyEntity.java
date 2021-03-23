package tech.madest.eparser.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Setter
@Getter
public class CompanyEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "company_name" )
    private String companyName;

    @Column( name = "thumb" )
    private String thumb;

    @Column( name = "url" )
    private String url;

    @Column( name = "country" )
    private String country;

    @Column( name = "language" )
    private String language;


}
