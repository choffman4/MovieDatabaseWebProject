package connor.josh.moviedatabaseui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    static String url = "jdbc:mysql://awseb-e-ctpmnqybmw-stack-awsebrdsdatabase-gmem0u0zwemc.ciabqvuwkkzn.us-west-2.rds.amazonaws.com:3306/" +
            "moviedb";
    static String user = "admin";
    static String pass = "password";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    //loop through database of users and add permissions
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        List<UserDetails> userDetailsList = new ArrayList<>();
//        userDetailsList.add(org.springframework.security.core.userdetails.User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN").build());

        String sql = "SELECT * from moviedb.useraccount";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = "USER";

                userDetailsList.add(org.springframework.security.core.userdetails.User.withUsername(username)
                        .password(passwordEncoder().encode(password))
                        .roles(role).build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    //stops the browser prompting for username/password, also see in configure()
    public class noPopupBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException) throws IOException, ServletException {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/movie.html").permitAll()
                .antMatchers("/sign_up.html").permitAll()
                .antMatchers("/user/newUser").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/javascript/login.js").permitAll()
                .antMatchers("/javascript/index.js").permitAll()
                .antMatchers("/javascript/sign_up.js").permitAll()
                .antMatchers("/javascript/movie.js").permitAll()
                .antMatchers("/favorites.html").permitAll()
                .antMatchers("/profile.html").permitAll()
                .antMatchers("/javascript/favorites.js").permitAll()
                .antMatchers("/javascript/profile.js").permitAll()
                .antMatchers("/javascript/signout.js").permitAll()
                .antMatchers("/movies/recommends/{imdib}").permitAll()


                .antMatchers("/user/**").hasRole("USER") // allows your homepage to be accessed
                .antMatchers("/movies/**").hasRole("USER") // allows your homepage to be accessed

                .anyRequest().authenticated() //any other requests, use httpBasic authentication
                .and()
                .csrf().disable() //disable cross site request forgery
                .sessionManagement().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .httpBasic()
                .authenticationEntryPoint(new noPopupBasicAuthenticationEntryPoint());
                //this stops browser from prompting for username/password
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
