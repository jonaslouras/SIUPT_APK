package com.example.myapplication4;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ExecutorService executorService;
    Handler mainThreadHandler;

    TextView textView;
    ProgressBar progressBar;
    String url = "http://alunos.upt.pt/~abilioc/musica1.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executorService = Executors.newFixedThreadPool(4);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

        textView = findViewById(R.id.listaDeMusicas);
        progressBar = findViewById(R.id.progressBar);
        textView.setMovementMethod(new ScrollingMovementMethod());

    }

    public void iniciarProcesso(View view) {
        new TarefaExecutar(executorService, mainThreadHandler,textView,progressBar,url);
    }
}