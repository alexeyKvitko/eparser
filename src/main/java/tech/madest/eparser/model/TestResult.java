package tech.madest.eparser.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestResult {

    private String section;
    private String result;
    private String message;
    private String pageType;
    private List<Block> data;

}
