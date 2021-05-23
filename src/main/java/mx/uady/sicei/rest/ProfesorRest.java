package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.service.ProfesorService;

@RestController // Metaprogramacion
@RequestMapping("/api")
public class ProfesorRest {

    @Autowired
    private ProfesorService profesorService;

    // GET /api/alumnos
    @GetMapping("/profesores")
    public ResponseEntity<List<Profesor>> getProfesores() {
        return ResponseEntity.ok().body(profesorService.getProfesores());
    }

    @GetMapping("/profesores/buscar") // RequestParam = Query parameter -> ?llave=valor&llave=valor
    public ResponseEntity<List<Profesor>> searchProfesores(@RequestParam("nombre") String nombre) {
        return ResponseEntity.ok().body(profesorService.buscarProfesores(nombre));
    }

    @PostMapping("/profesores") // POST api/profesores
    public ResponseEntity<Profesor> postProfesores(@RequestBody @Valid ProfesorRequest request) throws URISyntaxException {

        // RequestBody le indica a Java que estamos esperando un request que cumpla con
        // los campos del Objeto AlumnoRequest

        Profesor profesor = profesorService.crearProfesor(request);

        // 201 Created
        // Header: Location
        return ResponseEntity.created(new URI("/profesores/" + profesor.getId())).body(profesor);
    }

    @PutMapping("/profesores/{nombre}") // PUT api/profesores/Naomi
    public ResponseEntity<Profesor> editarPorfesor(@PathVariable("nombre") String nombre, @RequestBody ProfesorRequest profesor) {
        return ResponseEntity.ok().body(profesorService.actualizarProfesor(nombre,profesor));
        /*Profesor alumnoActualizado = alumnoService.updateAlumno(matricula, alumno);
        return ResponseEntity.ok().body(alumnoActualizado);*/
    }
    
}