package oopsdesign.ludo.models;

public class LudoBoard {
    private Long id;
    private Cell[][] cells;
    private Cell[][] homeCells;

    public LudoBoard(Long id) {
        this.id = id;
        initiateBoardConfig();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell[][] getHomeCells() {
        return homeCells;
    }

    public void setHomeCells(Cell[][] homeCells) {
        this.homeCells = homeCells;
    }

    public void initiateBoardConfig(){
        cells = new Cell[4][13];
        homeCells = new Cell[4][6];
        initializeCellState(cells);
        intializeHomeCellState(homeCells);

    }

    private void intializeHomeCellState(Cell[][] homeCells) {
        for(int i=0; i<homeCells.length; i++){
            for(int j=0; j<homeCells[i].length; j++){
                Cell cell = homeCells[i][j];
                cell.setType(CellType.HOME);
            }
        }
        homeCells[Colour.YELLOW.val][5].setType(CellType.COMPLETED);
        homeCells[Colour.GREEN.val][5].setType(CellType.COMPLETED);
        homeCells[Colour.RED.val][5].setType(CellType.COMPLETED);
        homeCells[Colour.BLUE.val][5].setType(CellType.COMPLETED);
    }

    private void initializeCellState(Cell[][] cells) {
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[i].length; j++){
                Cell cell = cells[i][j];
                cell.setType(CellType.NORMAL);
            }
        }
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
