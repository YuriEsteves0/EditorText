package com.yuri.ldt.View;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuri.ldt.Controller.Adapters.CardAdapter;
import com.yuri.ldt.Controller.MainActivityController;
import com.yuri.ldt.Model.CardModel;
import com.yuri.ldt.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RV;
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

        MainActivityController cards = new MainActivityController();
        List<CardModel> cardModelList = cards.criarCardsExemplares();

        CardAdapter adapter = new CardAdapter(this, cardModelList);
        RV.setAdapter(adapter);
        RV.setLayoutManager(new LinearLayoutManager(this));
    }

    public void pegarDados(){
        RV = findViewById(R.id.RV);
    }

}