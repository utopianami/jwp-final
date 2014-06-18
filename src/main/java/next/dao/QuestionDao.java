package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import next.model.Question;
import next.support.db.ConnectionManager;

public class QuestionDao {

	public void insert(Question inputQuestion) throws SQLException {
		final Question question = inputQuestion;
		final String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfComment) VALUES (?, ?, ?, ?, ?)";

		DBaccessDao db = new DBaccessDao() {
			
			@Override
			public Object executeQuery() {
				PreparedStatement pstmt = null;
				try{
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, question.getWriter());
					pstmt.setString(2, question.getTitle());
					pstmt.setString(3, question.getContents());
					pstmt.setTimestamp(4, new Timestamp(question.getTimeFromCreateDate()));
					pstmt.setInt(5, question.getCountOfComment());

					pstmt.executeUpdate();

				}catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (pstmt != null) { 
						try {
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				return null;
			}
		};
	}

	public List findAll() throws SQLException {
		final String sql = "SELECT questionId, writer, title, createdDate, countOfComment FROM QUESTIONS " + 
				" order by questionId desc";
		
		DBaccessDao db = new DBaccessDao() {
			@Override
			public Object executeQuery() {
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<Question> questions = new ArrayList<Question>();
				Question question = null;
				try{
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						question = new Question(
								rs.getLong("questionId"),
								rs.getString("writer"),
								rs.getString("title"),
								null,
								rs.getTimestamp("createdDate"),
								rs.getInt("countOfComment"));
						questions.add(question);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (pstmt != null) { 
						try {
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				return question;
			}
		};
		return (List)db.getResult();
	}

	public Question findById(long inputQuestionId) throws SQLException {
		final long questionId = inputQuestionId;
		final String sql = "SELECT questionId, writer, title, contents, createdDate, countOfComment FROM QUESTIONS " + 
				"WHERE questionId = ?";
		
		DBaccessDao db = new DBaccessDao(){
			@Override
			public Object executeQuery() {
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				Question question = null;
				try{
					pstmt = conn.prepareStatement(sql);
					pstmt.setLong(1, questionId);

					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						question = new Question(
								rs.getLong("questionId"),
								rs.getString("writer"),
								rs.getString("title"),
								rs.getString("contents"),
								rs.getTimestamp("createdDate"),
								rs.getInt("countOfComment"));
					}
				}catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (pstmt != null) { 
						try {
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				return question;
			}
		};
		return (Question)db.getResult();
	}
}

