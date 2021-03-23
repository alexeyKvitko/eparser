package tech.madest.eparser.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "page_manufacturer")
public class PageManufacturerEntity {

    @Column( name="page_id")
    private Long pageId;

    @Id
    @Column( name="manufacturer_id")
    private Long manufacturerId;

}
