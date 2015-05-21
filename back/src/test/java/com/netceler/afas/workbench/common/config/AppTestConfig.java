package com.netceler.afas.workbench.common.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.netceler.afas.workbench.common.dao.IRuleDao;
import com.netceler.afas.workbench.common.dao.RuleDaoImpl;
import com.netceler.afas.workbench.common.model.Rule;

@Configuration
@ComponentScan("com.netceler.afas.workbench")
@EnableWebMvc
@EnableTransactionManagement
public class AppTestConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSource datasource = (DataSource) new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:schema.sql").build();
		return datasource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties
				.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		properties.put("org.hibernate.envers.revision_field_name", "REV");
		properties.put("org.hibernate.envers.revision_type_field_name",
				"REVTYPE");
		properties.put("org.hibernate.envers.auditTableSuffix", "_HISTORY");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", "true");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
				dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(Rule.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);
		transactionManager.setRollbackOnCommitFailure(true);
		return transactionManager;
	}

	@Autowired
	@Bean(name = "ruleDao")
	public IRuleDao getUserDao(SessionFactory sessionFactory) {
		return new RuleDaoImpl(sessionFactory);
	}

}