package net.kzn.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages= {"net.kzn.shoppingbackend.dto"})
@EnableTransactionManagement
public class HibernetConfig {
	
	//change the below ad DBMS you choose
	private final static String DATABASE_URL="jdbc:h2:tcp://localhost/~/OnlineShopping";
	private final static String DATABASE_DRIVER="org.h2.Driver";
	private final static String DATABASE_DIALECT="org.hibernate.dialect.H2Dialect";
	private final static String DATABASE_USERNAME="sa";
	private final static String DATABASE_PASSWORD="";
	
	//dataSource bean will be avialable 
	@Bean
	public  DataSource getDataSource() {
		
		//Poviding the database connection information
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		
		return dataSource;
	}
	
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder bulider= new LocalSessionFactoryBuilder(dataSource);
		bulider.addProperties(getHibernetProperties());
		bulider.scanPackages("net.kzn.shoppingbackend.dto");
		
		
		return bulider.buildSessionFactory();
		
	}
  //all the hibernate properties will be returned in this method
	private Properties getHibernetProperties() {
		// TODO Auto-generated method stub
		Properties properties=new Properties();
		properties.put("hibernate.dialect",DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}
	//tranaction manger bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager transactionManager	= new HibernateTransactionManager(sessionFactory );
		return transactionManager;
	}

}