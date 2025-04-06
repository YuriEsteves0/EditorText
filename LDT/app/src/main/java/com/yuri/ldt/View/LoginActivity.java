package com.yuri.ldt.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.yuri.ldt.Controller.LoginActivityController;
import com.yuri.ldt.R;

public class LoginActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private RelativeLayout email;
    private Button btnEntrar;
    private TextInputEditText senhaInput2, senhaInput, emailInput;
    private LoginActivityController loginActivityController = new LoginActivityController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pegarDados();

        configTabLayout();
        configLogin();
    }

    public void configLogin(){
        btnEntrar.setOnClickListener(v -> {
            loginActivityController.verifDados(this, senhaInput2, senhaInput, emailInput);
        });
    }

    public void configTabLayout(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if(pos == 0){
                    email.setVisibility(View.VISIBLE);
                }else if(pos == 1){
                    email.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void pegarDados(){
        tabLayout = findViewById(R.id.tabLayout);
        email = findViewById(R.id.email);
        senhaInput2 = findViewById(R.id.senhaInput2);
        senhaInput = findViewById(R.id.senhaInput);
        emailInput = findViewById(R.id.emailInput);
        btnEntrar = findViewById(R.id.btnEntrar);
    }
}