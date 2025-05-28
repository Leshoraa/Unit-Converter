package com.leshoraa.unitconverter;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leshoraa.unitconverter.adapter.GridAdapter;
import com.leshoraa.unitconverter.databinding.ActivityMainBinding;
import com.leshoraa.unitconverter.model.UnitData;
import com.leshoraa.unitconverter.model.UnitItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isTitleSmall = false;
    private boolean isSearchBesideTitle = false;
    private List<UnitItem> originalList;
    private GridAdapter adapter;
    private final ConstraintSet expandedSet = new ConstraintSet();
    private final ConstraintSet compactSet = new ConstraintSet();
    private static final float TITLE_SCALE_SMALL = 0.75f;
    private static final float TITLE_SCALE_LARGE = 1.0f;


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
        originalList = UnitData.getUnitData();
        adapter = new GridAdapter(new ArrayList<>(originalList), item -> {
            if (item.getType() == UnitItem.TYPE_UNIT) {
                int pos = originalList.indexOf(item);
                String title = null;
                for (int i = pos; i >= 0; i--) {
                    if (originalList.get(i).getType() == UnitItem.TYPE_TITLE) {
                        title = originalList.get(i).getUnitName();
                        break;
                    }
                }
                Intent intent = new Intent(MainActivity.this, ConvertActivity.class);
                intent.putExtra("unit_title", title);
                intent.putExtra("selected_unit", item.getUnitSymbol());
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
        binding.recyclerView.setAdapter(adapter);

        // Layout sets for search animation
        expandedSet.clone(binding.titleContainer);
        compactSet.clear(R.id.search, ConstraintSet.TOP);
        compactSet.clear(R.id.search, ConstraintSet.START);
        compactSet.clear(R.id.search, ConstraintSet.END);
        compactSet.clear(R.id.search, ConstraintSet.BOTTOM);
        compactSet.connect(R.id.search, ConstraintSet.START, R.id.tvTitle1, ConstraintSet.END, 8);
        compactSet.connect(R.id.search, ConstraintSet.TOP, R.id.tvTitle1, ConstraintSet.TOP);
        compactSet.connect(R.id.search, ConstraintSet.BOTTOM, R.id.tvTitle1, ConstraintSet.BOTTOM);
        compactSet.connect(R.id.search, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        compactSet.constrainWidth(R.id.search, 0);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) return;

                int scrollOffset = recyclerView.computeVerticalScrollOffset();

                // Title scale animation
                if (scrollOffset > 10 && !isTitleSmall) {
                    binding.tvTitle1.animate().cancel();
                    binding.tvTitle1.setPivotX(0);
                    binding.tvTitle1.setPivotY(binding.tvTitle1.getHeight() / 2f);
                    binding.tvTitle1.animate()
                            .scaleX(TITLE_SCALE_SMALL)
                            .scaleY(TITLE_SCALE_SMALL)
                            .setDuration(200)
                            .start();
                    isTitleSmall = true;
                } else if (scrollOffset <= 10 && isTitleSmall) {
                    binding.tvTitle1.animate().cancel();
                    binding.tvTitle1.setPivotX(0);
                    binding.tvTitle1.setPivotY(binding.tvTitle1.getHeight() / 2f);
                    binding.tvTitle1.animate()
                            .scaleX(TITLE_SCALE_LARGE)
                            .scaleY(TITLE_SCALE_LARGE)
                            .setDuration(200)
                            .start();
                    isTitleSmall = false;
                    binding.tvTitle1.setText("Unit Converter");
                }

                // Dynamic title update
                int firstVisible = layoutManager.findFirstVisibleItemPosition();
                if (firstVisible != RecyclerView.NO_POSITION) {
                    for (int i = firstVisible; i >= 0; i--) {
                        UnitItem item = adapter.getItem(i);
                        if (item.getType() == UnitItem.TYPE_TITLE) {
                            View view = layoutManager.findViewByPosition(i);
                            if (view == null || view.getBottom() <= 0) {
                                String newTitle = item.getUnitName();
                                if (!binding.tvTitle1.getText().toString().equals(newTitle)) {
                                    TransitionManager.beginDelayedTransition(binding.titleContainer);
                                    binding.tvTitle1.setText(newTitle);
                                }
                            }
                            break;
                        }
                    }
                }

                // Move search beside title
                if (scrollOffset > 10 && !isSearchBesideTitle) {
                    TransitionManager.beginDelayedTransition(binding.titleContainer);
                    compactSet.applyTo(binding.titleContainer);
                    isSearchBesideTitle = true;
                } else if (scrollOffset <= 10 && isSearchBesideTitle) {
                    TransitionManager.beginDelayedTransition(binding.titleContainer);
                    expandedSet.applyTo(binding.titleContainer);
                    isSearchBesideTitle = false;
                }
            }
        });

        // TextWatcher for search
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterUnitList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterUnitList(String query) {
        if (query == null || query.trim().isEmpty()) {
            adapter.updateList(new ArrayList<>(originalList));
            return;
        }

        query = query.toLowerCase();
        List<UnitItem> filtered = new ArrayList<>();
        Set<String> addedTitles = new HashSet<>();
        Set<UnitItem> addedUnits = new HashSet<>();

        // 1. Priority 1: Exact symbol match (e.g. "K" for Kelvin)
        List<UnitItem> exactSymbolMatches = new ArrayList<>();
        // 2. Priority 2: Exact name match (e.g. "kelvin")
        List<UnitItem> exactNameMatches = new ArrayList<>();
        // 3. Priority 3: Partial matches
        List<UnitItem> partialMatches = new ArrayList<>();

        // Categorize matches
        for (UnitItem item : originalList) {
            if (item.getType() == UnitItem.TYPE_UNIT) {
                String symbol = item.getUnitSymbol().toLowerCase();
                String name = item.getUnitName().toLowerCase();

                if (symbol.equals(query)) {
                    exactSymbolMatches.add(item);
                } else if (name.equals(query)) {
                    exactNameMatches.add(item);
                } else if (symbol.contains(query) || name.contains(query)) {
                    partialMatches.add(item);
                }
            }
        }

        // Process matches in priority order
        processMatches(filtered, exactSymbolMatches, addedTitles, addedUnits);
        processMatches(filtered, exactNameMatches, addedTitles, addedUnits);
        processMatches(filtered, partialMatches, addedTitles, addedUnits);

        adapter.updateList(filtered);
    }

    private void processMatches(List<UnitItem> filtered, List<UnitItem> matches,
                                Set<String> addedTitles, Set<UnitItem> addedUnits) {
        for (UnitItem match : matches) {
            String title = findTitleForUnit(match);
            if (title != null && !addedTitles.contains(title)) {
                filtered.add(new UnitItem(UnitItem.TYPE_TITLE, title));
                addedTitles.add(title);
            }

            if (!addedUnits.contains(match)) {
                filtered.add(match);
                addedUnits.add(match);
            }

            // Add all units from the same category
            boolean foundTitle = false;
            for (UnitItem item : originalList) {
                if (item.getType() == UnitItem.TYPE_TITLE && item.getUnitName().equals(title)) {
                    foundTitle = true;
                    continue;
                }
                if (foundTitle) {
                    if (item.getType() == UnitItem.TYPE_TITLE) break;
                    if (!addedUnits.contains(item)) {
                        filtered.add(item);
                        addedUnits.add(item);
                    }
                }
            }
        }
    }

    private String findTitleForUnit(UnitItem unitItem) {
        boolean foundUnit = false;
        String currentTitle = null;

        for (UnitItem item : originalList) {
            if (item == unitItem) {
                foundUnit = true;
                break;
            }
            if (item.getType() == UnitItem.TYPE_TITLE) {
                currentTitle = item.getUnitName();
            }
        }

        return foundUnit ? currentTitle : null;
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
