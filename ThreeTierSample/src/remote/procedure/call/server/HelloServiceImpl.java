package remote.procedure.call.server;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		return "hi,"+name;

	}
	/*
	public String sayHi(String name,String key) {
		// TODO Auto-generated method stub
		return "hi,"+ name + key;

	}
	*/
	@Override
	public String testHi(String name,String hlanguage) {
		
		return "测试是否能通过实现调用到该testHi,"+name+hlanguage;
	}

}
