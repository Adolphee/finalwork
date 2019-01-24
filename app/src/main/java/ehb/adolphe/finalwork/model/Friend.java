package ehb.adolphe.finalwork.model;

public class Friend {
    private String fname;
    private String lname;
    private String email;
    private String city;
    private String studies;

    public Friend(String fname, String lname, String email, String city, String studies) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.city = city;
        this.studies = studies;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }
}
