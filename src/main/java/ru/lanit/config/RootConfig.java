package ru.lanit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:app.properties")
public class RootConfig implements WebMvcConfigurer {

    @Autowired
    Environment environment;

    @Override

    public void addCorsMappings(CorsRegistry registry) {
        if (!isProdProfile()) {
            registry.addMapping("/**").allowedMethods("GET", "POST", "DELETE");
        }
    }

    private boolean isProdProfile() {
        for (String profile : environment.getActiveProfiles()) {
            if (profile.equals("production")) {
                return true;
            }
        }
        return false;
    }
}