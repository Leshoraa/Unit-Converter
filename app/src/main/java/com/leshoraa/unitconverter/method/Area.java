package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Area {

    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("km²", 1_000_000.0);
        conversionFactors.put("m²", 1.0);
        conversionFactors.put("cm²", 0.0001);
        conversionFactors.put("mm²", 1e-6);
        conversionFactors.put("mi²", 2_589_988.11);
        conversionFactors.put("yd²", 0.836127);
        conversionFactors.put("ft²", 0.092903);
        conversionFactors.put("in²", 0.00064516);
        conversionFactors.put("ha", 10_000.0);
        conversionFactors.put("ac", 4_046.86);
    }

    public static double convert(double value, String fromUnit, String toUnit) {
        fromUnit = extractSymbol(fromUnit);
        toUnit = extractSymbol(toUnit);

        if (!conversionFactors.containsKey(fromUnit)) {
            throw new IllegalArgumentException("Unknown from unit: " + fromUnit);
        }
        if (!conversionFactors.containsKey(toUnit)) {
            throw new IllegalArgumentException("Unknown to unit: " + toUnit);
        }

        double valueInSquareMeters = value * conversionFactors.get(fromUnit);
        return valueInSquareMeters / conversionFactors.get(toUnit);
    }

    private static String extractSymbol(String unit) {
        if (unit.contains("(") && unit.contains(")")) {
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
        double valueInSquareMeters = value * fromFactor;
        double result = valueInSquareMeters / toFactor;

        StringBuilder explanation_area = new StringBuilder();
        explanation_area.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_area.append("Step 1: Convert ").append(fromUnit).append(" to square meters (m²)\n");
        explanation_area.append("1 ").append(fromUnit).append(" = ").append(fromFactor).append(" m²\n");
        explanation_area.append(value).append(" × ").append(fromFactor).append(" = ").append(valueInSquareMeters).append(" m²\n\n");

        explanation_area.append("Step 2: Convert m² to ").append(toUnit).append("\n");
        explanation_area.append("1 ").append(toUnit).append(" = ").append(toFactor).append(" m²\n");
        explanation_area.append(valueInSquareMeters).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_area.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_area.toString();
    }
}
