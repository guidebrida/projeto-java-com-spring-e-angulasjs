package projeto.curso.manutencao.security;

import com.amazonaws.services.dynamodbv2.xspec.S;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import projeto.curso.manutencao.domain.enums.Funcionarios;

import java.util.Collection;

import java.util.Set;
import java.util.stream.Collectors;

public class UserSS  implements UserDetails {
    private static final long serialversionUID = 1L;
    private Integer id;
    private String usuario;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

   public UserSS(){
   }

    public UserSS(Integer id, String usuario, String senha, Collection<Funcionarios> funcionarios) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.authorities = funcionarios.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    public Integer getId(){
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
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
