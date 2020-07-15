package zxing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class LogoUtil {
	public static BufferedImage logoMatrix(BufferedImage matrixImage,String logo) throws Exception {
		Graphics2D g2 = matrixImage.createGraphics();
		BufferedImage logoImg=ImageIO.read(new File(logo));
		int height =matrixImage.getHeight();
		int width =matrixImage.getWidth();
		g2.drawImage(logoImg, width*2/5, height*2/5, width/5, height/5, null);
		BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke);
		RoundRectangle2D round = new RoundRectangle2D.Float(width*2/5, height*2/5, width/5, height/5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		
		g2.setColor(Color.WHITE);
		g2.draw(round);
		
		BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke2);
		RoundRectangle2D round2 = new RoundRectangle2D.Float(2+width*2/5, 2+height*2/5, width/5-4, height/5-4,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setColor(Color.gray);
		g2.draw(round2);
		
		g2.dispose();
		matrixImage.flush();

		
		return matrixImage;
	}

}
