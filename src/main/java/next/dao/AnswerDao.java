package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import next.model.Answer;
import next.support.db.ConnectionManager;

public class AnswerDao {

	public void insert(Answer answer) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
			String updateCount = "UPDATE QUESTIONS SET countOfComment = countOfComment + 1";
			pstmt = con.prepareStatement(sql);
			pstmt2 = con.prepareStatement(updateCount);
			pstmt.setString(1, answer.getWriter());
			pstmt.setString(2, answer.getContents());
			pstmt.setTimestamp(3, new Timestamp(answer.getTimeFromCreateDate()));
			pstmt.setLong(4, answer.getQuestionId());
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}		
	}

	public List<Answer> findAllByQuestionId(long questionId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? " + 
					"order by answerId desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, questionId);

			rs = pstmt.executeQuery();

			List<Answer> answers = new ArrayList<Answer>();
			Answer answer = null;
			while (rs.next()) {
				answer = new Answer(
						rs.getLong("answerId"),
						rs.getString("writer"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						questionId);
				answers.add(answer);
			}

			return answers;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
