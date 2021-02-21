package java2.application_target_list.web_ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin_menu").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/user_menu").hasAnyRole(Role.USER.name())
//                .antMatchers("/target/deleteTargetFromList").hasAnyRole(Role.ADMIN.name())
//                .antMatchers("/target/changeTargetName").hasAnyRole(Role.ADMIN.name())
//                .antMatchers("/target/changeTargetDescription").hasAnyRole(Role.ADMIN.name())
//                .antMatchers("/target/changeTargetDeadline").hasAnyRole(Role.ADMIN.name())
//                .antMatchers("/user/addUserToList").hasAnyRole(Role.ADMIN.name())
//                .antMatchers("/user/deleteUserFromList").hasAnyRole(Role.ADMIN.name())
//                .antMatchers("/user/changeUserFirstName").hasAnyRole(Role.ADMIN.name())
//                .antMatchers("/user/changeUserLastName").hasAnyRole(Role.ADMIN.name())
//                .antMatchers("/board/deleteRecordFromList").hasAnyRole(Role.ADMIN.name())
//                .anyRequest()
//                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles(Role.USER.name())
                .and()
                .withUser("admin")
                .password("admin")
                .roles(Role.ADMIN.name());
    }




    @Bean
    protected PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
