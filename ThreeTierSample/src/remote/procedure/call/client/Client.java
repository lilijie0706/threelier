package remote.procedure.call.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.student.entity.override;

public class Client {
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxyObj(Class serviceInterface,InetSocketAddress addr) {
		
	/*	String str = new String();
		String[] str = new String[] {"aaa","bbb","ccc"}
		匿名实现类
		new InvocationHandler(){
				@override
				public Object invoke(Object proxy, Method method, Object[] args)
				        throws Throwable{}
				        }
		*/
		
		
		return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[] {serviceInterface}, new InvocationHandler(){
				@override
				public Object invoke(Object proxy, Method method, Object[] args)
				        throws Throwable{
					Socket socket = new Socket();
					ObjectInputStream input = null;
					ObjectOutputStream output = null;
					
					try {
					socket.connect(addr);
					output = new ObjectOutputStream(socket.getOutputStream());
					//发送接口名字 方法名；方法参数类型及参数列表
					output.writeUTF(serviceInterface.getName());
					output.writeUTF(method.getName());
					output.writeObject(method.getParameterTypes());
					output.writeObject(args);
					//等待服务端处理。。。。。。
					//接收服务端处理后的返回值
					input = new ObjectInputStream(socket.getInputStream());
					
					return input.readObject();
					}catch(Exception e) {
						e.printStackTrace();
						return null;
					}finally {
						try {
							if(output != null) output.close();
							if(input != null) input.close();
							/*if(socket != null) socket.close();
							if(server != null) server.close();*/
							}catch(Exception e) {
								e.printStackTrace();
							}
						
					}
					
					
					
					
				}
				
		});
	}

}
