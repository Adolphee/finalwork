package ehb.adolphe.finalwork.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ehb.adolphe.finalwork.activities.MainActivity;

public class Quiz {
    @SerializedName("questions")
    @Expose
    private List<Question> questions;

    private Integer position = 0;
    private Integer score = 0;
    private Long experience = 0L;
    private Student opponent;
    private boolean previousCorrect = false;
    private Long bonusPoints = 0L;
    private Long initial_exp = Long.parseLong(""+ Math.round(MainActivity.AUTH_USER.getExperience() % 5000));

    public Long getSimpleScore(){
        Integer s = getScore();
        Integer m = getMaxPoints();
        Double myScore = Double.parseDouble(( (double) s / (double) m) + "") * 20;
        return Math.round(myScore);
    }


    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> value) { this.questions = value; }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public boolean nextQuestion(){
        if(position < questions.size()-1){
            position++;
            return true;
        }
        return false;
    }

    public void handleCorrectAnswer(){
        score += questions.get(position).getPoints();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isPreviousCorrect() {
        return previousCorrect;
    }

    public void setPreviousCorrect(boolean previousCorrect) {
        this.previousCorrect = previousCorrect;
    }

    public Long getExperienceGained() {
        return experience;
    }

    public Long addExperience(Long exp) {
        experience += exp;
        return exp;
    }

    public Student getOpponent() {
        return opponent;
    }

    public void setOpponent(Student opponent) {
        this.opponent = opponent;
    }

    public Integer getMaxPoints(){
        Integer max = 0;
        for(Question q: questions){
            max += q.getPoints();
        }
        return max;
    }

    public float getRating(){
        float stars = ((float) score / (float) getMaxPoints()) * 5;
        return stars;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public void addBonusPoints(long l) {
        addExperience(l);
        bonusPoints += l;
    }

    public Long getBonusPoints() {
        return bonusPoints;
    }

    public Long getInitialExperience() {
        return initial_exp;
    }

    public void setInitialExperience(Long initial_exp) {
        this.initial_exp = initial_exp;
    }
}