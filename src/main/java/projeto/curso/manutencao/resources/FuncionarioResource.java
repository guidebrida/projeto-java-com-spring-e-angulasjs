package projeto.curso.manutencao.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.curso.manutencao.domain.Equipamento;
import projeto.curso.manutencao.domain.Funcionario;

import projeto.curso.manutencao.dto.FuncionarioDTO;
import projeto.curso.manutencao.services.FuncionarioService;

import java.net.URI;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioResource {


    @Autowired
    private FuncionarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> find(@PathVariable Integer id) {
        Funcionario obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Funcionario> insert(@RequestBody Funcionario obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/logado")
    public FuncionarioDTO funcionarioLogado(){
        return new FuncionarioDTO(service.funcionarioLogado());
    }

}
