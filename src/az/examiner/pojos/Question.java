package az.examiner.pojos;

/**
 *
 * @author Hp-Pc
 */
public class Question {

    private int questionId;
    private String questionData;
    private int subjId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionData() {
        return questionData;
    }

    public void setQuestionData(String questionData) {
        this.questionData = questionData;
    }

    public int getSubjId() {
        return subjId;
    }

    public void setSubjId(int subjId) {
        this.subjId = subjId;
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", questionData=" + questionData + ", subjId=" + subjId + '}';
    }

}
