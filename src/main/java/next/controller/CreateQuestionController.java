package next.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;

public class CreateQuestionController implements Controller {
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");

		QuestionDao questionDao = new QuestionDao();
		Question question = new Question(writer, title, contents);
		questionDao.insert(question);
		

		return "/list.next";
	}
}
