package br.fiap.aguiabranca_backend.security;

import br.fiap.aguiabranca_backend.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        log.info("Filtro interceptando rota: {} | Token presente: {}", request.getRequestURI(), token != null);

        if (token != null) {
            try {
                String email = tokenService.validarToken(token);
                log.info("Token decodificado para email: {}", email);

                if (email != null) {
                    usuarioRepository.findByEmail(email).ifPresentOrElse(usuario -> {
                        var authentication = new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                usuario.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.info("Usuario autenticado com sucesso: {} | Authorities: {}", usuario.getEmail(), usuario.getAuthorities());
                    }, () -> log.warn("Usuario nao encontrado no banco: {}", email));
                }
            } catch (Exception e) {
                log.error("Falha ao validar token ou autenticar: {}", e.getMessage(), e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "").trim();
    }
}