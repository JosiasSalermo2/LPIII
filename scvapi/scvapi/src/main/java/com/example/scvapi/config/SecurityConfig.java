package com.example.scvapi.config;

import com.example.scvapi.security.JwtAuthFilter;
import com.example.scvapi.security.JwtService;
import com.example.scvapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/v1/cargos/**")
                        .permitAll()
                        //.hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/v1/comorbidades/**")
                        .permitAll()
                    .antMatchers("/api/v1/compras/**")
                        .permitAll()
                        //.hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/v1/descartes/**")
                        .permitAll()
                    .antMatchers("/api/v1/enderecos/**")
                        .permitAll()
                    .antMatchers("/api/v1/estoques/**")
                        .permitAll()
                    .antMatchers("/api/v1/fabricantes/**")
                        .permitAll()
                    .antMatchers("/api/v1/fornecedores/**")
                        .permitAll()
                    .antMatchers("/api/v1/funcionarios/**")
                        .permitAll()
                        //.hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/v1/lotes/**")
                        .permitAll()
                    .antMatchers("/api/v1/pacientes/**")
                        .permitAll()
                    .antMatchers("/api/v1/telefones/**")
                        .permitAll()
                    .antMatchers("/api/v1/tiposvacinas/**")
                        .permitAll()
                    .antMatchers("/api/v1/vacinacoes/**")
                        .permitAll()
                    .antMatchers("/api/v1/vacinas/**")
                        .permitAll()
                        //.hasAnyRole("ADMIN")
                    .antMatchers("/api/v1/agendamentos/**")
                        .permitAll()
                        //.hasAnyRole("USER", "ADMIN")
                    .antMatchers( "/api/v1/usuarios/**")
                        .permitAll()
                    .anyRequest().authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                 .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
