package com.yuri.ldt.View;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.yuri.ldt.Controller.CardEditController;
import com.yuri.ldt.Controller.Helpers.ActivityHelper;
import com.yuri.ldt.Controller.Helpers.AndroidHelper;
import com.yuri.ldt.Controller.Helpers.SoftInputAssist;
import com.yuri.ldt.R;

public class CardEdit extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    private EditText editTextNote;
    private ImageView Underline, Bold, Italic, saving;
    private SoftInputAssist softInputAssist;
    private String textoDigitado, titulo, data, descricao, idCard, idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_card_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pegarDados();
        pegarDadosIntent();

        softInputAssist = new SoftInputAssist(this);

        CardEditController cardEditController = new CardEditController(this, editTextNote, idCard, saving);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityHelper.finishActivity(CardEdit.this);
                finish();
            }
        });

        Underline.setOnClickListener(view -> {
            int start = editTextNote.getSelectionStart();
            int end = editTextNote.getSelectionEnd();

            if (start != end) {
                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(editTextNote.getText());
                stringBuilder.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                editTextNote.setText(stringBuilder);
                editTextNote.setSelection(end);
                AndroidHelper.logs("UNDERLINE");
            }
        });

        Bold.setOnClickListener(view -> {
            int start = editTextNote.getSelectionStart();
            int end = editTextNote.getSelectionEnd();

            if (start != end) {
                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(editTextNote.getText());
                stringBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                editTextNote.setText(stringBuilder);
                editTextNote.setSelection(end);
                AndroidHelper.logs("STRONG");
            }
        });

        Italic.setOnClickListener(view -> {
            int start = editTextNote.getSelectionStart();
            int end = editTextNote.getSelectionEnd();

            if (start != end) {
                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(editTextNote.getText());
                stringBuilder.setSpan(new StyleSpan(Typeface.ITALIC), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                editTextNote.setText(stringBuilder);
                editTextNote.setSelection(end);
                AndroidHelper.logs("ITALIC");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (softInputAssist != null) softInputAssist.onPause();
        CardEditController.pararAutoSalvar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (softInputAssist != null) softInputAssist.onDestroy();
        CardEditController.pararAutoSalvar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (softInputAssist != null) softInputAssist.onResume();
    }

    public void pegarDadosIntent(){
        Intent intent = getIntent();
        idCard = intent.getStringExtra("idCard");
        titulo = intent.getStringExtra("titulo");
        descricao = intent.getStringExtra("descricao");
        idUsuario = intent.getStringExtra("idUsuario");
        data = intent.getStringExtra("data");

        topAppBar.setTitle(titulo);
        editTextNote.setText(descricao);

    }

    public void pegarDados(){
        topAppBar = findViewById(R.id.topAppBar);
        Underline = findViewById(R.id.Underline);
        Bold = findViewById(R.id.Bold);
        Italic = findViewById(R.id.Italic);
        saving = findViewById(R.id.saving);
        editTextNote = findViewById(R.id.editTextNote);
    }
}