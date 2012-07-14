package pl.kwisniewski.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.kwisniewski.entities.plain.SimpleEntity;
import pl.kwisniewski.services.SimpleService;

public class TableServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(TableServlet.class);
	

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String submit = request.getParameter("submit");
		
		if("Init".equals(submit)){
			submitInit(request, response);
			return;
		}else if("Create".equals(submit)){
			submitCreate(request, response);
			return;
		}else if("Edit".equals(submit)){
			submitEdit(request, response);
			return;
		}else if("Delete".equals(submit)){
			submitDelete(request, response);
			return;
		}
				
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/TableJSP.jsp");
		requestDispatcher.forward(request, response);		
		
	}
	
	private void submitInit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		SimpleService service = new SimpleService();
		List<SimpleEntity> list = service.getSimpleEntityList();		
		request.setAttribute("simpleEntityList", list);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/TableJSP.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void submitCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hello.do?submit=Init");
		requestDispatcher.forward(request, response);
		
	}
	
	private void submitEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.do?submit=Init&id=" + id);
		requestDispatcher.forward(request, response);
		
	}
	
	private void submitDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.do?submit=Init&id=" + id);
		requestDispatcher.forward(request, response);
		
	}
		
}
