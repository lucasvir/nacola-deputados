package br.com.lucasvir.nacola_deputados.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Deputado implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String siglaPartido;
    private String siglaUf;
    private String urlFoto;

    public Deputado() {
    }

    public Deputado(String name, String email, String siglaPartido, String siglaUf, String urlFoto) {
        this.name = name;
        this.email = email;
        this.siglaPartido = siglaPartido;
        this.siglaUf = siglaUf;
        this.urlFoto = urlFoto;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deputado deputado = (Deputado) o;
        return Objects.equals(id, deputado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Deputado{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", siglaPartido='" + siglaPartido + '\'' +
                ", siglaUf='" + siglaUf + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }
}
