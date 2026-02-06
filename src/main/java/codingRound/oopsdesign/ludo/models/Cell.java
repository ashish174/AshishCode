package codingRound.oopsdesign.ludo.models;

public class Cell {
    private int num;
    private CellType type;
    private Goti[] gotis;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public Goti[] getGotis() {
        return gotis;
    }

    public void setGotis(Goti[] gotis) {
        this.gotis = gotis;
    }
}
