package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import ui.GameFrame;

public class Map {
	private GameFrame gFrame;
	private Cell[] cell;
	private int mapArray[][];

	public Map(GameFrame gFrame) {
		this.gFrame = gFrame;
		cell = new Cell[10];
		mapArray = new int[gFrame.columnNum][gFrame.rowNum];
		loadImages();
		loadMap();
	}

	public void loadBonusRewards(int num) {
		switch (num) {
			case 1:
				mapArray[29][17] = 5;
				break;
			case 2:
				mapArray[29][17] = 0;
				mapArray[8][14] = 5;
				break;
			case 3:
				mapArray[8][14] = 0;
				mapArray[22][3] = 5;
				break;
			case 4:
				mapArray[22][3] = 0;
				mapArray[5][4] = 5;
				break;
			case 5:
				mapArray[5][4] = 0;
				break;
		}
	}

	public void loadImages() {
		try {
			// floor
			cell[0] = new Cell();
			cell[0].img = ImageIO.read(getClass().getResourceAsStream("/images/floor_placeholder.png"));
			cell[0].setCollidable(false);

			// wall (This separates rooms)
			cell[1] = new Cell();
			cell[1].img = ImageIO.read(getClass().getResourceAsStream("/images/wall.png"));
			cell[1].setCollidable(true);

			// barrier
			cell[2] = new Cell();
			cell[2].img = ImageIO.read(getClass().getResourceAsStream("/images/barrier.png"));
			cell[2].setCollidable(true);

			// disguise //t-shirt
			cell[3] = new Cell();
			cell[3].img = ImageIO.read(getClass().getResourceAsStream("/images/disguise.png"));
			cell[3].setCollidable(false);

			// camera(just a regular bblock/barrier)
			cell[4] = new Cell();
			cell[4].img = ImageIO.read(getClass().getResourceAsStream("/images/camera.png"));
			cell[4].setCollidable(false);

			// bonus reward (a fan wanting a signiture from the celeb)
			cell[5] = new Cell();
			cell[5].img = ImageIO.read(getClass().getResourceAsStream("/images/fan.png"));
			cell[5].setCollidable(false);

			// blocks for goal
			cell[6] = new Cell();
			cell[6].img = ImageIO.read(getClass().getResourceAsStream("/images/floor_placeholder.png"));
			cell[6].setCollidable(true);

			// laser (punishment)
			cell[7] = new Cell();
			cell[7].img = ImageIO.read(getClass().getResourceAsStream("/images/laser.png"));
			cell[7].setCollidable(false);

			// wall_2 //separator
			cell[8] = new Cell();
			cell[8].img = ImageIO.read(getClass().getResourceAsStream("/images/wall_2.png"));
			cell[8].setCollidable(true);

			// barrier 2 (clothing racks)
			cell[9] = new Cell();
			cell[9].img = ImageIO.read(getClass().getResourceAsStream("/images/barrier_2.png"));
			cell[9].setCollidable(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap() {
		try {
			InputStream inpStrm = getClass().getResourceAsStream("/map/map.txt");
			BufferedReader bReader = new BufferedReader(new InputStreamReader(inpStrm));

			int col = 0;
			int row = 0;

			while (col < gFrame.columnNum && row < gFrame.rowNum) {
				String line = bReader.readLine();

				while (col < gFrame.columnNum) {
					String nums[] = line.split(" ");
					int num = Integer.parseInt(nums[col]);
					mapArray[col][row] = num;
					col++;

				}
				if (col == gFrame.columnNum) {
					col = 0;
					row++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2d) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		int cellType;

		while (col < gFrame.columnNum && row < gFrame.rowNum) {
			cellType = mapArray[col][row];
			g2d.drawImage(cell[cellType].img, x, y, gFrame.cellSize, gFrame.cellSize, null);
			col++;
			x += gFrame.cellSize;

			if (col == gFrame.columnNum) {
				col = 0;
				x = 0;
				row++;
				y += gFrame.cellSize;
			}
		}
	}

	public int[][] getMapArray() {
		return mapArray;
	}

	public boolean checkColidable(int type) {
		return cell[type].getCollidable();
	}
}
