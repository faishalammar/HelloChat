package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

public class Message {
    private String username;
    private String date;
    private String details;

    public Message(String username, String date, String details) {
        this.username = username;
        this.date = date;
        this.details = details;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
