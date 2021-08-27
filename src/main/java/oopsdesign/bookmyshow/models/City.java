package oopsdesign.bookmyshow.models;

import java.util.List;

public class City {
    private Long id;
    private String name;

    List<Theater> theaters;

    public List<Theater> getTheaters(){
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
