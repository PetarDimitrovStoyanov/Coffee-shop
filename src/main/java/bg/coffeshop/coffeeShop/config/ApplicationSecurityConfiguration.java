package bg.coffeshop.coffeeShop.config;

import bg.coffeshop.coffeeShop.constant.RoleEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("asd");
        http
                .authorizeRequests()
                // with this line we allow access to all static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // with this line we allow access to homepage, login and register pages
                .antMatchers("/", "/homepage", "/auth/login", "/auth/register").permitAll()
                .antMatchers("/js/**", "/css/**", "/images/**", "/videos/**").permitAll()
                // here we give access on statistics page only if the user has role "ADMIN"
                .antMatchers("/statistics").hasRole(RoleEnum.ADMINISTRATOR.name())
                .antMatchers("/routes/**", "/api/**").permitAll()
                // with this line we not permit allow to all others pages of not authenticated users
                .antMatchers("/**").authenticated()
                .and()
                // configure login with login HTML form with two fields
                .formLogin()
                // gives instructions on Spring where is my login page
                .loginPage("/auth/login")
                // those that wants can name the parameter differently, e.g. as it is shown below
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .usernameParameter("email")
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // redirect user if the login was successful
                .defaultSuccessUrl("/homepage", true)
                // redirect user if the login was unsuccessful
                .failureForwardUrl("/error")
                .and()
                .logout().clearAuthentication(true)
                // this is the URL that Spring will allow for logout
                .logoutUrl("/logout")
                // where to go after logout
                .logoutSuccessUrl("/")
                // remove the session from the server
                .invalidateHttpSession(true)
                // delete the cookie that refers my session
                .deleteCookies("JSESSIONID");
      /*  http*/
       /*       *//*  .csrf()
                .csrfTokenRepository(csrfTokenRepository())*//*
                *//*.and()*//*
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
                .antMatchers("/", "/auth/register", "/auth/login").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/homepage")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/");*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // this gives to Spring two important components
        // 1. Our User detail service that translates usernames/emails, phone numbers and etc.to UserDetails
        // 2. Password encode - the component that decides whether the user password matches
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        // registration:
        // topsecretpass -> password encoder -> (asdasdasdasdasdasd pwd)

        // login -> username, raw password
        // password encoder matches raw password
    }
}
