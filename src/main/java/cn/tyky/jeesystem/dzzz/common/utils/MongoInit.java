package cn.tyky.jeesystem.dzzz.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.Mongo;

public class MongoInit extends MongoTemplate implements BeanDefinitionRegistryPostProcessor {

	public MongoInit(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		System.out.println("Mongo1 -------->>>>>>>>>");
	}


	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//		System.out.println("Mongo0 -------->>>>>>>>>");
	}

}
