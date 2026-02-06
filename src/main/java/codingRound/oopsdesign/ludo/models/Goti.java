package codingRound.oopsdesign.ludo.models;

public class Goti {
    private Colour colour;
    private GotiStatus status;
    private Cell location;

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public GotiStatus getStatus() {
        return status;
    }

    public void setStatus(GotiStatus status) {
        this.status = status;
    }

    public Cell getLocation() {
        return location;
    }

    public void setLocation(Cell location) {
        this.location = location;
    }
}
