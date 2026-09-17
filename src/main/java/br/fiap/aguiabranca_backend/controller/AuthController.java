package br.fiap.aguiabranca_backend.controller;

import br.fiap.aguiabranca_backend.dto.LoginDTO;
import br.fiap.aguiabranca_backend.dto.RegistroDTO;
import br.fiap.aguiabranca_backend.dto.TokenResponseDTO;
import br.fiap.aguiabranca_backend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<TokenResponseDTO> registrar(@RequestBody @Valid RegistroDTO dto) {
        TokenResponseDTO response = usuarioService.registrar(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginDTO dto) {
        TokenResponseDTO response = usuarioService.autenticar(dto);
        return ResponseEntity.ok(response);
    }
}