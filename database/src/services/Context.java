package services;

import java.io.IOException;

import org.springframework.beans.factory.BeanFactory;

import model.Hive;
import model.Spider;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Context {
	
	public static BeanFactory f= new FileSystemXmlApplicationContext("config/beans.xml");
	
	public static void main(String[] args) throws IOException {
		Hive hive= (Hive) Context.f.getBean("canalTurfCrawler");
		
		hive.start();
	}
	
}
