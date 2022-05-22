package com.example.myapplication4;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;

public class TarefaExecutar {

    ExecutorService executor;
    Handler resultHandler;
    TextView textView;
    ProgressBar progressBar;
    private ListaDeMusicas listaDeMusicas;


    public TarefaExecutar(ExecutorService executor, Handler resultHandler, TextView textView, ProgressBar progressBar, String url) {
        this.executor = executor;
        this.resultHandler = resultHandler;
        this.textView = textView;
        this.progressBar = progressBar;
        listaDeMusicas = new ListaDeMusicas();

        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                doWork(url);
                atualizaInterface(listaDeMusicas.toString());
            }
        });
    }

    private void doWork(String url) {
        String jsonStr;
        HttpHandler handler;
        int progresso;

        listaDeMusicas = new ListaDeMusicas();
        handler = new HttpHandler();
        jsonStr = handler.lerInformacao(url);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONArray musicas = jsonObj.getJSONArray("Minha_musica");
                defineProgressBar(musicas.length()-1);
                for (int i = 0; i < musicas.length(); i++) {
                    JSONObject musicasJSONObject = musicas.getJSONObject(i);
                    String musicaNome = musicasJSONObject.getString("nome_musica");
                    String id_musica = musicasJSONObject.getString("id_musica");
                    String nome_artista = musicasJSONObject.getString("nome_artista");
                    listaDeMusicas.add(new Musica(musicaNome, id_musica, nome_artista));
                    progresso = i;
                    sleep(100);
                    atualizaInterface(progresso);
                }
            } catch (JSONException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void atualizaInterface(int progresso) {

        resultHandler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(progresso);
            }
        });
    }

    private void atualizaInterface(String mensagem) {

        resultHandler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(mensagem);
            }
        });
    }

    private void defineProgressBar(int max) {

        resultHandler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setMax(max);
            }
        });
    }
}