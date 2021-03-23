package tech.madest.eparser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParseItem {

    private String displayName;
    private String parseValue;
    private boolean isImage;

}
