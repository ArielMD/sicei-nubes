package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.request.EquipoRequest;
import mx.uady.sicei.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> getEquipos() {

        List<Equipo> equipos = new LinkedList<>();
        equipoRepository.findAll().iterator().forEachRemaining(equipos::add);

        return equipos;
    }

    public Equipo getEquipo(Integer id) {
        Equipo equipo = equipoRepository.findbyId(id);
    
        return equipo;
      }

    public Equipo crearEquipo(EquipoRequest request){
        Equipo equipo = new Equipo();
        equipo.setId(request.getId());
        equipo = equipoRepository.save(equipo);

        return equipo;
    }

    public Equipo actualizarEquipo(Integer id, EquipoRequest request){
        Equipo equipo = getEquipo(id);
        equipo.setAlumnos(request.getAlumnos());
        equipo.setModelo(request.getModelo());
        equipoRepository.save(equipo);
        return equipo;
    }

    public void eliminarEquipo(Integer id) {
        Equipo equipo = getEquipo(id);
    
        equipoRepository.delete(equipo);
      }
    
}
