package pullRequestUtil;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class pullRequest {

    private String title;
    private String body;
    private String base;
    private String head;
    private int issue;
    private String state;
    private int number;

    public String getTitle() {
        return title;
    }

    public String getBody() {return body; }

    public String getBase() {
        return base;
    }

    public String getHead() {
        return head;
    }

    public int getIssue(){return issue;}

    public String getState(){return state;}

    public int getNumber() {return number;}



    public String setTitle() { return title;    }

    public String setBody() {return body; }

    public String setBase() {
        return base;
    }

    public String setHead() {return head; }

    public int setIssue() {return issue;}

    public String setState() {return state;}

    public int setNumber() {return number;}










}
