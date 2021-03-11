package tech.madest.eparser.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryDto implements Serializable {

    private Integer categoryId;
    private Integer depth;
    private Integer parentId;
    private String categoryCode;
    private String categoryName;
    private String categoryImage;

}
