package projeto.curso.manutencao.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.curso.manutencao.domain.Funcionario;
import projeto.curso.manutencao.repositories.FuncionarioRepository;
import projeto.curso.manutencao.security.UserSS;
import projeto.curso.manutencao.services.exception.ObjectNotFoundException;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private FuncionarioRepository repo;

    public Funcionario find(Integer id) {
        Optional<Funcionario> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Funcionario não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));
    }

    public Funcionario insert(Funcionario obj) {
        obj.setSenha(pe.encode(obj.getSenha()));
        return repo.save(obj);
    }


    public Funcionario findByUsuario(String usuario) {
        Funcionario obj = repo.findByUsuario(usuario);
        if (obj == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + obj.getId() + ", Tipo: " + Funcionario.class.getName());
        }
        return obj;
    }

    public Funcionario funcionarioLogado() {
        UserSS username = Usuario.authenticated();
        if (username == null) {
            throw new ObjectNotFoundException(
                    "Não tem nenhum usuario logado!");
        }

        return this.findByUsuario(username.getUsername());
    }


}

