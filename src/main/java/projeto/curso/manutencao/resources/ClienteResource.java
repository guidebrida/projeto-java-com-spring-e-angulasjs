package projeto.curso.manutencao.resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.curso.manutencao.domain.Cliente;
import projeto.curso.manutencao.dto.ClienteDTO;
import projeto.curso.manutencao.dto.ClienteNewDTO;
import projeto.curso.manutencao.services.ClienteService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
   @GetMapping
    public ResponseEntity<List<ClienteDTO>>findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }



    @PreAuthorize("hasAnyRole('RECEPCIONISTA', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ClienteNewDTO objDto) {
        Cliente obj = service.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('TECNICO','ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id,@RequestBody ClienteDTO clienteDTO) {
        service.update(id, clienteDTO);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasAnyRole('TECNICO','ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
