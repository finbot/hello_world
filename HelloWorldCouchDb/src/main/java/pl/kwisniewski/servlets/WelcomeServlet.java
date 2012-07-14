package pl.kwisniewski.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.kwisniewski.entities.plain.SimpleEntity;
import pl.kwisniewski.services.SimpleService;

public class WelcomeServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(WelcomeServlet.class);
	
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
						
		String submit = request.getParameter("submit");
		
		if("Init".equals(submit)){
			submitInit(request, response);
			return;
		}else if("Back".equals(submit)){
			submitBack(request, response);
			return;
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/WelcomeJSP.jsp");
		requestDispatcher.forward(request, response);		
		
	}
	
	private void submitInit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");		
		SimpleService simpleService = new SimpleService();
		SimpleEntity entity = simpleService.getSimpleEntity(id);
		request.setAttribute("userName", entity.getName());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/WelcomeJSP.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void submitBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hello.do?submit=Init");
		requestDispatcher.forward(request, response);
		
	}

}
