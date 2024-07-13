package com.aluracursos.forohub.domain.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(unique = true, name = "nombre_usuario")
    private String nombreUsuario;

    @Column(unique = true)
    private String nombre;

    @Column(unique = true)
    private String email;

    private String contrasena;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> publicaciones;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> publicacionesRespondidas;

    public void setPublicaciones(List<Topico> publicaciones) {
        publicaciones.forEach(e-> e.setAutor(this));
        this.publicaciones = publicaciones;
    }

    public void setPublicacionesRespondidas(List<Respuesta> publicacionesRespondidas) {
        publicacionesRespondidas.forEach(e-> e.setAutor(this));
        this.publicacionesRespondidas = publicacionesRespondidas;
    }

    //Indica el rol que tiene asignado
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
