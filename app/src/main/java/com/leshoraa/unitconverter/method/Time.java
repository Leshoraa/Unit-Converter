package com.leshoraa.unitconverter.method;

import java.util.HashMap;
import java.util.Map;

public class Time {

    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        conversionFactors.put("s", 1.0); // Seconds
        conversionFactors.put("ms", 0.001); // Milliseconds
        conversionFactors.put("µs", 1e-6); // Microseconds
        conversionFactors.put("ns", 1e-9); // Nanoseconds
        conversionFactors.put("min", 60.0); // Minutes
        conversionFactors.put("h", 3600.0); // Hours
        conversionFactors.put("d", 86400.0); // Days
        conversionFactors.put("wk", 604800.0); // Weeks
        conversionFactors.put("mo", 2629800.0); // Months (approx.)
        conversionFactors.put("yr", 31557600.0); // Years (approx.)
        conversionFactors.put("dec", 315576000.0); // Decades (approx.)
        conversionFactors.put("cen", 3155760000.0); // Centuries (approx.)
        conversionFactors.put("for", 1209600.0); // Fortnights
        conversionFactors.put("J", 0.1); // Jiffies (approx. 1/10 of a second)
        conversionFactors.put("tP", 5.39106e-44); // Planck time
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

        double valueInSeconds = value * conversionFactors.get(fromUnit); // Convert to seconds
        return valueInSeconds / conversionFactors.get(toUnit); // Convert to target unit
    }

    private static String extractSymbol(String unit) {
        // If the unit is in format "Seconds (s)", extract "s"
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
        double valueInSeconds = value * fromFactor;
        double result = valueInSeconds / toFactor;

        StringBuilder explanation_time = new StringBuilder();
        explanation_time.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        explanation_time.append("Step 1: Convert ").append(fromUnit).append(" to seconds (s)\n");
        explanation_time.append("1 ").append(fromUnit).append(" = ").append(fromFactor).append(" s\n");
        explanation_time.append(value).append(" × ").append(fromFactor).append(" = ").append(valueInSeconds).append(" s\n\n");

        explanation_time.append("Step 2: Convert seconds (s) to ").append(toUnit).append("\n");
        explanation_time.append("1 ").append(toUnit).append(" = ").append(toFactor).append(" s\n");
        explanation_time.append(valueInSeconds).append(" ÷ ").append(toFactor).append(" = ").append(result).append(" ").append(toUnit).append("\n\n");

        explanation_time.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation_time.toString();
    }
}