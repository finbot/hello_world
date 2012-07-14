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

public class HelloServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(HelloServlet.class);
	

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String submit = request.getParameter("submit");
		
		if("Init".equals(submit)){
			submitInit(request, response);
			return;
		}else if("OK".equals(submit)){
			submitOK(request, response);
			return;
		}else if("Back".equals(submit)){
			submitBack(request, response);
			return;
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/WelcomeJSP.jsp");
		requestDispatcher.forward(request, response);		
		
	}
	
	private void submitInit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/HelloJSP.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void submitOK(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String userName = request.getParameter("userName");
		
		SimpleEntity entity = new SimpleEntity();
		entity.setName(userName);
		SimpleService service = new SimpleService();
		service.createSimpleEntity(entity);

        String id = entity.get_id();
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/welcome.do?submit=Init&id=" + id);
		requestDispatcher.forward(request, response);
		
	}
	
	private void submitBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/table.do?submit=Init");
		requestDispatcher.forward(request, response);
		
	}

}
