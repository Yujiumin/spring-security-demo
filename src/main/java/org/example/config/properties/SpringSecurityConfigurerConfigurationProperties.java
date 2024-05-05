package org.example.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Michael
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.security.configurer")
public class SpringSecurityConfigurerConfigurationProperties {

    private HttpSecurity httpSecurity;

    @Data
    public static class HttpSecurity {

        private String[] permit;

    }
}
