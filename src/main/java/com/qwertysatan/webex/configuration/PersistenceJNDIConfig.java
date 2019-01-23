package com.qwertysatan.webex.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by vladislav.uvarov on 22.01.2019.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/persistence-jndi.properties")
@ComponentScan("com.qwertysatan.webex")
@EnableJpaRepositories(basePackages = {"com.qwertysatan.webex.repository"})
public class PersistenceJNDIConfig {

	@Autowired
	private Environment env;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${hibernate.show_sql}")
	private boolean showSql;

	@Value("${hibernate.generated_ddl}")
	private boolean generateDdl;

	@Value("${hibernate.dialect}")
	private String dialect;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(buildDataSource());
		entityManagerFactoryBean.setPackagesToScan("com.qwertysatan.webex");
		entityManagerFactoryBean.setJpaVendorAdapter(buildVendorAdapter());
		return entityManagerFactoryBean;
	}

	@Bean
	public DataSource buildDataSource() throws NamingException {
		return (DataSource) new JndiTemplate().lookup(jdbcUrl);
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	public JpaVendorAdapter buildVendorAdapter(){
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(showSql);
		vendorAdapter.setGenerateDdl(generateDdl);
		vendorAdapter.setDatabasePlatform(dialect);
		return vendorAdapter;
	}
}
