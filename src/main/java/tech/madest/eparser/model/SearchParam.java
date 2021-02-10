package tech.madest.eparser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchParam {

    private String startTag;
    private String endTag;
    private int direction;
    private int entry;
}
