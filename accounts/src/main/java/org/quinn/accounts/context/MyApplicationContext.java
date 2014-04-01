package org.quinn.accounts.context;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MyApplicationContext.applicationContext = applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBeansByType(Class<T> beanType) {
		Map<String, T> beans = MyApplicationContext.applicationContext.getBeansOfType(beanType);
		if (beans != null && beans.size() > 0)
			return beans.values().iterator().next();
		return null;
	}

}
