package ru.aizatgaz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceData extends BaseData {

    private String name;
    private Integer year;
    private String color;

    @JsonProperty("pantone_value")
    private String pantoneValue;
}
