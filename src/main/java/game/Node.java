package game;

/**
 * Nodes for a* searching algorithm.
 *
 * @author julio patrick Asifiwe
 */

public class Node {
    public boolean isCollidable;
    public boolean open;
    public boolean checked;
    public Node parent;
    public int columnNum;
    public int rowNum;
    public int gCost;
    public int hCost;
    public int fCost;

    public Node(int column, int row) {
        this.columnNum = column;
        this.rowNum = row;
    }

}
