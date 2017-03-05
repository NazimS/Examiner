package controllers;

import db.DataManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import pojos.Answer;
import pojos.Question;
import pojos.Subject;

public class StudentController implements Initializable {

    @FXML
    private Button submit;
    @FXML
    private ComboBox<Subject> subjects;
    @FXML
    private Button previous;
    @FXML
    private Button next;
    @FXML
    private Label name;
    @FXML
    private Label question;
    @FXML
    private RadioButton variantA;
    @FXML
    private RadioButton variantB;
    @FXML
    private RadioButton variantC;
    @FXML
    private RadioButton variantD;

    @FXML
    private ToggleGroup group;

    private DataManager datamanager;
    private List< Question> questionList;
    private int currentQuestion;
    private List< Answer> answerList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datamanager = new DataManager();
        List<Subject> subjectList = datamanager.getSubjects();
        subjects.getItems().addAll(subjectList);
        //subjects.setValue(subjectList.get(0));//acilan kimi 0-ci secilmis kimi gosterir
        previous.setVisible(false);
    }

    public void refresquestionList() {
        questionList = datamanager.getQuestions(subjects.getValue());
        currentQuestion = 0;
        setCurrentQuestion();
        if (questionList.size() == 1) {
            next.setVisible(false);
        }
    }

    public void setCurrentQuestion() {
        question.setText(questionList.get(currentQuestion).getQuestionData());
        getCurrentAnswers();
    }

    public void goToNextQuestion() {
        if (currentQuestion < questionList.size() - 1) {
            currentQuestion++;
        }
        setCurrentQuestion();
        if (currentQuestion == questionList.size() - 1) {
            next.setVisible(false);
        }
        if (currentQuestion > 0) {
            previous.setVisible(true);
        }
    }

    public void goToPreviousQuestion() {
        if (currentQuestion > 0) {
            currentQuestion--;
        }
        setCurrentQuestion();
        if (currentQuestion == 0) {
            previous.setVisible(false);
        }
        if (currentQuestion < questionList.size() - 1) {
            next.setVisible(true);
        }
    }

    public void getCurrentAnswers() {
        answerList = datamanager.getAnswers(questionList.get(currentQuestion));
        //radiobuttonlari set etmek
        variantA.setUserData(answerList.get(0));
        variantA.setText(answerList.get(0).getAnswerData());

        variantB.setUserData(answerList.get(1));
        variantB.setText(answerList.get(1).getAnswerData());

        variantC.setUserData(answerList.get(2));
        variantC.setText(answerList.get(2).getAnswerData());

        variantD.setUserData(answerList.get(3));
        variantD.setText(answerList.get(3).getAnswerData());

    }

    public void selectAnswer() {
        if (group.getSelectedToggle() != null) {
            Answer selectedAnswer = (Answer) group.getSelectedToggle().getUserData();
            questionList.get(currentQuestion).setSelectedAnswerId(selectedAnswer.getAnswerId());
        }
    }

    public void submit() {

    }

    public void goTologin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            name.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
