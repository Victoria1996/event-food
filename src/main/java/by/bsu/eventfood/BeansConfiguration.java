package by.bsu.eventfood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
public class BeansConfiguration {
    @Bean
    public CompositeLogoutHandler compositeLogoutHandler() {
        return new CompositeLogoutHandler(new CookieClearingLogoutHandler(), new SecurityContextLogoutHandler());
    }
}
