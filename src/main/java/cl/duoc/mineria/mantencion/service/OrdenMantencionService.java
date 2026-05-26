package cl.duoc.mineria.mantencion.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.mineria.mantencion.exception.MaintenanceNotFoundException;
import cl.duoc.mineria.mantencion.model.EstadoOrden;
import cl.duoc.mineria.mantencion.model.OrdenMantencion;
import cl.duoc.mineria.mantencion.repository.OrdenMantencionRepository;

@Service
public class OrdenMantencionService {

    private final OrdenMantencionRepository repository;

    public OrdenMantencionService(OrdenMantencionRepository repository) {
        this.repository = repository;
    }

    public OrdenMantencion crearOrden(OrdenMantencion orden) {
        return repository.save(orden);
    }

    public List<OrdenMantencion> obtenerTodas() {
        return repository.findAll();
    }

    public OrdenMantencion resolverOrden(Long id, EstadoOrden nuevoEstado) {
        OrdenMantencion orden = repository.findById(id)
        .orElseThrow(() -> new MaintenanceNotFoundException("La orden de mantención con ID " + id + " no existe en los registros del taller."));

        orden.setEstadoOrden(nuevoEstado);

        if (nuevoEstado == EstadoOrden.COMPLETADA) {
            orden.setFechaHoraResolucion(LocalDateTime.now());
        }

        return repository.save(orden);
    }

    public List<OrdenMantencion> obtenerPorEquipo(Long equipoId) {
        return repository.findByEquipoId(equipoId);
    }
}
