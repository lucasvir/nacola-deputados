package br.com.lucasvir.nacola_deputados.model.entities;

import br.com.lucasvir.nacola_deputados.model.enums.UnidadeFederativa;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_users")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 100, unique = true, nullable = false)
    private String email;
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeFederativa uf;

    @ManyToMany
    @JoinTable(name = "user_deputados", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "deputado_id"))
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

