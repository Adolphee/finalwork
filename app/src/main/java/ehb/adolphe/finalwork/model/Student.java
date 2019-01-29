
package ehb.adolphe.finalwork.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Student {

    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("fieldOfStudy")
    @Expose
    private String fieldOfStudy;
    @SerializedName("progressyear")
    @Expose
    private Integer progressyear;
    @SerializedName("birthDate")
    @Expose
    private Object birthDate;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private Object salt;
    @SerializedName("rank")
    @Expose
    private Object rank;
    @SerializedName("experience")
    @Expose
    private Object experience;
    @SerializedName("slogan")
    @Expose
    private Object slogan;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("teacher")
    @Expose
    private Boolean teacher;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Integer getProgressyear() {
        return progressyear;
    }

    public void setProgressyear(Integer progressyear) {
        this.progressyear = progressyear;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getSalt() {
        return salt;
    }

    public void setSalt(Object salt) {
        this.salt = salt;
    }

    public Object getRank() {
        return rank;
    }

    public void setRank(Object rank) {
        this.rank = rank;
    }

    public Object getExperience() {
        return experience;
    }

    public void setExperience(Object experience) {
        this.experience = experience;
    }

    public Object getSlogan() {
        return slogan;
    }

    public void setSlogan(Object slogan) {
        this.slogan = slogan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getTeacher() {
        return teacher;
    }

    public void setTeacher(Boolean teacher) {
        this.teacher = teacher;
    }


}
