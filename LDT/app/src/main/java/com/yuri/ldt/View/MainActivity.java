package com.yuri.ldt.View;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.yuri.ldt.Controller.Adapters.CardAdapter;
import com.yuri.ldt.Controller.MainActivityController;
import com.yuri.ldt.Model.CardModel;
import com.yuri.ldt.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RV;
    private MaterialToolbar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pegarDados();

        MainActivityController cards = new MainActivityController(this);
        List<CardModel> cardModelList = cards.criarCardsExemplares();

        CardAdapter adapter = new CardAdapter(this, cardModelList);
        RV.setAdapter(adapter);
        RV.setLayoutManager(new LinearLayoutManager(this));

        topBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.profile) {
                    MainActivityController.logoff(MainActivity.this);
                    return true;
                }
                return false;
            }
        });
    }

    public void pegarDados(){
        RV = findViewById(R.id.RV);
        topBar = findViewById(R.id.topApp);
    }

}