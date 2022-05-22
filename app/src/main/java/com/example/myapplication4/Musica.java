package com.example.myapplication4;

class Musica {
    private String nome;
    private String id;
    private String artista;

    Musica(String nome, String id, String artista) {
        this.nome = nome;
        this.id = id;
        this.artista = artista;
    }

    String getNome() {
        return nome;
    }

    String getId() {
        return id;
    }

    String getArtista() {
        return artista;
    }
}
