package com.leshoraa.unitconverter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.leshoraa.unitconverter.databinding.ActivityConvertBinding;
import com.leshoraa.unitconverter.method.Area;
import com.leshoraa.unitconverter.method.DataStorage;
import com.leshoraa.unitconverter.method.Distance;
import com.leshoraa.unitconverter.method.Energy;
import com.leshoraa.unitconverter.method.Frequency;
import com.leshoraa.unitconverter.method.FuelEconomy;
import com.leshoraa.unitconverter.method.Mass;
import com.leshoraa.unitconverter.method.Power;
import com.leshoraa.unitconverter.method.Pressure;
import com.leshoraa.unitconverter.method.Radiation;
import com.leshoraa.unitconverter.method.Speed;
import com.leshoraa.unitconverter.method.Temperature;
import com.leshoraa.unitconverter.method.Time;
import com.leshoraa.unitconverter.method.Volume;
import com.leshoraa.unitconverter.model.UnitData;
import com.leshoraa.unitconverter.model.UnitItem;

import java.util.ArrayList;
import java.util.List;

public class ConvertActivity extends AppCompatActivity {

    private ActivityConvertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityConvertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        actionActivity();
    }

    private void actionActivity() {
        String unitTitle = getIntent().getStringExtra("unit_title");
        String selectedUnit = getIntent().getStringExtra("selected_unit");

        if (unitTitle != null) {
            binding.tvTitleConvert.setText(unitTitle);
            populateUnitDropdown(unitTitle, selectedUnit);
            setupConversionListeners(unitTitle);
        }

        hideSystemUI();
        setupCopyButton();
    }

    private void setupConversionListeners(String unitTitle) {
        binding.inputA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.inputA.isFocused()) {
                    convert(false);
                }
            }
        });

        binding.inputB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.inputB.isFocused()) {
                    convert(true);
                }
            }
        });

        binding.unitDropdown1.setOnItemClickListener((parent, view, position, id) -> {
            convert(false);
        });

        binding.unitDropdown2.setOnItemClickListener((parent, view, position, id) -> {
            convert(false);
        });
    }

    private void convert(boolean reverse) {
        try {
            String fromUnit = binding.unitDropdown1.getText().toString();
            String toUnit = binding.unitDropdown2.getText().toString();

            if (fromUnit.isEmpty() || toUnit.isEmpty()) {
                return;
            }

            double inputValue;
            double result;

            if (!reverse) {
                String inputStr = binding.inputA.getText().toString();
                if (inputStr.isEmpty()) {
                    binding.inputB.setText("");
                    return;
                }
                inputValue = Double.parseDouble(inputStr);
                switch (binding.tvTitleConvert.getText().toString()) {
                    case "Temperature":
                        result = Temperature.convert(inputValue, fromUnit, toUnit);
                        String explanation_temperature = Temperature.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_temperature);
                        break;
                    case "Speed":
                        result = Speed.convert(inputValue, fromUnit, toUnit);
                        String explanation_speed = Speed.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_speed);
                        break;
                    case "Distance / Length":
                        result = Distance.convert(inputValue, fromUnit, toUnit);
                        String explanation_distance = Distance.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_distance);
                        break;
                    case "Mass / Weight":
                        result = Mass.convert(inputValue, fromUnit, toUnit);
                        String explanation_Mass = Mass.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Mass);
                        break;
                    case "Time":
                        result = Time.convert(inputValue, fromUnit, toUnit);
                        String explanation_Time = Time.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Time);
                        break;
                    case "Area":
                        result = Area.convert(inputValue, fromUnit, toUnit);
                        String explanation_Area = Area.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Area);
                        break;
                    case "Volume":
                        result = Volume.convert(inputValue, fromUnit, toUnit);
                        String explanation_Volume = Volume.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Volume);
                        break;
                    case "Pressure":
                        result = Pressure.convert(inputValue, fromUnit, toUnit);
                        String explanation_Pressure = Pressure.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Pressure);
                        break;
                    case "Energy":
                        result = Energy.convert(inputValue, fromUnit, toUnit);
                        String explanation_Energy = Energy.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Energy);
                        break;
                    case "Power":
                        result = Power.convert(inputValue, fromUnit, toUnit);
                        String explanation_Power = Power.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Power);
                        break;
                    case "Radiation":
                        result = Radiation.convert(inputValue, fromUnit, toUnit);
                        String explanation_Radiation = Radiation.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Radiation);
                        break;
                    case "Frequency":
                        result = Frequency.convert(inputValue, fromUnit, toUnit);
                        String explanation_Frequency = Frequency.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Frequency);
                        break;
                    case "Data Storage":
                        result = DataStorage.convert(inputValue, fromUnit, toUnit);
                        String explanation_DataStorage = DataStorage.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_DataStorage);
                        break;
                    case "Fuel Economy":
                        result = FuelEconomy.convert(inputValue, fromUnit, toUnit);
                        String explanation_FuelEconomy = FuelEconomy.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_FuelEconomy);
                        break;
                    default:
                        result = inputValue;
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }
                binding.inputB.setText(String.valueOf(result));
            } else {
                String inputStr = binding.inputB.getText().toString();
                if (inputStr.isEmpty()) {
                    binding.inputA.setText("");
                    return;
                }
                inputValue = Double.parseDouble(inputStr);
                switch (binding.tvTitleConvert.getText().toString()) {
                    case "Temperature":
                        result = Temperature.convert(inputValue, fromUnit, toUnit);
                        String explanation_temperature = Temperature.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_temperature);
                        break;
                    case "Speed":
                        result = Speed.convert(inputValue, fromUnit, toUnit);
                        String explanation_speed = Speed.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_speed);
                        break;
                    case "Distance / Length":
                        result = Distance.convert(inputValue, fromUnit, toUnit);
                        String explanation_distance = Distance.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_distance);
                        break;
                    case "Mass / Weight":
                        result = Mass.convert(inputValue, fromUnit, toUnit);
                        String explanation_Mass = Mass.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Mass);
                        break;
                    case "Time":
                        result = Time.convert(inputValue, fromUnit, toUnit);
                        String explanation_Time = Time.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Time);
                        break;
                    case "Area":
                        result = Area.convert(inputValue, fromUnit, toUnit);
                        String explanation_Area = Area.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Area);
                        break;
                    case "Volume":
                        result = Volume.convert(inputValue, fromUnit, toUnit);
                        String explanation_Volume = Volume.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Volume);
                        break;
                    case "Pressure":
                        result = Pressure.convert(inputValue, fromUnit, toUnit);
                        String explanation_Pressure = Pressure.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Pressure);
                        break;
                    case "Energy":
                        result = Energy.convert(inputValue, fromUnit, toUnit);
                        String explanation_Energy = Energy.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Energy);
                        break;
                    case "Power":
                        result = Power.convert(inputValue, fromUnit, toUnit);
                        String explanation_Power = Power.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Power);
                        break;
                    case "Radiation":
                        result = Radiation.convert(inputValue, fromUnit, toUnit);
                        String explanation_Radiation = Radiation.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Radiation);
                        break;
                    case "Frequency":
                        result = Frequency.convert(inputValue, fromUnit, toUnit);
                        String explanation_Frequency = Frequency.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_Frequency);
                        break;
                    case "Data Storage":
                        result = DataStorage.convert(inputValue, fromUnit, toUnit);
                        String explanation_DataStorage = DataStorage.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_DataStorage);
                        break;
                    case "Fuel Economy":
                        result = FuelEconomy.convert(inputValue, fromUnit, toUnit);
                        String explanation_FuelEconomy = FuelEconomy.getDetailedExplanation(inputValue, fromUnit, toUnit);
                        binding.tvMethod.setText(explanation_FuelEconomy);
                        break;
                    default:
                        result = inputValue;
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }
                binding.inputA.setText(String.valueOf(result));
            }
        } catch (NumberFormatException e) {
            // Handle invalid number input
        } catch (IllegalArgumentException e) {
            // Handle unknown unit
        }
    }

    private void setupCopyButton() {
        binding.copy.setOnClickListener(v -> {
            String textToCopy = binding.tvMethod.getText().toString();
            if (!textToCopy.isEmpty()) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Converted Method", textToCopy);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateUnitDropdown(String unitTitle, String selectedUnit) {
        List<UnitItem> unitItems = UnitData.getUnitData();
        List<String> unitList = new ArrayList<>();

        boolean foundTitle = false;
        for (UnitItem item : unitItems) {
            if (item.getType() == UnitItem.TYPE_TITLE && item.getUnitName().equals(unitTitle)) {
                foundTitle = true;
                continue;
            }
            if (foundTitle && item.getType() == UnitItem.TYPE_UNIT) {
                unitList.add(item.getUnitSymbol());
            }
            if (foundTitle && item.getType() == UnitItem.TYPE_TITLE) {
                break;
            }
        }

        AutoCompleteTextView unitDropdown1 = binding.unitDropdown1;
        AutoCompleteTextView unitDropdown2 = binding.unitDropdown2;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                unitList
        );
        unitDropdown1.setAdapter(adapter);
        unitDropdown2.setAdapter(adapter);

        // Set selected unit ke dropdown1
        if (selectedUnit != null && unitList.contains(selectedUnit)) {
            unitDropdown1.setText(selectedUnit, false);
            unitDropdown2.setText(getNextUnit(selectedUnit, unitList), false);
        }

        unitDropdown1.setOnItemClickListener((parent, view, position, id) -> {
            String selected = (String) parent.getItemAtPosition(position);
            unitDropdown2.setText(getNextUnit(selected, unitList), false);
        });
    }

    private String getNextUnit(String currentUnit, List<String> unitList) {
        int index = unitList.indexOf(currentUnit);
        if (index >= 0 && index + 1 < unitList.size()) {
            return unitList.get(index + 1);
        } else if (!unitList.isEmpty()) {
            return unitList.get(0); // fallback ke awal kalau gak ada next
        }
        return "";
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}