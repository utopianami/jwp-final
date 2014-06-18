package next.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.Controller;

public class AddCommentController implements Controller {
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String questionIdbySTR = request.getParameter("questionId");
		long questionId = Long.parseLong(questionIdbySTR.trim());
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		

		AnswerDao anDao = new AnswerDao();
		Answer answer = new Answer(writer, contents, questionId);
		anDao.insert(answer);
		
		String url = "/show.next?questionId=" + questionIdbySTR;
		return url;
	}
}
