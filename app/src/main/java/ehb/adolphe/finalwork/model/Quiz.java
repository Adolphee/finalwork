package ehb.adolphe.finalwork.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quiz {
    @SerializedName("questions")
    @Expose
    private List<Question> questions;

    private Integer position = 0;


    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> value) { this.questions = value; }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public boolean nextQuestion(){
        if(position < questions.size()){
            position++;
            return true;
        }
        return false;
    }
}