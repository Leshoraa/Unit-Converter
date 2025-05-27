package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Distance {

    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("km", 1000.0); // 1 km = 1000 m
        conversionFactors.put("m", 1.0); // 1 m = 1 m
        conversionFactors.put("cm", 0.01); // 1 cm = 0.01 m
        conversionFactors.put("mm", 0.001); // 1 mm = 0.001 m
        conversionFactors.put("µm", 0.000001); // 1 µm = 0.000001 m
        conversionFactors.put("nm", 0.000000001); // 1 nm = 0.000000001 m
        conversionFactors.put("mi", 1609.34); // 1 mi = 1609.34 m
        conversionFactors.put("yd", 0.9144); // 1 yd = 0.9144 m
        conversionFactors.put("ft", 0.3048); // 1 ft = 0.3048 m
        conversionFactors.put("in", 0.0254); // 1 in = 0.0254 m
        conversionFactors.put("nmi", 1852.0); // 1 nmi = 1852 m
        conversionFactors.put("Å", 1e-10); // 1 Å = 1e-10 m
        conversionFactors.put("ly", 9.461e15); // 1 ly = 9.461e15 m
        conversionFactors.put("AU", 1.496e11); // 1 AU = 1.496e11 m
        conversionFactors.put("pc", 3.086e16); // 1 pc = 3.086e16 m
        conversionFactors.put("fur", 201.168); // 1 fur = 201.168 m
        conversionFactors.put("rd", 5.0292); // 1 rd = 5.0292 m
        conversionFactors.put("ch", 20.1168); // 1 ch = 20.1168 m
        conversionFactors.put("li", 0.201168); // 1 li = 0.201168 m
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

        double valueInMeters = value * conversionFactors.get(fromUnit);
        return valueInMeters / conversionFactors.get(toUnit);
    }

    private static String extractSymbol(String unit) {
        // If the unit is in format "Kilometers (km)", extract "km"
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
        double valueInMeters = value * fromFactor;
        double result = valueInMeters / toFactor;

        StringBuilder explanation_distance = new StringBuilder();

        explanation_distance.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_distance.append("Step 1: Convert ").append(fromUnit).append(" to meters (m)\n");
        explanation_distance.append("1 ").append(fromUnit).append(" = ").append(fromFactor).append(" m\n");
        explanation_distance.append(value).append(" × ").append(fromFactor).append(" = ").append(valueInMeters).append(" m\n\n");

        explanation_distance.append("Step 2: Convert meters (m) to ").append(toUnit).append("\n");
        explanation_distance.append("1 ").append(toUnit).append(" = ").append(toFactor).append(" m\n");
        explanation_distance.append(valueInMeters).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_distance.append("So, ").append(value).append(" ").append(fromUnit)
                .append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_distance.toString();
    }
}