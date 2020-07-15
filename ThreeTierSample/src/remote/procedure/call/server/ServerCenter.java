package remote.procedure.call.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.student.entity.override;



public class ServerCenter implements Server {

	private static HashMap<String, Class> serviceRegister = new HashMap();
	private static int port;/* = 9999; */
	// 连接池
	private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	private static boolean isRunning = false;

	public ServerCenter(int port) {
		this.port = port;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		ServerSocket server = null;
		try {

			server = new ServerSocket();
			server.bind(new InetSocketAddress(port));
		} catch (Exception e) {
			e.printStackTrace();
		}

		isRunning = true;
		while (true) {

			System.out.println("start server......");
			Socket socket = null;
			try {
				socket = server.accept();// 等待客户端连接
			} catch (Exception e) {
				e.printStackTrace();
			}
			executor.execute(new ServiceTask(socket));
			// 接收到客户端连接及请求
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		isRunning = false;
		executor.shutdown();

	}

	@Override
	public void register(Class serviceName, Class serviceImpl) {
		// TODO Auto-generated method stub
		serviceRegister.put(serviceName.getName(), serviceImpl);

	}

	private static class ServiceTask implements Runnable {

		private Socket socket;

		public ServiceTask() {

		}

		public ServiceTask(Socket socket) {
			this.socket = socket;
		}

		@override
		public void run() {
			ObjectOutputStream output = null;
			ObjectInputStream input = null;

			try {
				input = new ObjectInputStream(socket.getInputStream());
				String serviceName = input.readUTF();
				String methodName = input.readUTF();
				Class[] ParameterTypes = (Class[]) input.readObject();
				Object[] arguments = (Object[]) input.readObject();
				// 根据客户请求，到map中找到与之对应的接口
				Class serviceClass = serviceRegister.get(serviceName);
				Method method = serviceClass.getMethod(methodName, ParameterTypes);
				Object result = method.invoke(serviceClass.newInstance(), arguments);
				// 向客户端将返回值传回去
				output = new ObjectOutputStream(socket.getOutputStream());

				output.writeObject(result);
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				try {
					if (output != null)
						output.close();
					if (input != null)
						input.close();
					/*
					 * if(socket != null) socket.close(); if(server != null) server.close();
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

}
