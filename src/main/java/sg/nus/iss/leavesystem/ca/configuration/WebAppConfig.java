package sg.nus.iss.leavesystem.ca.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sg.nus.iss.leavesystem.ca.interceptor.SessionInterceptor;

@Component
public class WebAppConfig implements WebMvcConfigurer {

	@Autowired
	SessionInterceptor _sessionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(_sessionInterceptor).excludePathPatterns("/api/**");
	}
}
