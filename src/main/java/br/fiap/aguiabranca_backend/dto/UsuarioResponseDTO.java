package br.fiap.aguiabranca_backend.dto;

import br.fiap.aguiabranca_backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
    private String id;
    private String nome;
    private String email;
    private Role role;
}