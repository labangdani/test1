package com.example.test1.config;

import com.example.test1.service.UserDetailsServiceImpl;
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
               .withUser("kenne").password(passwordEncoder.encode("12342")).roles("USER","MODERATOR");
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("1234")).roles("USER");
       auth.inMemoryAuthentication()
               .withUser("Momene").password(passwordEncoder.encode("1234")).roles("USER");


      /* auth.jdbcAuthentication()
               .dataSource(dataSource)
               .usersByUsernameQuery("select username as principal, password as credential, active from utilisateur where username = ?")
//               .authoritiesByUsernameQuery("select name as role from roles where id = (select idrole as role from user_roles where idu=(select idu from utilisateur where username=?))")
               .passwordEncoder(passwordEncoder)
               .rolePrefix("ROLE_");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        // Configuration for Login Form.
        http.authorizeRequests().and().formLogin()//
                //
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
//                .loginPage("/User/connexion")//
                .defaultSuccessUrl("/User/home")//
//                .failureUrl("/admin/login?error=true")

                // Configuration for the Logout page.
                // (After logout, go to home page)
                .and().logout().logoutSuccessUrl("/");

        http.authorizeRequests().antMatchers("/User/listecommanduser").hasRole("USER");
        http.authorizeRequests().antMatchers("/plat/remplirPlatForm","/resto/allresto", "/command/listecommande").hasRole("MODERATOR");
        http.exceptionHandling().accessDeniedPage("/User/403");
        //http.authorizeRequests().antMatchers("/api/auth/**").permitAll();

    }
}
