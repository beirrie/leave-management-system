package sg.nus.iss.leavesystem.ca.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		HttpSession _Session = request.getSession();
		System.out.println(request.getRequestURI());
		System.out.println("Session is null:" + (_Session.getAttribute("user") == null));
		//If you are going to any pages(excluding login), I will check if you have valid session. 
		if(!request.getRequestURI().equals("/login")) {
			System.out.println("Inside here");
			// However, if you are going to authenticate page, I will not stop you. 
			if (request.getRequestURI().equals("/authenticate")) {
				return true;
			}
			// If null, redirect you to login.
			if (_Session.getAttribute("user") == null) {
				response.sendRedirect("/login");
				return false;
			}
			// If valid session, direct you to the page you are asking for.
			else {
				return true;
			}			
		}
		//If you are going to login page ...
		else {
			//and you are not already logged in, allow you to proceed.
			if (_Session.getAttribute("user") == null) {
				return true;
			} 
			//else will redirect you to view balance page.
			else {
				response.sendRedirect("/LeaveApplication");
				return false;
			}
		}
	}
	
}
