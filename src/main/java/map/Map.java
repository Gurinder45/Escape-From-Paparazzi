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
		cell = new Cell[8];
		mapArray = new int[gFrame.columnNum][gFrame.rowNum];
		loadImages();
		loadMap();
	}

	public void loadBonusRewards(int num) {
		switch (num) {
			case 1:
				mapArray[20][20] = 5;
				break;
			case 2:
				mapArray[20][20] = 0;
				mapArray[7][18] = 5;
				break;
			case 3:
				mapArray[7][18] = 0;
				mapArray[19][5] = 5;
				break;
			case 4:
				mapArray[19][5] = 0;
				mapArray[1][1] = 5;
				break;
			case 5:
				mapArray[1][1] = 0;
				break;
		}
	}

	public void loadImages() {
		try {
			//floor
			cell[0] = new Cell();
			cell[0].img = ImageIO.read(getClass().getResourceAsStream("/images/floor_placeholder.png"));

			//wall
			cell[1] = new Cell();
			cell[1].img = ImageIO.read(getClass().getResourceAsStream("/images/wall_placeholder.png"));
			cell[1].setCollidable(true);

			//barrier //外側の四角
			cell[2] = new Cell();
			cell[2].img = ImageIO.read(getClass().getResourceAsStream("/images/barrier_placeholder.png"));
			cell[2].setCollidable(true);

			//disguise //t-shirt
			cell[3] = new Cell();
			cell[3].img = ImageIO.read(getClass().getResourceAsStream("/images/disguise.png"));
			cell[3].setCollidable(false);

			//punishment //camera
			cell[4] = new Cell();
			cell[4].img = ImageIO.read(getClass().getResourceAsStream("/images/camera.png"));
			cell[4].setCollidable(false);

			//bonus reward
			cell[5] = new Cell();
			cell[5].img = ImageIO.read(getClass().getResourceAsStream("/images/fan.png"));
			cell[5].setCollidable(false);

			//?
			cell[6] = new Cell();
			cell[6].img = ImageIO.read(getClass().getResourceAsStream("/images/floor_placeholder.png"));
			cell[6].setCollidable(true);

			cell[6] = new Cell();
			cell[6].img = ImageIO.read(getClass().getResourceAsStream("/images/floor_placeholder.png"));
			cell[6].setCollidable(true);

			cell[7] = new Cell();
			cell[7].img = ImageIO.read(getClass().getResourceAsStream("/images/laser.png"));
			cell[7].setCollidable(false);

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
