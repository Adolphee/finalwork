package ehb.adolphe.finalwork.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("what")
    @Expose
    private String what;
    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("correct")
    @Expose
    private boolean correct;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getWhat() { return what; }
    public void setWhat(String value) { this.what = value; }

    public boolean getActive() { return active; }
    public void setActive(boolean value) { this.active = value; }

    public boolean getCorrect() { return correct; }
    public void setCorrect(boolean value) { this.correct = value; }
}
