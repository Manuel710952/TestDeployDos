package com.example.deploy.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class listenerWebPrueba implements ServletContextListener {
	
	private static final Logger LOG = LogManager.getLogger(listenerWebPrueba.class);
	Properties props = new Properties();
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LOG.info("ENTRO::contextInitialized::TestQuartz::init");
		try {
			this.printPropertieEnviroment();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void printPropertieEnviroment() {
		try {
			InputStream input = listenerWebPrueba.class.getClassLoader().getResourceAsStream("config.properties");
			this.props.load(input);
			LOG.info("ENTRO::doGet::printPropertieEnviroment::" + this.props.getProperty("ftpserver.port"));
			ObjectMapper mapper = new ObjectMapper();
			String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
			Car car = mapper.readValue(json, Car.class);
			LOG.info("ENTRO::doGet::valorCra::" + car.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
 
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	LOG.info("destruyo::contextDestroyed");
    }
 
}

