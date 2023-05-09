package sejong.europlanner.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/swagger-ui/**", "/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/boards/**", "/comments/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/boards/**", "/comments/**").permitAll()
                .antMatchers(HttpMethod.GET, "/boards/**", "/comments/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/boards/**", "/comments/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/boards/**", "/comments/**").permitAll()
                .and()
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();

        http.headers().frameOptions().disable();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        List<String> permitAllEndpoints = Arrays.asList(
                "/h2-console/**",
                "/swagger.ui/**",
                "/users/**"
        );
        return new JwtTokenFilter(jwtSecret, permitAllEndpoints);
    }
}
