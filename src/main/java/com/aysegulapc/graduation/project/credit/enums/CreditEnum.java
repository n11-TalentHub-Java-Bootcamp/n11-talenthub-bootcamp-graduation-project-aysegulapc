package com.aysegulapc.graduation.project.credit.enums;

public enum CreditEnum {
    ONAY("ONAY"),
    RED("RED");

    private final String type;

    private CreditEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
