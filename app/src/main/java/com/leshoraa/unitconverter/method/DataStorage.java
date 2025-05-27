package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class DataStorage {

    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("b", 1.0); // Bit
        conversionFactors.put("B", 8.0); // Byte = 8 bits
        conversionFactors.put("KB", 8_000.0); // 1 KB = 1000 Bytes = 8000 bits
        conversionFactors.put("MB", 8_000_000.0); // 1 MB = 1,000,000 Bytes
        conversionFactors.put("GB", 8_000_000_000.0);
        conversionFactors.put("TB", 8_000_000_000_000.0);
        conversionFactors.put("PB", 8_000_000_000_000_000.0);
        conversionFactors.put("EB", 8_000_000_000_000_000_000.0);
    }

    public static double convert(double value, String fromUnit, String toUnit) {
        fromUnit = extractSymbol(fromUnit);
        toUnit = extractSymbol(toUnit);

        if (!conversionFactors.containsKey(fromUnit) || !conversionFactors.containsKey(toUnit)) {
            throw new IllegalArgumentException("Unknown unit: " + fromUnit + " or " + toUnit);
        }

        double inBits = value * conversionFactors.get(fromUnit);
        return inBits / conversionFactors.get(toUnit);
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

        if (!conversionFactors.containsKey(fromUnit) || !conversionFactors.containsKey(toUnit)) {
            return "Conversion not supported between " + fromUnit + " and " + toUnit;
        }

        if (fromUnit.equals(toUnit)) {
            return "Both units are the same, so the value remains unchanged: " + value + " " + fromUnit;
        }

        double fromFactor = conversionFactors.get(fromUnit);
        double toFactor = conversionFactors.get(toUnit);
        double inBits = value * fromFactor;
        double result = inBits / toFactor;

        StringBuilder explanation_data = new StringBuilder();
        explanation_data.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_data.append("Step 1: Convert to bits:\n");
        explanation_data.append(value).append(" × ").append(fromFactor).append(" = ").append(inBits).append(" bits\n\n");

        explanation_data.append("Step 2: Convert bits to ").append(toUnit).append(":\n");
        explanation_data.append(inBits).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_data.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_data.toString();
    }
}
