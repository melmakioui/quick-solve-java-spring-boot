package com.quicksolve.proyecto.entity.type;

public enum MethodType {
    ROUND_ROBIN("ROUND_ROBIN"),
    LESS_INCIDENCES_TECH("LESS_INCIDENCES_TECH");

    public final String name;

    MethodType(String s) {
        name = s;
    }
}
