package com.leshoraa.unitconverter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leshoraa.unitconverter.adapter.GridAdapter;
import com.leshoraa.unitconverter.databinding.ActivityMainBinding;
import com.leshoraa.unitconverter.model.UnitData;
import com.leshoraa.unitconverter.model.UnitItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final float TITLE_SCALE_SMALL = 0.7f;
    private static final float TITLE_SCALE_LARGE = 1.0f;
    private boolean isTitleSmall = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        hideSystemUI();
        setupDesign();
    }

    private void setupDesign() {
        List<UnitItem> unitItems = UnitData.getUnitData();
        GridAdapter adapter = new GridAdapter(unitItems, item -> {
            if (item.getType() == UnitItem.TYPE_UNIT) {
                int pos = unitItems.indexOf(item);
                String title = null;
                for (int i = pos; i >= 0; i--) {
                    if (unitItems.get(i).getType() == UnitItem.TYPE_TITLE) {
                        title = unitItems.get(i).getUnitName();
                        break;
                    }
                }
                Intent intent = new Intent(MainActivity.this, ConvertActivity.class);
                intent.putExtra("unit_title", title);
                intent.putExtra("selected_unit", item.getUnitSymbol());
                //intent.putExtra("unit_name", item.getUnitName());
                //intent.putExtra("unit_symbol", item.getUnitSymbol());
                startActivity(intent);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                UnitItem item = adapter.getItem(position);
                return item.getType() == UnitItem.TYPE_TITLE ? 3 : 1;
            }
        });
        binding.recyclerView.setLayoutManager(gridLayoutManager);

        binding.recyclerView.setLayoutManager(gridLayoutManager);

        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) return;

                int scrollOffset = recyclerView.computeVerticalScrollOffset();

                if (scrollOffset > 10 && !isTitleSmall) {
                    binding.tvTitle1.animate().cancel();
                    binding.tvTitle1.post(() -> {
                        binding.tvTitle1.setPivotX(0);
                        binding.tvTitle1.setPivotY(binding.tvTitle1.getHeight() / 2f);
                    });
                    binding.tvTitle1.animate()
                            .scaleX(TITLE_SCALE_SMALL)
                            .scaleY(TITLE_SCALE_SMALL)
                            .setDuration(200)
                            .start();
                    isTitleSmall = true;
                } else if (scrollOffset <= 10 && isTitleSmall) {
                    binding.tvTitle1.animate().cancel();
                    binding.tvTitle1.post(() -> {
                        binding.tvTitle1.setPivotX(0);
                        binding.tvTitle1.setPivotY(binding.tvTitle1.getHeight() / 2f);
                    });
                    binding.tvTitle1.animate()
                            .scaleX(TITLE_SCALE_LARGE)
                            .scaleY(TITLE_SCALE_LARGE)
                            .setDuration(200)
                            .start();
                    isTitleSmall = false;
                    binding.tvTitle1.setText("Unit Converter");
                }

                int firstVisible = layoutManager.findFirstVisibleItemPosition();
                if (firstVisible == RecyclerView.NO_POSITION) return;

                String newTitle = null;

                for (int i = firstVisible; i >= 0; i--) {
                    UnitItem item = adapter.getItem(i);
                    if (item.getType() == UnitItem.TYPE_TITLE) {
                        View view = layoutManager.findViewByPosition(i);
                        if (view == null || view.getBottom() <= 0) {
                            newTitle = item.getUnitName();
                        }
                        break;
                    }
                }

                if (newTitle != null && !binding.tvTitle1.getText().toString().equals(newTitle)) {
                    binding.tvTitle1.setText(newTitle);
                }
            }
        });
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}
