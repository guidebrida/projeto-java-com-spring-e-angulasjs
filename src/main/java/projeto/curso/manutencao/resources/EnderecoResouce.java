package projeto.curso.manutencao.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.curso.manutencao.domain.Cliente;
import projeto.curso.manutencao.domain.Endereco;
import projeto.curso.manutencao.dto.ClienteDTO;

import projeto.curso.manutencao.services.EnderecoService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResouce {


    @Autowired
    private EnderecoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> find(@PathVariable Integer id) {
        Endereco obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

   @PreAuthorize("hasAnyRole('RECEPCIONISTA', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Endereco> insert(@RequestBody Endereco obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//   @PreAuthorize("hasAnyRole('TECNICO', 'ADMIN')")
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Void> update(@RequestBody Endereco obj, @PathVariable Integer id) {
//        obj.setId(id);
//        obj = service.update(obj);
//        return ResponseEntity.noContent().build();
//    }

   @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        List<Endereco> list = service.findAll();
        List<Endereco> listDto = list.stream().map(obj -> new Endereco()).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
