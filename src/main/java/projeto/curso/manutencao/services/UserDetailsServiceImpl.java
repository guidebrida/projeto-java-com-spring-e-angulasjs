package projeto.curso.manutencao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projeto.curso.manutencao.domain.Funcionario;
import projeto.curso.manutencao.repositories.FuncionarioRepository;
import projeto.curso.manutencao.security.UserSS;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FuncionarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Funcionario func = repo.findByUsuario(usuario);
        if (func == null) {
            throw new UsernameNotFoundException(usuario);
        }
        return new UserSS(func.getId(), func.getUsuario(), func.getSenha(), Collections.singleton(func.getFuncionario()));
    }
}
