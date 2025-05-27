package com.leshoraa.unitconverter.method;

public class FuelEconomy {

    public static double convert(double value, String fromUnit, String toUnit) {
        fromUnit = extractSymbol(fromUnit);
        toUnit = extractSymbol(toUnit);

        // Normalize to L/100km
        double lPer100km;
        switch (fromUnit) {
            case "L/100km":
                lPer100km = value;
                break;
            case "mpg":
                lPer100km = 235.214583 / value;
                break;
            case "km/L":
                lPer100km = 100 / value;
                break;
            case "mpg (UK)":
                lPer100km = 282.480936 / value;
                break;
            default:
                throw new IllegalArgumentException("Unknown from unit: " + fromUnit);
        }

        // Convert from L/100km to target
        switch (toUnit) {
            case "L/100km":
                return lPer100km;
            case "mpg":
                return 235.214583 / lPer100km;
            case "km/L":
                return 100 / lPer100km;
            case "mpg (UK)":
                return 282.480936 / lPer100km;
            default:
                throw new IllegalArgumentException("Unknown to unit: " + toUnit);
        }
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

        double lPer100km;
        StringBuilder explanation_fuel = new StringBuilder();
        explanation_fuel.append("To convert ").append(value).append(" ").append(fromUnit).append(" to ").append(toUnit).append(":\n\n");

        switch (fromUnit) {
            case "L/100km":
                lPer100km = value;
                explanation_fuel.append("Step 1: Already in L/100km = ").append(lPer100km).append("\n\n");
                break;
            case "mpg":
                lPer100km = 235.214583 / value;
                explanation_fuel.append("Step 1: Convert mpg to L/100km using 235.214583 ÷ mpg\n");
                explanation_fuel.append("235.214583 ÷ ").append(value).append(" = ").append(lPer100km).append("\n\n");
                break;
            case "km/L":
                lPer100km = 100 / value;
                explanation_fuel.append("Step 1: Convert km/L to L/100km using 100 ÷ km/L\n");
                explanation_fuel.append("100 ÷ ").append(value).append(" = ").append(lPer100km).append("\n\n");
                break;
            case "mpg (UK)":
                lPer100km = 282.480936 / value;
                explanation_fuel.append("Step 1: Convert mpg (UK) to L/100km using 282.480936 ÷ mpg\n");
                explanation_fuel.append("282.480936 ÷ ").append(value).append(" = ").append(lPer100km).append("\n\n");
                break;
            default:
                return "Unknown from unit: " + fromUnit;
        }

        double result;
        switch (toUnit) {
            case "L/100km":
                result = lPer100km;
                explanation_fuel.append("Step 2: Already in L/100km.\n\n");
                break;
            case "mpg":
                result = 235.214583 / lPer100km;
                explanation_fuel.append("Step 2: Convert L/100km to mpg using 235.214583 ÷ L/100km\n");
                explanation_fuel.append("235.214583 ÷ ").append(lPer100km).append(" = ").append(result).append("\n\n");
                break;
            case "km/L":
                result = 100 / lPer100km;
                explanation_fuel.append("Step 2: Convert L/100km to km/L using 100 ÷ L/100km\n");
                explanation_fuel.append("100 ÷ ").append(lPer100km).append(" = ").append(result).append("\n\n");
                break;
            case "mpg (UK)":
                result = 282.480936 / lPer100km;
                explanation_fuel.append("Step 2: Convert L/100km to mpg (UK) using 282.480936 ÷ L/100km\n");
                explanation_fuel.append("282.480936 ÷ ").append(lPer100km).append(" = ").append(result).append("\n\n");
                break;
            default:
                return "Unknown to unit: " + toUnit;
        }

        explanation_fuel.append("So, ").append(value).append(" ").append(fromUnit).append(" is equal to ").append(result).append(" ").append(toUnit).append(".");
        return explanation_fuel.toString();
    }
}
