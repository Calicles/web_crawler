package services;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Context {
	
	public static BeanFactory f= new FileSystemXmlApplicationContext("config/beans.xml");
	
	public static void main(String[] args) {
		System.out.println( Context.f.getBean("canalTurfCrawler"));
	}
	
}
