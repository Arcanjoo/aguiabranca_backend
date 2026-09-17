package br.fiap.aguiabranca_backend.service;

import br.fiap.aguiabranca_backend.dto.LoginDTO;
import br.fiap.aguiabranca_backend.dto.RegistroDTO;
import br.fiap.aguiabranca_backend.dto.TokenResponseDTO;
import br.fiap.aguiabranca_backend.model.Usuario;
import br.fiap.aguiabranca_backend.repository.UsuarioRepository;
import br.fiap.aguiabranca_backend.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public TokenResponseDTO registrar(RegistroDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .role(dto.getRole())
                .build();

        usuarioRepository.save(usuario);
        String token = tokenService.gerarToken(usuario);

        return new TokenResponseDTO(token, usuario.getNome(), usuario.getRole().name());
    }

    public TokenResponseDTO autenticar(LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas."));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas.");
        }

        String token = tokenService.gerarToken(usuario);
        return new TokenResponseDTO(token, usuario.getNome(), usuario.getRole().name());
    }
}