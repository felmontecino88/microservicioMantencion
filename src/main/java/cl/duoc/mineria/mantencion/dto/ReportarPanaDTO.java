package cl.duoc.mineria.mantencion.dto;

import cl.duoc.mineria.mantencion.model.Prioridad;
import cl.duoc.mineria.mantencion.model.TipoEquipo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportarPanaDTO {

    @NotNull(message = "El tipo de equipo es obligatorio (Camión, Pala, etc)")
    private TipoEquipo tipoEquipo;

    @NotNull(message = "El ID del equipo afectado es obligatorio")
    @Min(value = 1, message = "El ID del equipo debe ser un número entero mayor que 0")
    private Long equipoId;

    @NotNull(message = "El ID del usuario que reporta es obligatorio")
    @Min(value = 1, message = "El ID del usuario debe ser un número entero mayor que o")
    private Long reportadoPorUsuarioId;

    @NotBlank(message = "Debes describir brevemente cual es la falla detectada")
    private String descripcionFalla;

    @NotNull(message = "La prioridad de la reparación es obligatorio")
    private Prioridad prioridad;
}
