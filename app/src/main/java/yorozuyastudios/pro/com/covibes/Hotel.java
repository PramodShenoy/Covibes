package yorozuyastudios.pro.com.covibes;


import java.io.Serializable;

public class Hotel implements Serializable {

    String name;
    String location;
    String img_url;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Hotel(String name, String location, String img_url) {

        this.name = name;
        this.location = location;
        this.img_url = img_url;
    }

}
