package next.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetList
 */
public class GetList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionDao questionDao = new QuestionDao();
		List<Question> questions = null;
		
		try {
			questions = (List<Question>) questionDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String responseString = "";
		
		Gson gson = new Gson();
		responseString = gson.toJson(questions);
		System.out.println(responseString);
		
		PrintWriter out = response.getWriter();
		
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		out.write(responseString);
		
	}


}
