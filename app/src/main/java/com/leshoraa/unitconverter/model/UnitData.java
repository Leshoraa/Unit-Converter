package com.leshoraa.unitconverter.model;

import java.util.ArrayList;
import java.util.List;

public class UnitData {

    public static List<UnitItem> getUnitData() {
        List<UnitItem> items = new ArrayList<>();

        // Temperature
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Temperature"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Celsius (°C)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kelvin (K)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Fahrenheit (°F)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Rankine (°R)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Delisle (°D)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Réaumur (°Ré)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Rømer (°Rø)"));

        // Speed
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Speed"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilometers (km/h)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Miles (mph)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Meters (m/s)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Feet (ft/s)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Knots (kt)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Mach (M)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Centimeters (cm/s)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Yards (yd/s)"));

        // Distance / Length
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Distance / Length"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilometers (km)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Meters (m)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Centimeters (cm)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Millimeters (mm)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Micrometers (µm)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Nanometers (nm)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Miles (mi)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Yards (yd)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Feet (ft)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Inches (in)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Nautical miles (nmi)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Angstrom (Å)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Light years (ly)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Astronomical units (AU)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Parsecs (pc)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Furlongs (fur)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Rods (rd)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Chains (ch)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Links (li)"));

        // Mass / Weight
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Mass / Weight"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilograms (kg)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Grams (g)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Milligrams (mg)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Micrograms (µg)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Metric tons (t)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Pounds (lb)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Ounces (oz)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Stones (st)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Carats (ct)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Tons (short)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Tons (long)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Atomic mass unit (amu)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Grains (gr)"));

        // Time
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Time"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Seconds (s)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Milliseconds (ms)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Microseconds (µs)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Nanoseconds (ns)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Minutes (min)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Hours (h)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Days (d)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Weeks (wk)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Months (mo)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Years (yr)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Decades (dec)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Centuries (cen)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Fortnights (for)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Jiffies (J)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Planck time (tP)"));

        // Area
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Area"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Square kilometers (km²)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Square meters (m²)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Square centimeters (cm²)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Square millimeters (mm²)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Square miles (mi²)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Square yards (yd²)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Square feet (ft²)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Square inches (in²)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Hectares (ha)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Acres (ac)"));

        // Volume
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Volume"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Liters (L)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Milliliters (mL)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Cubic meters (m³)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Cubic centimeters (cm³)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Cubic millimeters (mm³)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Cubic inches (in³)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Cubic feet (ft³)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Cubic yards (yd³)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Gallons (gal) (US/UK)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Quarts (qt)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Pints (pt)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Fluid ounces (fl oz)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Barrels (bbl)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Teaspoons (tsp)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Tablespoons (tbsp)"));

        // Pressure
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Pressure"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Pascals (Pa)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilopascals (kPa)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Megapascals (MPa)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Bars (bar)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Atmospheres (atm)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Torrs (Torr)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Pounds per square inch (psi)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Millimeters of mercury (mmHg)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Inches of mercury (inHg)"));

        // Energy
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Energy"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Joules (J)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilojoules (kJ)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Calories (cal)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilocalories (kcal)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Electron volts (eV)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "British thermal units (BTU)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Foot-pounds (ft·lb)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilowatt-hours (kWh)"));

        // Power
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Power"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Watts (W)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilowatts (kW)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Horsepower (hp)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Megawatts (MW)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Gigawatts (GW)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "BTU (BTU/h)"));

        // Radiation
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Radiation"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Becquerels (Bq)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Curies (Ci)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Sieverts (Sv)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Grays (Gy)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Roentgens (R)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Rads (rad)"));

        // Frequency
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Frequency"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Hertz (Hz)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilohertz (kHz)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Megahertz (MHz)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Gigahertz (GHz)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Terahertz (THz)"));

        // Data Storage
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Data Storage"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Bits (b)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Bytes (B)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilobytes (KB)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Megabytes (MB)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Gigabytes (GB)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Terabytes (TB)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Petabytes (PB)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Exabytes (EB)"));

        // Fuel Economy
        items.add(new UnitItem(UnitItem.TYPE_TITLE, "Fuel Economy"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Liters per 100 kilometers (L/100km)"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Miles per gallon (mpg (US) )"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Miles per gallon (mpg (UK) )"));
        items.add(new UnitItem(UnitItem.TYPE_UNIT, "Kilometers per liter (km/L)"));

        return items;
    }
}