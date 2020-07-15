package remote.procedure.call.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import remote.procedure.call.server.HelloService;
import remote.procedure.call.server.HelloServiceImpl;
import remote.procedure.call.server.Server;
import remote.procedure.call.server.ServerCenter;



public class RPCServerTest {
	public static void main(String[] args) throws Exception {
		
		//开启一个线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//服务中心
				Server server = new ServerCenter(9999);
				server.register(HelloService.class, HelloServiceImpl.class);
				try {
					server.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
			
		}).start();
		
	}

}
