package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Pressure {

    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("Pa", 1.0);
        conversionFactors.put("kPa", 1000.0);
        conversionFactors.put("MPa", 1_000_000.0);
        conversionFactors.put("bar", 100_000.0);
        conversionFactors.put("atm", 101_325.0);
        conversionFactors.put("Torr", 133.322);
        conversionFactors.put("psi", 6894.76);
        conversionFactors.put("mmHg", 133.322);
        conversionFactors.put("inHg", 3386.39);
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

        double valueInPascals = value * conversionFactors.get(fromUnit);
        return valueInPascals / conversionFactors.get(toUnit);
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
        double valueInPascals = value * fromFactor;
        double result = valueInPascals / toFactor;

        StringBuilder explanation_pressure = new StringBuilder();
        explanation_pressure.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_pressure.append("Step 1: Convert ").append(fromUnit).append(" to Pascals (Pa)\n");
        explanation_pressure.append("1 ").append(fromUnit).append(" = ").append(fromFactor).append(" Pa\n");
        explanation_pressure.append(value).append(" × ").append(fromFactor).append(" = ").append(valueInPascals).append(" Pa\n\n");

        explanation_pressure.append("Step 2: Convert Pascals to ").append(toUnit).append("\n");
        explanation_pressure.append("1 ").append(toUnit).append(" = ").append(toFactor).append(" Pa\n");
        explanation_pressure.append(valueInPascals).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_pressure.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_pressure.toString();
    }
}
