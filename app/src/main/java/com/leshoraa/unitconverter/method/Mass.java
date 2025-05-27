package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Mass {

    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("kg", 1.0); // 1 kg = 1 kg
        conversionFactors.put("g", 0.001); // 1 g = 0.001 kg
        conversionFactors.put("mg", 0.000001); // 1 mg = 0.000001 kg
        conversionFactors.put("µg", 0.000000001); // 1 µg = 0.000000001 kg
        conversionFactors.put("t", 1000.0); // 1 t = 1000 kg
        conversionFactors.put("lb", 0.453592); // 1 lb = 0.453592 kg
        conversionFactors.put("oz", 0.0283495); // 1 oz = 0.0283495 kg
        conversionFactors.put("st", 6.35029); // 1 st = 6.35029 kg
        conversionFactors.put("ct", 0.0002); // 1 ct = 0.0002 kg
        conversionFactors.put("Tons (short)", 907.185); // 1 short ton = 907.185 kg
        conversionFactors.put("Tons (long)", 1016.05); // 1 long ton = 1016.05 kg
        conversionFactors.put("amu", 1.66054e-27); // 1 amu = 1.66054e-27 kg
        conversionFactors.put("gr", 0.00006479891); // 1 gr = 0.00006479891 kg
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

        double valueInKilograms = value * conversionFactors.get(fromUnit); // Convert to kg
        return valueInKilograms / conversionFactors.get(toUnit); // Convert to target unit
    }

    private static String extractSymbol(String unit) {
        // If the unit is in format "Kilograms (kg)", extract "kg"
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
        double valueInKg = value * fromFactor;
        double result = valueInKg / toFactor;

        StringBuilder explanation_mass = new StringBuilder();

        explanation_mass.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_mass.append("Step 1: Convert ").append(fromUnit).append(" to kilograms (kg)\n");
        explanation_mass.append("1 ").append(fromUnit).append(" = ").append(fromFactor).append(" kg\n");
        explanation_mass.append(value).append(" × ").append(fromFactor).append(" = ").append(valueInKg).append(" kg\n\n");

        explanation_mass.append("Step 2: Convert kilograms (kg) to ").append(toUnit).append("\n");
        explanation_mass.append("1 ").append(toUnit).append(" = ").append(toFactor).append(" kg\n");
        explanation_mass.append(valueInKg).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_mass.append("So, ").append(value).append(" ").append(fromUnit)
                .append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_mass.toString();
    }
}