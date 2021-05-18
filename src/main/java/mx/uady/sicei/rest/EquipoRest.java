package mx.uady.sicei.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.service.EquipoService;

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
    
}
