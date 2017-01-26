package pojos;

import types.AnswerType;

/**
 *
 * @author Hp-Pc
 */
public class Answer {

    private int answerId;
    private String answerData;
    private AnswerType answerType;
    private int questionId;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerData() {
        return answerData;
    }

    public void setAnswerData(String answerData) {
        this.answerData = answerData;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Answer{" + "answerId=" + answerId + ", answerData=" + answerData + ", answerType=" + answerType + ", questionId=" + questionId + '}';
    }

}
