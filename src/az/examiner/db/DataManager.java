package az.examiner.db;

import az.examiner.pojos.Answer;
import az.examiner.pojos.Question;
import az.examiner.pojos.Subject;
import az.examiner.types.AnswerType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp-Pc
 */
public class DataManager {

    public DataManager() {
    }

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private void connect() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://localhost:3306/exam_db";
        String user = "root";
        String password = "root";
        Class.forName(driver);
        connection = (Connection) DriverManager.getConnection(connectionUrl, user, password);
    }

    private void disconnect() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public List<Subject> getSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try {
            connect();
            String query = "SELECT * FROM SUBJECTS ORDER BY 1 ASC";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Subject tempSubj = new Subject();
                tempSubj.setSubjectId(resultSet.getInt(1));
                tempSubj.setSubjectName(resultSet.getString(2));
                subjects.add(tempSubj);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        return subjects;
    }

    public List<Question> getQuestions(Subject subject) {
        List<Question> questions = new ArrayList<>();
        try {
            connect();
            String query = " SELECT * FROM QUESTIONS Q WHERE Q.SUBJ_ID = ? ORDER BY 1 ASC ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, subject.getSubjectId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question tempQuest = new Question();
                tempQuest.setQuestionId(resultSet.getInt(1));
                tempQuest.setQuestionData(resultSet.getString(2));
                tempQuest.setSubjId(resultSet.getInt(3));
                questions.add(tempQuest);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }

        return questions;
    }

    public List<Answer> getAnswers(Question question) {
        List<Answer> answers = new ArrayList<>();
        try {
            connect();
            String query = "SELECT * FROM ANSWERS A WHERE A.QUEST_ID = ? ORDER BY 1 ASC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, question.getQuestionId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer tempAns = new Answer();
                tempAns.setAnswerId(resultSet.getInt(1));
                tempAns.setAnswerData(resultSet.getString(2));
                tempAns.setAnswerType(AnswerType.TRUE);
                tempAns.setQuestionId(resultSet.getInt(4));
                answers.add(tempAns);

            }
            

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return answers;
    }

    public static void main(String[] args) {

        DataManager dataManager = new DataManager();
        List<Subject> sj = dataManager.getSubjects();
        for (Subject s : sj) {
            System.out.println(s.getSubjectName());
        }

        List<Question> qs = dataManager.getQuestions(sj.get(1));
        for (Question q : qs) {
            System.out.println(q);
        }
    }

    private Answer Answer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
