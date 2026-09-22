package br.fiap.aguiabranca_backend.controller;

import br.fiap.aguiabranca_backend.dto.UsuarioResponseDTO;
import br.fiap.aguiabranca_backend.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> obterPerfil(@AuthenticationPrincipal Usuario usuario) {
        UsuarioResponseDTO response = UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .build();

        return ResponseEntity.ok(response);
    }
}