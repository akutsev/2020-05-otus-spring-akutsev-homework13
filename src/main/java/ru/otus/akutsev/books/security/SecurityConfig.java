package ru.otus.akutsev.books.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.sql.DataSource;
import java.util.Locale;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	public void configure( WebSecurity web ) {
		web.ignoring().antMatchers( "/" );
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 	throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	public void configure( HttpSecurity http ) throws Exception {
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.ALWAYS )
				.and()
				.authorizeRequests().antMatchers( "/edit*", "/save**", "/delete*").authenticated()
				.and()
				.formLogin()
				.and()
				.rememberMe()
				.key("ran6789dom")
				.tokenValiditySeconds(60*10);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}
}
