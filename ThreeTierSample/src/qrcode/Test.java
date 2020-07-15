package qrcode;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String imgPath="src/二维码.png";
		String content = "我15分钟后回家吃饭";
		
		QRCodeUtil qrUtil = new QRCodeUtil(); 
		qrUtil.encoderQRCode(content, imgPath,"png",17);
		String imgContent = qrUtil.decoderQRCode(imgPath);
		System.out.println(imgContent);
		System.out.println("ceshijiema");
		

	}

}
