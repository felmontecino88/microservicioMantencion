package cl.duoc.mineria.mantencion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mineria.mantencion.dto.ReportarPanaDTO;
import cl.duoc.mineria.mantencion.dto.ResolverOrdenDTO;
import cl.duoc.mineria.mantencion.mapper.MantencionMapper;
import cl.duoc.mineria.mantencion.model.OrdenMantencion;
import cl.duoc.mineria.mantencion.service.OrdenMantencionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/mantenciones")
public class OrdenMantencionController {

    private final OrdenMantencionService service;
    private final MantencionMapper mapper;

    public OrdenMantencionController(OrdenMantencionService service, MantencionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/reportar")
    public ResponseEntity<OrdenMantencion> reportarPana(@Valid @RequestBody ReportarPanaDTO dto) {
        OrdenMantencion nuevaOrden = mapper.toEntity(dto);
        OrdenMantencion guardada = service.crearOrden(nuevaOrden);
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrdenMantencion>> listarTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @PutMapping("/resolver")
    public ResponseEntity<OrdenMantencion> resolverOrden(@Valid @RequestBody ResolverOrdenDTO dto) {
        OrdenMantencion actualizada = service.resolverOrden(dto.getId(), dto.getEstadoOrden());
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<OrdenMantencion>> listarPorEquipo(@PathVariable Long equipoId) {
        return ResponseEntity.ok(service.obtenerPorEquipo(equipoId));
    }




}
