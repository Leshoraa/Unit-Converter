package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Speed {
    private static final Map<String, Double> toMetersPerSecond = new HashMap<>();

    static {
        toMetersPerSecond.put("km/h", 1.0 / 3.6);
        toMetersPerSecond.put("mph", 0.44704);
        toMetersPerSecond.put("m/s", 1.0);
        toMetersPerSecond.put("ft/s", 0.3048);
        toMetersPerSecond.put("kt", 0.514444);
        toMetersPerSecond.put("M", 343.0); // Mach (approximate at sea level)
        toMetersPerSecond.put("cm/s", 0.01);
        toMetersPerSecond.put("yd/s", 0.9144);
    }

    public static double convert(double value, String fromUnit, String toUnit) {
        fromUnit = normalize(fromUnit);
        toUnit = normalize(toUnit);

        if (!toMetersPerSecond.containsKey(fromUnit) || !toMetersPerSecond.containsKey(toUnit)) {
            throw new IllegalArgumentException("Unknown speed unit");
        }

        double valueInMps = value * toMetersPerSecond.get(fromUnit); // ke m/s
        return valueInMps / toMetersPerSecond.get(toUnit); // dari m/s ke target
    }

    private static String normalize(String unit) {
        // Hapus spasi tambahan dan buat lebih konsisten
        return unit.replace("(", "")
                .replace(")", "")
                .trim();
    }

    public static String getDetailedExplanation(double value, String fromUnit, String toUnit) {
        fromUnit = normalize(fromUnit);
        toUnit = normalize(toUnit);

        if (fromUnit.equals(toUnit)) {
            return "Both units are the same, so the value remains unchanged: " + value + " " + fromUnit;
        }

        if (!toMetersPerSecond.containsKey(fromUnit) || !toMetersPerSecond.containsKey(toUnit)) {
            return "Conversion not supported between " + fromUnit + " and " + toUnit;
        }

        double toMpsFactorFrom = toMetersPerSecond.get(fromUnit);
        double toMpsFactorTo = toMetersPerSecond.get(toUnit);
        double valueInMps = value * toMpsFactorFrom;
        double result = valueInMps / toMpsFactorTo;

        StringBuilder explanation = new StringBuilder();

        explanation.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");
        explanation.append("Step 1: Convert ").append(fromUnit).append(" to meters per second (m/s)\n");
        explanation.append("1 ").append(fromUnit).append(" = ").append(toMpsFactorFrom).append(" m/s\n");
        explanation.append(value).append(" × ").append(toMpsFactorFrom).append(" = ").append(valueInMps).append(" m/s\n\n");

        explanation.append("Step 2: Convert meters per second (m/s) to ").append(toUnit).append("\n");
        explanation.append("1 ").append(toUnit).append(" = ").append(toMpsFactorTo).append(" m/s\n");
        explanation.append(valueInMps).append(" ÷ ").append(toMpsFactorTo).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation.append("So, ").append(value).append(" ").append(fromUnit)
                .append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation.toString();
    }
}
