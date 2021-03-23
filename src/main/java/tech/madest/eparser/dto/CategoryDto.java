package tech.madest.eparser.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class CategoryDto implements Serializable {

    private Integer categoryId;
    private Integer depth;
    private Integer parentId;
    private String categoryCode;
    private String categoryName;
    private String categoryImage;
    private boolean node;
    private List<CategoryDto> childs;

    public CategoryDto() {
        this.childs = new LinkedList<>();
    }
}
