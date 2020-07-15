package remote.procedure.call.server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Server {
	public void start() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException;
	public void stop();
	public void register(Class serviceName,Class serviceImpl);

}
