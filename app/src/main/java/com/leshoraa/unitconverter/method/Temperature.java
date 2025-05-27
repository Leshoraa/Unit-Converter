package com.leshoraa.unitconverter.method;

public class Temperature {

    public static double convert(double value, String fromUnit, String toUnit) {
        // First convert to Celsius (as intermediate step)
        double celsius = toCelsius(value, fromUnit);

        // Then convert from Celsius to target unit
        return fromCelsius(celsius, toUnit);
    }

    private static double toCelsius(double value, String fromUnit) {
        switch (fromUnit) {
            case "°C":
                return value;
            case "K":
                return value - 273.15;
            case "°F":
                return (value - 32) * 5/9;
            case "°R":
                return (value - 491.67) * 5/9;
            case "°D":
                return 100 - value * 2/3;
            case "°Ré":
                return value * 5/4;
            case "°Rø":
                return (value - 7.5) * 40/21;
            default:
                throw new IllegalArgumentException("Unknown unit: " + fromUnit);
        }
    }

    private static double fromCelsius(double celsius, String toUnit) {
        switch (toUnit) {
            case "°C":
                return celsius;
            case "K":
                return celsius + 273.15;
            case "°F":
                return celsius * 9/5 + 32;
            case "°R":
                return (celsius + 273.15) * 9/5;
            case "°D":
                return (100 - celsius) * 3/2;
            case "°Ré":
                return celsius * 4/5;
            case "°Rø":
                return celsius * 21/40 + 7.5;
            default:
                throw new IllegalArgumentException("Unknown unit: " + toUnit);
        }
    }

    public static String getDetailedExplanation(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return "Both units are the same, so the value remains unchanged: " + value + " " + fromUnit;
        }

        StringBuilder explanation = new StringBuilder();
        double celsius = toCelsius(value, fromUnit);
        double result = fromCelsius(celsius, toUnit);

        explanation.append("If the temperature is ").append(value).append(" ").append(fromUnit).append(", then:\n\n");

        switch (fromUnit + " to " + toUnit) {
            case "°C to °F":
                explanation.append("F = (").append(value).append(" × 9/5) + 32\n")
                        .append("F = ").append(value * 9 / 5).append(" + 32\n")
                        .append("F = ").append(result).append(" °F\n\n");
                break;
            case "°F to °C":
                explanation.append("C = (").append(value).append(" - 32) × 5/9\n")
                        .append("C = ").append((value - 32)).append(" × 5/9\n")
                        .append("C = ").append(celsius).append(" °C\n\n");
                break;
            case "K to °C":
                explanation.append("C = ").append(value).append(" - 273.15\n")
                        .append("C = ").append(celsius).append(" °C\n\n");
                break;
            case "°C to K":
                explanation.append("K = ").append(value).append(" + 273.15\n")
                        .append("K = ").append(result).append(" K\n\n");
                break;
            case "°R to °C":
                explanation.append("C = (").append(value).append(" - 491.67) × 5/9\n")
                        .append("C = ").append((value - 491.67)).append(" × 5/9\n")
                        .append("C = ").append(celsius).append(" °C\n\n");
                break;
            case "°C to °R":
                explanation.append("R = (").append(value).append(" + 273.15) × 9/5\n")
                        .append("R = ").append(value + 273.15).append(" × 9/5\n")
                        .append("R = ").append(result).append(" °R\n\n");
                break;
            case "°D to °C":
                explanation.append("C = 100 - (").append(value).append(" × 2/3)\n")
                        .append("C = 100 - ").append(value * 2 / 3).append("\n")
                        .append("C = ").append(celsius).append(" °C\n\n");
                break;
            case "°C to °D":
                explanation.append("D = (100 - ").append(value).append(") × 3/2\n")
                        .append("D = ").append((100 - value)).append(" × 3/2\n")
                        .append("D = ").append(result).append(" °D\n\n");
                break;
            case "°Ré to °C":
                explanation.append("C = ").append(value).append(" × 5/4\n")
                        .append("C = ").append(celsius).append(" °C\n\n");
                break;
            case "°C to °Ré":
                explanation.append("Ré = ").append(value).append(" × 4/5\n")
                        .append("Ré = ").append(result).append(" °Ré\n\n");
                break;
            case "°Rø to °C":
                explanation.append("C = (").append(value).append(" - 7.5) × 40/21\n")
                        .append("C = ").append(value - 7.5).append(" × 40/21\n")
                        .append("C = ").append(celsius).append(" °C\n\n");
                break;
            case "°C to °Rø":
                explanation.append("Rø = ").append(value).append(" × 21/40 + 7.5\n")
                        .append("Rø = ").append(value * 21 / 40).append(" + 7.5\n")
                        .append("Rø = ").append(result).append(" °Rø\n\n");
                break;
            default:
                explanation.append("Step 1: Convert to Celsius: ").append(celsius).append(" °C\n")
                        .append("Step 2: Convert from Celsius to ").append(toUnit).append(": ").append(result).append(" ").append(toUnit).append("\n\n");
                break;
        }

        explanation.append("So, ").append(value).append(" ").append(fromUnit)
                .append(" is equal to ").append(result).append(" ").append(toUnit).append(".");

        return explanation.toString();
    }
}