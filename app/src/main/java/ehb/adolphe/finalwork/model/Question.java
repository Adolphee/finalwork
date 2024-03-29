package ehb.adolphe.finalwork.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("points")
    @Expose
    private long points;
    @SerializedName("answers")
    @Expose
    private List<Answer> answers;
    @SerializedName("active")
    @Expose
    private boolean active;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getQuestion() { return question; }
    public void setQuestion(String value) { this.question = value; }

    public long getPoints() { return points; }
    public void setPoints(long value) { this.points = value; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> value) { this.answers = value; }

    public boolean getActive() { return active; }
    public void setActive(boolean value) { this.active = value; }
}