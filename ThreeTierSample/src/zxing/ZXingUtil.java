package zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.InvertedLuminanceSource;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import jp.sourceforge.qrcode.util.Color;




public class ZXingUtil {
	public static void encodeImg(String imgPath,String format,String content,int width,int height,String logo) throws Exception {
		
		File file=new File(imgPath);
		
			 
		Hashtable<EncodeHintType, Object> hints=new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.H );
		hints.put(EncodeHintType.CHARACTER_SET,"utf-8" );
		hints.put(EncodeHintType.MARGIN,1 );
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				img.setRGB(x, y,   (bitMatrix.get(x, y)? Color.BLACK:Color.WHITE) );			
			}
		}
		//画logo
		img=LogoUtil.logoMatrix(img, logo);
		
		ImageIO.write(img, format, file);
		
		
		
	}

	public static void decodeImg(File file) throws Exception {
		if(!file.exists()) return;
		
		MultiFormatReader formatReader = new MultiFormatReader();
		BufferedImage imge = ImageIO.read(file);
		
		//new BufferedImageLuminanceSource();
		LuminanceSource source= new BufferedImageLuminanceSource(imge);
		
		HybridBinarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Map map=new HashMap();
		map.put(EncodeHintType.CHARACTER_SET, "utf-8");
		Result result = formatReader.decode(binaryBitmap,map);
		System.out.println("解析结果为："+result.toString());
	}
	
	

}
