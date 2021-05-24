package mx.uady.sicei.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.net.URI;

import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.service.EquipoService;
import mx.uady.sicei.model.request.EquipoRequest;

@RestController
@RequestMapping("/api")
public class EquipoRest {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> obtenerUsuario() {
        List<Equipo> equipos = equipoService.getEquipos();
        return ResponseEntity.ok(equipos);
    }

    @GetMapping("/equipos/{id}")
    public ResponseEntity<Equipo> getEquipo(@PathVariable Integer id) {
        return ResponseEntity.ok().body(equipoService.getEquipo(id));
    }

    @PostMapping("/equipos")
    public ResponseEntity<Equipo> postEquipos(@RequestBody @Valid EquipoRequest request) throws URISyntaxException {

        Equipo equipo = equipoService.crearEquipo(request);

        return ResponseEntity.created(new URI("/equipos/" + equipo.getId())).body(equipo);
    }

    @PutMapping("/equipos/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@RequestBody @Valid EquipoRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok().body(equipoService.actualizarEquipo(id, request));   
    }

    @PutMapping("/equipos/{equipoId}/alumnos/{alumnoId}/add")
    public ResponseEntity<Equipo> agregarAlumno(@PathVariable("equipoId") Integer equipoID,@PathVariable("alumnoId") Integer usuarioID) {
        return ResponseEntity.ok().body(equipoService.agregarAlumno(equipoID, usuarioID));   
    }

    @PutMapping("/equipos/{equipoId}/alumnos/{alumnoId}/delete")
    public ResponseEntity<Equipo> eliminarAlumno(@PathVariable("equipoId") Integer equipoID,@PathVariable("alumnoId") Integer usuarioID) {
        return ResponseEntity.ok().body(equipoService.eliminarAlumno(equipoID, usuarioID));   
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Integer id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.ok().build();
    }
    
}
