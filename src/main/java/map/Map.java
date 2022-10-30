package map;

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
		cell = new Cell[3];
		mapArray = new int[gFrame.columnNum][gFrame.rowNum];
		loadImages();
		loadMap();
	}

	public void loadImages() {
		try {
			cell[0] = new Cell();
			cell[0].img = ImageIO.read(getClass().getResourceAsStream("/images/floor_placeholder.png"));
			
			cell[1] = new Cell();
			cell[1].img = ImageIO.read(getClass().getResourceAsStream("/images/wall_placeholder.png"));
			
			cell[2] = new Cell();
			cell[2].img = ImageIO.read(getClass().getResourceAsStream("/images/barrier_placeholder.png"));
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
				
				while(col < gFrame.columnNum) {
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
			
		} catch(Exception e) {
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
}
