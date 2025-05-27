package com.leshoraa.unitconverter.model;

import java.io.Serializable;

public class UnitItem implements Serializable {
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_UNIT = 1;

    private int type;
    private String name;

    public UnitItem(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getLabel() {
        return name;
    }

    public String getUnitSymbol() {
        if (name.contains("(") && name.contains(")")) {
            int start = name.indexOf("(") + 1;
            int end = name.indexOf(")");
            return name.substring(start, end);
        }
        return "";
    }

    public String getUnitName() {
        if (name.contains("(")) {
            return name.substring(0, name.indexOf("(")).trim();
        }
        return name;
    }
}