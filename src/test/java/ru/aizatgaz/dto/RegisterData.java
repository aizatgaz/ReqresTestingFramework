package ru.aizatgaz.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RegisterData {
    private String email;
    private String password;
}
