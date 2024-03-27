package ru.aizatgaz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class UserRequestAndResponseData extends BaseData {

    private String name;
    private String job;
    private String createdAt;
    private String updatedAt;

}
