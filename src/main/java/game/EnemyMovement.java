package game;

import java.util.ArrayList;

import javax.sound.midi.Track;

import ui.GameFrame;
import ui.GamePanel;

/**
 * @author julio patrick Asifiwe
 * handles the movements of the enemy when following the celebrity
 *
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
     * clears everything and loads all nodes 
     */
    public void clearAll() {
        opened.clear();
        path.clear();
        succeeded = false;
        loadNodes();

    }

    /**
     * loading all the nodes or colunms from the frame 
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
     * reseting all the nodes to new
     */
    public void resetNodes() {
        int column = 0;
        int row = 0;

        while (column < gFrame.columnNum && row < gFrame.rowNum) {
            // iterate through the nodes and make them back to their original state
            nodes[column][row].open = false;
            nodes[column][row].checked = false;
            nodes[column][row].solid = false;
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
     * @param startColum of where the enemy will start
     * @param startRow of where the enemy will start
     * @param endColumn of where the goal or celebrity will be
     * @param endRow of where the goal or celebrity will be
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
                nodes[column][row].solid = true;
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
     * calculating how far the node to be found is
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
     * @return true if u succeeded to find the goal
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
     * 
     * @param node to be opened 
     */
    public void openNode(Node node) {
        if (node.open == false && node.checked == false && node.solid == false) {
            node.open = true;
            node.parent = cur;
            opened.add(node);

        }
    }

    /**
     * tracking the path of the enemy
     */
    public void trackPath() {
        Node node = end;

        while (node != start) {
            path.add(0, node);
            node = node.parent;
        }
    }

    /**
     * @return the next column to to current position
     */
    public int getNextColumn() {
        return path.get(0).columnNum;
    }

    /**
     * @return the next row to current position
     */
    public int getNextRow() {
        return path.get(0).rowNum;
    }

}
