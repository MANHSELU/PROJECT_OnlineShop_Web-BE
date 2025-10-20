package Filter;

import Model.Users;
import Repository.UserRepository;
import Util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractJwtFromHeader(request);
        String email = null;
        if (token != null && jwtUtil.validateToken(token)) {
            email = jwtUtil.getSubject(token);
        }
        Users users = userRepository.FindByEmail(email);
        if (users != null) {
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + users.getRole().toString())); // Phải thêm ROLE_ vì là prefix
                                                                                                                                        // mới xác định được role trong Spring Security
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(users.getEmail(),
                    null,
                    authorities
            ); // ==> Nếu User tồn tại thì tạo object UsernamePasswordAuthenticationToken chứa Principal : users.getEmail() và Authorities: role của user (ROLE_MEMBER, ROLE_ADMIN, …).
            SecurityContextHolder.getContext().setAuthentication(authentication); //Đoạn này để Spring Security biết rằng request này đã đăng nhập với user đó và role tương ứng.
                                                                                  //Nhờ vậy các annotation như @PreAuthorize("hasRole('MEMBER')") mới hoạt động.
        }
        filterChain.doFilter(request, response); // filterChain này cho phép thực hiện các request tiếp theo
    }

    private String extractJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // cắt "Bearer "
        }
        return null;
    }




}
