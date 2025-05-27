package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Volume {

    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("L", 0.001);
        conversionFactors.put("mL", 1e-6);
        conversionFactors.put("m³", 1.0);
        conversionFactors.put("cm³", 1e-6);
        conversionFactors.put("mm³", 1e-9);
        conversionFactors.put("in³", 1.6387e-5);
        conversionFactors.put("ft³", 0.0283168);
        conversionFactors.put("yd³", 0.764555);
        conversionFactors.put("gal", 0.00378541); // US gallons
        conversionFactors.put("qt", 0.000946353);
        conversionFactors.put("pt", 0.000473176);
        conversionFactors.put("fl oz", 2.9574e-5);
        conversionFactors.put("bbl", 0.158987);
        conversionFactors.put("tsp", 4.9289e-6);
        conversionFactors.put("tbsp", 1.4787e-5);
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

        double valueInCubicMeters = value * conversionFactors.get(fromUnit);
        return valueInCubicMeters / conversionFactors.get(toUnit);
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
        double valueInCubicMeters = value * fromFactor;
        double result = valueInCubicMeters / toFactor;

        StringBuilder explanation_volume = new StringBuilder();
        explanation_volume.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_volume.append("Step 1: Convert ").append(fromUnit).append(" to cubic meters (m³)\n");
        explanation_volume.append("1 ").append(fromUnit).append(" = ").append(fromFactor).append(" m³\n");
        explanation_volume.append(value).append(" × ").append(fromFactor).append(" = ").append(valueInCubicMeters).append(" m³\n\n");

        explanation_volume.append("Step 2: Convert m³ to ").append(toUnit).append("\n");
        explanation_volume.append("1 ").append(toUnit).append(" = ").append(toFactor).append(" m³\n");
        explanation_volume.append(valueInCubicMeters).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_volume.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_volume.toString();
    }
}
