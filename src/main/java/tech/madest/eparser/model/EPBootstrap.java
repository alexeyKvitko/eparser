package tech.madest.eparser.model;

import lombok.Getter;
import lombok.Setter;
import tech.madest.eparser.dto.CategoryDto;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class EPBootstrap {

    private List< CategoryDto > categories;

    public EPBootstrap() {
        this.categories = new LinkedList<>();
    }
}
