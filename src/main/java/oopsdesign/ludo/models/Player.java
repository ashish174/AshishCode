package oopsdesign.ludo.models;

import java.util.List;

public class Player {
    private String userName;
    private Goti[] gotis;
    private Colour colour;

    public Player(Person person) {
        userName = person.getUserName();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Goti[] getGotis() {
        return gotis;
    }

    public void setGotis(Goti[] gotis) {
        this.gotis = gotis;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }
}
