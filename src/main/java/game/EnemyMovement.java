package game;

import java.util.ArrayList;

import ui.GameFrame;
import ui.GamePanel;

/**
 * implement A* search algorithm to find the main character
 * 
 * @author julio patrick Asifiwe
 * @author Gurinder Bhogal
 */
public class EnemyMovement {
    private GameFrame gFrame;
    private Node[][] nodes;
    private ArrayList<Node> opened;
    private boolean succeeded;
    private ArrayList<Node> path;
    private Node start, end, cur;

    public EnemyMovement(GameFrame gFrame) {
        this.gFrame = gFrame;
        this.opened = new ArrayList<Node>();
        this.path = new ArrayList<Node>();
        loadNodes();
    }

    /**
     * clears all lists and loads nodes again
     */
    public void clearAll() {
        opened.clear();
        path.clear();
        succeeded = false;
        loadNodes();

    }

    /**
     * Makes a 2 dimensional array of nodes the same size as the map
     */
    public void loadNodes() {
        nodes = new Node[gFrame.columnNum][gFrame.rowNum];
        int column = 0;
        int row = 0;

        while (column < gFrame.columnNum && row < gFrame.rowNum) {
            nodes[column][row] = new Node(column, row);
            column++;

            if (column == gFrame.columnNum) {
                column = 0;
                row++;
            }
        }
    }

    /**
     * Sets the start and end rows and columns, sets the isCollidable nodes, and
     * gets their
     * total cost
     * 
     * @param startColum starting column of the enemy
     * @param startRow   starting row of the enemy
     * @param endColumn  the column of the destination
     * @param endRow     the row ow the destination
     */
    public void setNodes(int startColum, int startRow, int endColumn, int endRow) {
        resetNodes();

        start = nodes[startColum][startRow];
        cur = start;
        end = nodes[endColumn][endRow];
        opened.add(cur);

        int column = 0;
        int row = 0;
        GamePanel gPanel = gFrame.getGamePanel();

        while (column < gFrame.columnNum && row < gFrame.rowNum) {
            int[][] mapArray = gPanel.getMapArray();
            // check if the cell is Collidable
            if (mapArray[column][row] == 1 || mapArray[column][row] == 2 || mapArray[column][row] == 6
                    || mapArray[column][row] == 8 || mapArray[column][row] == 9) { ///////
                nodes[column][row].isCollidable = true;
            }
            getTotalCost(nodes[column][row]);
            column++;
            if (column == gFrame.columnNum) {
                column = 0;
                row++;
            }
        }
    }

    /**
     * Finds the nodes g-cost and h-cost and adds them to get its f-cost
     * 
     * @param node to be looked for
     */
    public void getTotalCost(Node node) {
        int horizontalDist = Math.abs(node.columnNum - start.columnNum);
        int verticalDist = Math.abs(node.rowNum - start.rowNum);
        node.gCost = horizontalDist + verticalDist;

        horizontalDist = Math.abs(node.columnNum - end.columnNum);
        verticalDist = Math.abs(node.rowNum - end.rowNum);
        node.hCost = horizontalDist + verticalDist;

        node.fCost = node.gCost + node.hCost;
    }

    /**
     * Searches until it finds a path to the player
     * 
     * @return boolean true once succeeded
     */
    public boolean search() {
        while (succeeded == false) {
            int column = cur.columnNum;
            int row = cur.rowNum;

            cur.checked = true;
            opened.remove(cur);
            // open the node above
            // if statement to check we aren't leaving the map
            if (row - 1 >= 0) {
                openNode(nodes[column][row - 1]);
            }
            // open the node on the left
            if (column - 1 >= 0) {
                openNode(nodes[column - 1][row]);
            }
            // open the node below
            if (row + 1 < gFrame.rowNum) {
                openNode(nodes[column][row + 1]);
            }
            // open the node on the right
            if (column + 1 < gFrame.columnNum) {
                openNode(nodes[column + 1][row]);
            }

            int optimalIndex = 0;
            int optimalCost = 999;

            for (int i = 0; i < opened.size(); i++) {
                // find the optimal cost
                if (opened.get(i).fCost < optimalCost) {
                    optimalIndex = i;
                    optimalCost = opened.get(i).fCost;
                }
                // in the case where f cost is the same, check G cost
                else if (opened.get(i).fCost == optimalCost) {
                    if (opened.get(i).gCost < opened.get(optimalIndex).gCost) {
                        optimalIndex = i;
                    }
                }
            }
            if (opened.size() == 0) {
                break;
            }
            cur = opened.get(optimalIndex);
            if (cur == end) {
                succeeded = true;
                trackPath();
            }
        }
        return succeeded;
    }

    /**
     * Marks and tracks nodes which haven't been looked at
     * 
     * @param node node to open
     */
    public void openNode(Node node) {
        if (node.open == false && node.checked == false && node.isCollidable == false) {
            node.open = true;
            node.parent = cur;
            opened.add(node);

        }
    }

    /**
     * loads the successful path into the path ArrayList
     */
    public void trackPath() {
        Node node = end;

        while (node != start) {
            path.add(0, node);
            node = node.parent;
        }
    }

    /**
     * Resets all the boolean values in the nodes
     */
    public void resetNodes() {
        int column = 0;
        int row = 0;

        while (column < gFrame.columnNum && row < gFrame.rowNum) {
            // iterate through the nodes and make them back to their original state
            nodes[column][row].open = false;
            nodes[column][row].checked = false;
            nodes[column][row].isCollidable = false;
            column++;
            if (column == gFrame.columnNum) {
                column = 0;
                row++;
            }
        }
        opened.clear();
        path.clear();
        succeeded = false;
    }

    /**
     * Get the next column in the path
     * 
     * @return index representing the column
     */
    public int getNextColumn() {
        return path.get(0).columnNum;
    }

    /**
     * Get the next row in the path
     * 
     * @return index representing the row
     */
    public int getNextRow() {
        return path.get(0).rowNum;
    }

    public boolean getSuceeded() {
        return succeeded;
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    public ArrayList<Node> getOpened() {
        return opened;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public Node getCurrentNode() {
        return cur;
    }

}
