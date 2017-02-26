/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pojos.Answer;
import pojos.Question;
import pojos.Subject;
import types.AnswerType;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class TeacherController implements Initializable {
    
    @FXML
    private ComboBox<Subject> subjects;
    @FXML
    private TextArea question;
    @FXML
    private TextField variantA;
    @FXML
    private TextField variantB;
    @FXML
    private TextField variantC;
    @FXML
    private TextField variantD;
    @FXML
    private Label messages;
    @FXML
    private CheckBox answerTypeA;
    @FXML
    private CheckBox answerTypeB;
    @FXML
    private CheckBox answerTypeC;
    @FXML
    private CheckBox answerTypeD;
    ///////////////////////////////////
    private DataManager dataManager;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataManager = new DataManager();
        List<Subject> subjectList = dataManager.getSubjects();
        subjects.getItems().addAll(subjectList);
        subjects.setValue(subjectList.get(0));
    }
    
    @FXML
    private void addQuestion() {
        Question currentQuestion = new Question();
        currentQuestion.setQuestionData(question.getText());
        currentQuestion.setSubjId(subjects.getValue().getSubjectId());
         if (currentQuestion.getQuestionData().length()< 10) {
             messages.setTextFill(Color.RED);
          messages.setText("Your question is not added");
          return;
         }
        long questionId = dataManager.insertQuestion(currentQuestion);
        if (questionId == -1) {
            
            messages.setText("Your question is not added");
            messages.setTextFill(Color.RED);
        } else {
            Answer answerA = new Answer();
            Answer answerB = new Answer();
            Answer answerC = new Answer();
            Answer answerD = new Answer();
            
            answerA.setAnswerData(variantA.getText());
            answerA.setAnswerType(AnswerType.parseFrom(answerTypeA.isSelected()));
            answerA.setQuestionId(questionId);
            
            answerB.setAnswerData(variantB.getText());
            answerB.setAnswerType(AnswerType.parseFrom(answerTypeB.isSelected()));
            answerB.setQuestionId(questionId);
            
            answerC.setAnswerData(variantC.getText());
            answerC.setAnswerType(AnswerType.parseFrom(answerTypeC.isSelected()));
            answerC.setQuestionId(questionId);
            
            answerD.setAnswerData(variantD.getText());
            answerD.setAnswerType(AnswerType.parseFrom(answerTypeD.isSelected()));
            answerD.setQuestionId(questionId);
            
            dataManager.insertAnswer(answerA);
            dataManager.insertAnswer(answerB);
            dataManager.insertAnswer(answerC);
            dataManager.insertAnswer(answerD);
            
            messages.setText("Question successfully added");
            messages.setTextFill(Color.GREEN);
        }
    }
    
    @FXML
    public void refreshMessage() {
        messages.setText("");
    }
    
      public void goTologin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            question.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
    
}
