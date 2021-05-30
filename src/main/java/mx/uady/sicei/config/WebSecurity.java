package mx.uady.sicei.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenFilter tokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .csrf().disable()
                .httpBasic().disable() // Authorization: Basic base64(usuario:contrasena) x.x
            .authorizeRequests()
                .antMatchers("/api/login").permitAll() //access to login
                .antMatchers(HttpMethod.POST, "/api/register").permitAll() //access to sign up
                .anyRequest().authenticated()
            .and()
                .addFilterAfter(tokenFilter, BasicAuthenticationFilter.class);
    }
}