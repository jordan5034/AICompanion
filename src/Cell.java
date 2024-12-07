package games.AICompanion.src;

public class Cell {

    private boolean isObstacle;
    private boolean isGoal;
    private boolean isOccupied;
    private boolean isPath;

    private int row, col;

    public Cell(){
        this.isObstacle = false;
        this.isGoal = false;
        this.isOccupied = false;
        this.isPath = false;
    }

    public boolean isObstacle(){
        return isObstacle;
    }
    public void setObstacle(boolean isObstacle){
        this.isObstacle = isObstacle;
    }

    public boolean isGoal(){
        return isGoal;
    }
    public void setGoal(boolean isGoal){
        this.isGoal = isGoal;
    }

    public boolean isPath(){
        return isPath;
    }
    public void setPath(boolean path){
        this.isPath = path;
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    public boolean isOccupied(){
        return isOccupied;
    }
    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }

}
