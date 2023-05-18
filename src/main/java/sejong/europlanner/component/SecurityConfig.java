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
        String[] noAuthList = {"/h2-console/**", "/users/**"};
        String[] allMethodsEndpoints = {"/boards/**", "/comments/**", "/hotel/**", "/airplane/**"};
        String[] swaggerList = {
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/webjars/**"
        };

        http
                .cors().and()
                .authorizeRequests()
                .antMatchers(noAuthList).permitAll()
                .antMatchers(swaggerList).permitAll()
                .antMatchers(HttpMethod.POST, allMethodsEndpoints).permitAll()
                .antMatchers(HttpMethod.DELETE, allMethodsEndpoints).permitAll()
                .antMatchers(HttpMethod.GET, allMethodsEndpoints).permitAll()
                .antMatchers(HttpMethod.PUT, allMethodsEndpoints).permitAll()
                .antMatchers(HttpMethod.OPTIONS, allMethodsEndpoints).permitAll()
                .and()
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();

        http.headers().frameOptions().disable();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        List<String> permitAllEndpoints = Arrays.asList(
                "/h2-console/**",
                "/swagger-ui/**",
                "/users/**",
                "/swagger-ui.html",
                "/country",
                "/getURI"
        );
        return new JwtTokenFilter(jwtSecret, permitAllEndpoints);
    }
}
