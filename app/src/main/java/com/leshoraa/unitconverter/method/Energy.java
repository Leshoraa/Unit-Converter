package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Energy {
    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("J", 1.0);
        conversionFactors.put("kJ", 1000.0);
        conversionFactors.put("cal", 4.184);
        conversionFactors.put("kcal", 4184.0);
        conversionFactors.put("eV", 1.60218e-19);
        conversionFactors.put("BTU", 1055.06);
        conversionFactors.put("ft·lb", 1.35582);
        conversionFactors.put("kWh", 3.6e6);
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

        StringBuilder explanation_energy = new StringBuilder();
        explanation_energy.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_energy.append("Step 1: Multiply by the conversion factor of ").append(fromUnit).append("\n");
        explanation_energy.append(value).append(" × ").append(fromFactor).append(" = ").append(value * fromFactor).append("\n\n");

        explanation_energy.append("Step 2: Divide by the conversion factor of ").append(toUnit).append("\n");
        explanation_energy.append((value * fromFactor)).append(" ÷ ").append(toFactor).append(" = ").append(result).append("\n\n");

        explanation_energy.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_energy.toString();
    }
}
