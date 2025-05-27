package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Radiation {
    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("Bq", 1.0);
        conversionFactors.put("Ci", 3.7e10);
        conversionFactors.put("Sv", 1.0);
        conversionFactors.put("Gy", 1.0);
        conversionFactors.put("R", 0.00933);
        conversionFactors.put("rad", 0.01);
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

        StringBuilder explanation_radiation = new StringBuilder();
        explanation_radiation.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_radiation.append("Step 1: Convert to base unit (Bq, Sv, or Gy):\n");
        explanation_radiation.append(value).append(" × ").append(fromFactor).append(" = ").append(value * fromFactor).append(" base unit\n\n");

        explanation_radiation.append("Step 2: Convert base unit to ").append(toUnit).append(":\n");
        explanation_radiation.append((value * fromFactor)).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_radiation.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_radiation.toString();
    }
}
