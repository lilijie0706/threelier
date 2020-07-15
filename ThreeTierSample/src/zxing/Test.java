package zxing;

import java.io.File;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String imgPath = "src/二维码1.gif";
		String content = "hello world中文你好吗";
		String logo="src/logo.png";
		
//		生成图片
		ZXingUtil.encodeImg(imgPath, "gif", content, 430, 430,logo);
		
//		解码
		ZXingUtil.decodeImg(new File(imgPath));

	}

}
