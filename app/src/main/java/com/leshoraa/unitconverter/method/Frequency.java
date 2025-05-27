package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Frequency {
    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("Hz", 1.0);
        conversionFactors.put("kHz", 1e3);
        conversionFactors.put("MHz", 1e6);
        conversionFactors.put("GHz", 1e9);
        conversionFactors.put("THz", 1e12);
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

        double fromFactor = conversionFactors.get(fromUnit);
        double toFactor = conversionFactors.get(toUnit);
        double result = value * fromFactor / toFactor;

        StringBuilder explanation_frequency = new StringBuilder();
        explanation_frequency.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_frequency.append("Step 1: Convert to Hz:\n");
        explanation_frequency.append(value).append(" × ").append(fromFactor).append(" = ").append(value * fromFactor).append(" Hz\n\n");

        explanation_frequency.append("Step 2: Convert Hz to ").append(toUnit).append(":\n");
        explanation_frequency.append((value * fromFactor)).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_frequency.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_frequency.toString();
    }
}
