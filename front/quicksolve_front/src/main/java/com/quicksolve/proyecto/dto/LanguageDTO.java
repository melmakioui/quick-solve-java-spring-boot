package com.quicksolve.proyecto.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LanguageDTO {

    private String key;
    private String value;
    private Map<String,String> keyValue = new HashMap<>();


}
