package br.com.lucasvir.nacola_deputados.model.entities;

import br.com.lucasvir.nacola_deputados.model.enums.UnidadeFederativa;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UnidadeFederativa uf;

    @OneToMany
    @JoinTable(name = "user_deputados", joinColumns = @JoinColumn(name = "user_id"))
    private List<Deputado> deputados;

    public User() {
        this.deputados = new ArrayList<>();
    }

    public User(String name, String email, String password, UnidadeFederativa uf) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.uf = uf;
        this.deputados = new ArrayList<>();
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

    public String getPassword() {
        return password;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }

    public List<Deputado> getDeputados() {
        return deputados;
    }

    public void setDeputados(List<Deputado> deputados) {
        this.deputados = deputados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uf=" + uf +
                ", deputados=" + deputados +
                '}';
    }


}

