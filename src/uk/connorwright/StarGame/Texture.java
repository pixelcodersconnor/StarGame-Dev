package uk.connorwright.StarGame;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;



public class Texture {
	public static BufferedImage launcherbg = loadBitmap("/launcher_background.png");

	public static BufferedImage loadBitmap(String fileName) {
		try {
			BufferedImage image = ImageIO.read(Texture.class
					.getResource(fileName));
			int width = image.getWidth();
			int height = image.getHeight();
			BufferedImage result = new BufferedImage(width, height, 0);
			image.getRGB(0, 0);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}