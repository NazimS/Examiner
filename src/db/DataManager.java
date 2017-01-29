package db;

import pojos.Answer;
import pojos.Question;
import pojos.Subject;
import types.AnswerType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.User;
import types.UserTypes;

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
                tempAns.setAnswerType(AnswerType.parseFrom(resultSet.getString(3)));
                tempAns.setQuestionId(resultSet.getInt(4));
                answers.add(tempAns);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }

        return answers;
    }

    public User login(User user) {
        User foundedUser = null;
        try {
            connect();
            String query = "SELECT * FROM users u where u.email = ? and u.password = ? and u.enabled = 1 ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                foundedUser = new User();
                foundedUser.setUserId(resultSet.getInt(1));
                foundedUser.setName(resultSet.getString(2));
                foundedUser.setSurname(resultSet.getString(3));
                foundedUser.setUserType(UserTypes.parseFrom(resultSet.getString(4)));
                foundedUser.setEmail(resultSet.getString(5));
                foundedUser.setPassword(resultSet.getString(6));
                foundedUser.setEnabled((resultSet.getInt(7) == 1));
                foundedUser.setAttemptCount(resultSet.getByte(8));
                counter++;
            }
            if (counter == 1) {
                return foundedUser;
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return null;
    }

    public void countFails(User user) {
        try {
            connect();
            String query = "SELECT attempts FROM users u where u.email = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            resultSet = preparedStatement.executeQuery();
            int attempts = resultSet.next() ? resultSet.getInt(1) : -1;
            if(attempts >= 5){
                query = "update users u set u.attempts = 0  , u.enabled = 0 where u.email = ? ";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.execute();
            }
            else if (attempts >= 0) {
                attempts++;
                query = "update users u set u.attempts = ? where u.email = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, attempts);
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.execute();
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }

    }

}
