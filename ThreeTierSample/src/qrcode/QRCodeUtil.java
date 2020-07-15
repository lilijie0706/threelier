package qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class QRCodeUtil {
	
	public void encoderQRCode(String content,String imgPath,String imgType,int size) throws Exception {
		
		BufferedImage bufImg=qRcodeCommon(content,imgType,size);
		File file=new File(imgPath);
		ImageIO.write(bufImg, imgType, file);
		
	}
	
	public BufferedImage qRcodeCommon(String content,String imgType,int size) throws Exception{
		
		BufferedImage bufImg = null;
		Qrcode qrCodeHandler = new Qrcode();
		qrCodeHandler.setQrcodeErrorCorrect('M');
		qrCodeHandler.setQrcodeEncodeMode('B');
		qrCodeHandler.setQrcodeVersion(size);
		byte[] contentBytes = content.getBytes("UTF-8");
		boolean[][] codeOut = qrCodeHandler.calQrcode(contentBytes);//"Hello world"->byte[] "Hello world"
		
		int imgSize = 67+12*(size-1);
		
		
		
		bufImg=new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufImg.createGraphics();//ctrl+1 返回值
		gs.setBackground(Color.white);
		gs.clearRect(0, 0, imgSize, imgSize);
		gs.setColor(Color.black);
		int pixoff=2;
		
		for(int i=0;i<codeOut.length;i++) {
			for(int j=0;j<codeOut.length;j++) {
				if(codeOut[i][j]) {
					gs.fillRect(i*3+pixoff, j*3+pixoff, 3, 3);
				}
				
			}
		}
		Image logo = ImageIO.read(new File("src/logo.png"));
		int maxHeight=bufImg.getHeight();
		int maxWidth=bufImg.getWidth();
		gs.drawImage(logo, imgSize/5*2, imgSize/5*2,maxWidth/5,maxHeight/5, null);

		gs.dispose();
		bufImg.flush();
		return bufImg;
	}
	//解密 二维码（图片）-> 文字信息
	public String decoderQRCode(String imgPath) throws Exception{
		
		BufferedImage bufImg = ImageIO.read(new File(imgPath));
		QRCodeDecoder decoder = new QRCodeDecoder();
		TwoDimensionCodeImage tdcImage = new TwoDimensionCodeImage(bufImg);
		byte[] bs = decoder.decode(tdcImage);
		String content=new String(bs,"utf-8");
		return content;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
