package com.example.test1.repository;

import com.example.test1.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder.encode("12343")).roles("USER","MODERATOR","ADMIN");
        auth.inMemoryAuthentication()
                .withUser("mod").password(passwordEncoder.encode("12342")).roles("USER","MODERATOR");
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("1234")).roles("USER");


      /* auth.jdbcAuthentication()
               .dataSource(dataSource)
               .usersByUsernameQuery("select username as principal, password as credential, from utilisateur where username=? ")
               .authoritiesByUsernameQuery("select idrole as role from user_roles where idu=(select idu from utilisateur where username=?)")
               .passwordEncoder(passwordEncoder)
               .rolePrefix("ROLE_");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
       http.formLogin()/*.loginPage("/User/connexion")*/;
       http.authorizeRequests().antMatchers("/User/listeuser", "/User/home").hasRole("USER");
       http.authorizeRequests().antMatchers("/plat/remplirPlatForm", "/command/listecommande").hasRole("MODERATOR");
       http.exceptionHandling().accessDeniedPage("/User/403");
    }
}
