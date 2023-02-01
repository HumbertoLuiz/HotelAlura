package hotel.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Value("${hotel.project.rememberMe.key}")
	private String rememberMeKey;

	@Value("${hotel.project.rememberMe.validitySeconds}")
	private int rememberMeValiditySeconds;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public SecurityFilterChain WebSecurityConfig(HttpSecurity http) throws Exception {
		http
				.securityMatchers(requestMatcherCustomizer -> requestMatcherCustomizer
				.requestMatchers("/admin/**"))
				.authorizeHttpRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer
				.requestMatchers("/admin/**")
				.permitAll()
				.requestMatchers("/admin/reset-password/**")
				.permitAll()
				.anyRequest()
				.authenticated())							
				.formLogin(formLoginCustomizer -> formLoginCustomizer
				.loginPage("/admin/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/admin/home")
				.permitAll())
				.logout(logoutCustomizer -> logoutCustomizer
				.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
				.logoutSuccessUrl("/admin/login"))
				.rememberMe(rememberMeCustomizer -> rememberMeCustomizer
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(rememberMeValiditySeconds)
				.key(rememberMeKey))
				.exceptionHandling(	exceptionHandlingCustomizer -> exceptionHandlingCustomizer
				.accessDeniedPage("/admin/login"));

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web
                .ignoring()
                .requestMatchers("/css/**")
                .requestMatchers("/img/**")
                .requestMatchers("/js**")
                .requestMatchers("/webjars/**");
	}

}
