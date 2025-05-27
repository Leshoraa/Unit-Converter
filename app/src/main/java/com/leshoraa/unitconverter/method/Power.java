package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Power {
    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("W", 1.0);
        conversionFactors.put("kW", 1000.0);
        conversionFactors.put("hp", 745.7);
        conversionFactors.put("MW", 1e6);
        conversionFactors.put("GW", 1e9);
        conversionFactors.put("BTU/h", 0.293071);
    }

    public static double convert(double value, String fromUnit, String toUnit) {
        fromUnit = extractSymbol(fromUnit);
        toUnit = extractSymbol(toUnit);
        return value * conversionFactors.get(fromUnit) / conversionFactors.get(toUnit);
    }

    private static String extractSymbol(String unit) {
        if (unit.contains("(")) {
            return unit.substring(unit.indexOf("(") + 1, unit.indexOf(")")).trim();
        }
        return unit.trim();
    }

    public static String getDetailedExplanation(double value, String fromUnit, String toUnit) {
        fromUnit = extractSymbol(fromUnit);
        toUnit = extractSymbol(toUnit);

        if (fromUnit.equals(toUnit)) {
            return "Both units are the same, so the value remains unchanged: " + value + " " + fromUnit;
        }

        if (!conversionFactors.containsKey(fromUnit) || !conversionFactors.containsKey(toUnit)) {
            return "Conversion not supported between " + fromUnit + " and " + toUnit;
        }

        double fromFactor = conversionFactors.get(fromUnit);
        double toFactor = conversionFactors.get(toUnit);
        double result = value * fromFactor / toFactor;

        StringBuilder explanation_power = new StringBuilder();
        explanation_power.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_power.append("Step 1: Multiply by the conversion factor of ").append(fromUnit).append("\n");
        explanation_power.append(value).append(" × ").append(fromFactor).append(" = ").append(value * fromFactor).append("\n\n");

        explanation_power.append("Step 2: Divide by the conversion factor of ").append(toUnit).append("\n");
        explanation_power.append((value * fromFactor)).append(" ÷ ").append(toFactor).append(" = ").append(result).append("\n\n");

        explanation_power.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_power.toString();
    }
}
