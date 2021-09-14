package projeto.curso.manutencao.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.curso.manutencao.domain.Equipamento;
import projeto.curso.manutencao.dto.EquipamentoDTO;
import projeto.curso.manutencao.services.EquipamentoService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/equipamentos")
public class EquipamentoResource {

    @Autowired
    private EquipamentoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Equipamento> find(@PathVariable Integer id) {
        Equipamento obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('RECEPCIONISTA', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Equipamento> insert(@RequestBody Equipamento obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('TECNICO', 'ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid  @RequestBody EquipamentoDTO updateObj, @PathVariable Integer id) {
        Equipamento obj = service.update(updateObj, id);
        obj.setId(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EquipamentoDTO>> findAll() {
        List<Equipamento> list = service.findAll();
        List<EquipamentoDTO> listDto = list.stream().map(obj -> new EquipamentoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
