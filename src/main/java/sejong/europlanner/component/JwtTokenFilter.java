package sejong.europlanner.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final String jwtSecret;

    private final List<RequestMatcher> permitAllRequestMatchers;

    public JwtTokenFilter(String jwtSecret, List<String> permitAllEndpoints) {
        this.jwtSecret = jwtSecret;
        this.permitAllRequestMatchers = permitAllEndpoints.stream()
                .map(AntPathRequestMatcher::new)
                .collect(Collectors.toList());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Check if the request matches any permitAll endpoint
        boolean isPermitAllEndpoint = permitAllRequestMatchers.stream()
                .anyMatch(matcher -> matcher.matches(request));

        if (isPermitAllEndpoint) {
            filterChain.doFilter(request, response); // 건너뛰고 다음 필터로 넘어갑니다.
            return;
        }

        String token = request.getHeader("Authorization");

        if (token != null) {
            try {
                Jws<Claims> claimsJws = Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .parseClaimsJws(token);

                Claims claims = claimsJws.getBody();
                String username = claims.getSubject();

                UserDetails userDetails = User.withUsername(username)
                        .password("")
                        .authorities(new ArrayList<>())
                        .build();

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // JWT 토큰 값을 응답 헤더에 추가합니다 (Bearer 없이).
                response.setHeader("Authorization", token);
                filterChain.doFilter(request, response); // 이 부분을 try 블록 안으로 옮깁니다.


            } catch (JwtException e) {
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 응답 상태를 401로 설정합니다.
                return; // 필터 체인을 종료하고 다음 필터로 넘어가지 않습니다.
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 응답 상태를 401로 설정합니다.
            return; // 필터 체인을 종료하고 다음 필터로 넘어가지 않습니다.
        }
    }
}