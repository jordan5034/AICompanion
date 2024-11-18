public class Cell {

    private boolean isObstacle;
    private boolean isGoal;
    private boolean isOccupied;

    public Cell(){
        this.isObstacle = false;
        this.isGoal = false;
        this.isOccupied = false;
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

    public boolean isOccupied(){
        return isOccupied;
    }
    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }

}
