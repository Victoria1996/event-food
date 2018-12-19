package by.bsu.eventfood.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static by.bsu.eventfood.model.RoleName.BUSINESS_CLIENT;
import static by.bsu.eventfood.model.RoleName.GENERAL_CLIENT;
import static org.apache.logging.log4j.util.Strings.EMPTY;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${allowed-origins}")
    private String[] allowedOrigins;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        "/event/**", "/place/**")
                .hasAuthority(BUSINESS_CLIENT.name())

                .antMatchers(HttpMethod.GET,
                        "/profile/add-event")
                .hasAuthority(BUSINESS_CLIENT.name())

                .antMatchers(HttpMethod.POST,
                        "/comment/**")
                .hasAuthority(GENERAL_CLIENT.name())

                .antMatchers(HttpMethod.POST,
                        "/reservation/**")
                .hasAnyAuthority("ROLE_ANONYMOUS", GENERAL_CLIENT.name())

                .antMatchers("/actuator/**", "/sign-in", "/sign-up",
                        "/swagger-resources/**", "/swagger-ui.html",
                        "/v2/api-docs/**", "/webjars/**")
                .permitAll()

                .antMatchers(HttpMethod.GET,
                        "/profile/{id}", "/place/all", "/place/{id}", "/place/{id}/reserve",
                        "/event/all","/event/{id}")
                .permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(EMPTY);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowCredentials(true)
                        .allowedOrigins(allowedOrigins);
            }
        };
    }

}
