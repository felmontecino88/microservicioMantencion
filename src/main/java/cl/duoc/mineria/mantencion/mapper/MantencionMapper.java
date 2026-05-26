package cl.duoc.mineria.mantencion.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import cl.duoc.mineria.mantencion.dto.ReportarPanaDTO;
import cl.duoc.mineria.mantencion.model.EstadoOrden;
import cl.duoc.mineria.mantencion.model.OrdenMantencion;

@Component
public class MantencionMapper {
    
    public OrdenMantencion toEntity(ReportarPanaDTO dto) {
        if (dto == null) return null;

        OrdenMantencion orden = new OrdenMantencion();

        orden.setTipoEquipo(dto.getTipoEquipo());

        orden.setEquipoId(dto.getEquipoId() != null ? dto.getEquipoId() : 0);
        orden.setReportadoPorUsuarioId(dto.getReportadoPorUsuarioId() != null ? dto.getReportadoPorUsuarioId() : 0);

        orden.setDescripcionFalla(dto.getDescripcionFalla());
        orden.setPrioridad(dto.getPrioridad());

        orden.setEstadoOrden(EstadoOrden.PENDIENTE);
        orden.setFechaHoraReporte(LocalDateTime.now());

        return orden;
    }

}
