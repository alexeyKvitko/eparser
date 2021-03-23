package tech.madest.eparser.model;

import lombok.Getter;
import lombok.Setter;
import tech.madest.eparser.dto.PageTagDto;

import java.util.List;

@Getter
@Setter
public class PageData {

    private List< PageTagDto > pageTags;
    private List< Block > blocks;

}
