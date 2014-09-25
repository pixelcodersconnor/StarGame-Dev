package uk.connorwright.StarGame;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	//define variables
	private BufferedImage image;

	public BufferedImage loadImage(String path) throws IOException{

		image = ImageIO.read(getClass().getResource(path));
		return image;
	}

}
