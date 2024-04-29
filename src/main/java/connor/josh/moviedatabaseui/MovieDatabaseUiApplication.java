package connor.josh.moviedatabaseui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


//http://moviedbjava-env.eba-pppkpw72.us-west-2.elasticbeanstalk.com:5000/index.html


@SpringBootApplication
public class MovieDatabaseUiApplication /*extends WebMvcConfigurerAdapter*/ {

    public static void main(String[] args) {
        SpringApplication.run(MovieDatabaseUiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new
                WebMvcConfigurer() {
                    @Override
                    public void addCorsMappings(CorsRegistry registry) {
                        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
                    }
                };
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // Can just allow `methods` that you need.
//        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
//    }
}
