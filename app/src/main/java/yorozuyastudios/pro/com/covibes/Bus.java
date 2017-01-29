package yorozuyastudios.pro.com.covibes;


import java.io.Serializable;

public class Bus implements Serializable{

    String dep;
    String type;
    String arr;
    String operator;
    String rating;
    String duration;

    public Bus(String dep, String type, String arr, String operator, String rating, String duration) {
        this.dep = dep;
        this.type = type;
        this.arr = arr;
        this.operator = operator;
        this.rating = rating;
        this.duration = duration;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
