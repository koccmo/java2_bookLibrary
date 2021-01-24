package lv.javaguru.app.dependency_injection;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

	private Map<Class, Object> beans = new HashMap<>();
	public  ApplicationContext instance ;

	public  ApplicationContext getInstance () {
		return instance;
	}

	public void setInstance (ApplicationContext instance) {
	this.instance = instance;
}

	public ApplicationContext () {
	}

	public void addBean (Class beanClass, Object beanInstance) {
		beans.put(beanClass, beanInstance);
		Class[] instanceInterfaces = beanClass.getInterfaces();
		for (int i = 0; i < instanceInterfaces.length; i++) {
			Class instanceInterface = instanceInterfaces[i];
			Object instance = getBean(instanceInterface);
			if (instance == null) {
				beans.put(instanceInterface, beanInstance);
			}
		}
	}

	public <T extends Object> T getBean (Class c) {
		return (T) beans.get(c);
	}

}