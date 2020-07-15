package remote.procedure.call.test;

import java.net.InetSocketAddress;

import remote.procedure.call.client.Client;
import remote.procedure.call.server.HelloService;
import remote.procedure.call.server.HelloServiceImpl;

public class RPCClientTest {
	public static void main(String[] args) throws Exception {
		//传递的是接口中的方法，与实例中的方法没有关系
		HelloService service = Client.getRemoteProxyObj(Class.forName("remote.procedure.call.server.HelloService"), new InetSocketAddress("127.0.0.1",9999));
		
		System.out.println(  service.sayHi("zs") );
		System.out.println(  service.testHi("lily", "恭喜你！终于通过接口调到另外一个方法")  );
	}

}
