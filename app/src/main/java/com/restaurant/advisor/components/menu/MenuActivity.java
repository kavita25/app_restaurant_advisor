package com.restaurant.advisor.components.menu;

import android.os.Bundle;

import com.restaurant.advisor.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    String mMenus = "Indian";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.VERTICAL,
                false));
        mMenus = getIntent().getStringExtra("mealType");

        final List<MenuDataModel> people = getFoodMenu();

        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),
                        true,
                        getSectionCallback(people));
        recyclerView.addItemDecoration(sectionItemDecoration);

        recyclerView.setAdapter(new RestaurantMenuAdapter(getLayoutInflater(),
                people,
                R.layout.recycler_row));

    }

    private List<MenuDataModel> getFoodMenu() {
        FoodMenuRepo personRepo = new FoodMenuRepo();
        return personRepo.getFoodMenus(mMenus);
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<MenuDataModel> people) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return people.get(position).positionInSection == 0;
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return people.get(position).section;
            }
        };
    }

}
