package com.example.myapplication4;

import java.util.ArrayList;

class ListaDeMusicas {
    private ArrayList<Musica> musicas;

    ListaDeMusicas() {
        musicas = new ArrayList<>();
    }

    void add(Musica musica) {
        musicas.add(musica);
    }

    @Override
    public String toString() {
        StringBuilder resultado;

        resultado = new StringBuilder();

        for (Musica m : musicas) {
            resultado.append("\n\n").append(m.getId()).append("\n").append(m.getNome()).append("\n").append(m.getArtista());
        }
        return resultado.toString();
    }
}
