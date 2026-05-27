package cl.duoc.mineria.mantencion.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalValidationService {

    private final WebClient webClient;

    public ExternalValidationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    //validar si el usuario existe
    public boolean VerificarUsuarioExiste(Long usuarioId) {
        try {
            Boolean existe = this.webClient.get()
            .uri("http://localhost:8081/api/v1/usuarios/existe/" + usuarioId)
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();

            return existe != null && existe;
        } catch (Exception e) {
            return true;
        }
    }

    //validar si el equipo existe
    public boolean VerificarEquipoExiste(Long equipoId) {
        try {
            Boolean existe = webClient.get()
            .uri("http://localhost:8082/api/v1/equipos/existe/" + equipoId)
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();

            return existe != null && existe;
        } catch (Exception e) {
            return true;
        }
    }
}
