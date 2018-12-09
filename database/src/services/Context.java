package services;

import java.io.IOException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.io.FileSystemResource;

import model.Spider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Context {
	
	public static BeanFactory f= new FileSystemXmlApplicationContext("config/beans.xml");
	
	public static void main(String[] args) throws IOException {
		Spider spider= (Spider) Context.f.getBean("spider");
		spider.crawl("https://www.canalturf.com/resultats-PMU/2018-12-03/paris-vincennes/179968_prix-des-alpes.html");
		spider.bringDrivers();
		
	}
	
}
