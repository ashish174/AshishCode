package oopsdesign.ludo.models;

public class LudoBoard {
    private Long id;
    private Cell[][] cells;
    private Cell[][] homeCells;

    public LudoBoard(Long id) {
        this.id = id;
        initiateBoardConfig();
    }

    public void initiateBoardConfig(){
        cells = new Cell[4][13];
        homeCells = new Cell[4][6];
        homeCells[Colour.YELLOW.val][5].setType(CellType.COMPLETED);
        homeCells[Colour.GREEN.val][5].setType(CellType.COMPLETED);
        homeCells[Colour.RED.val][5].setType(CellType.COMPLETED);
        homeCells[Colour.BLUE.val][5].setType(CellType.COMPLETED);
        cells[Colour.YELLOW.val][0].setType(CellType.REST);
        cells[Colour.YELLOW.val][8].setType(CellType.REST);
        cells[Colour.GREEN.val][0].setType(CellType.REST);
        cells[Colour.GREEN.val][8].setType(CellType.REST);
        cells[Colour.RED.val][0].setType(CellType.REST);
        cells[Colour.RED.val][8].setType(CellType.REST);
        cells[Colour.BLUE.val][0].setType(CellType.REST);
        cells[Colour.BLUE.val][8].setType(CellType.REST);
    }




}
