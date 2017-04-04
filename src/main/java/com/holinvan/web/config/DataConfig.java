package com.holinvan.web.config;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.holinvan.web.repository")
public class DataConfig {
	
	private static Logger LOG = LoggerFactory.getLogger(DataConfig.class);
	
    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        LOG.info("DATABASE_URL: " + dbUri.toString());
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }
	
	@Bean
	public EclipseLinkJpaVendorAdapter getEclipseLinkJpaVendorAdapter() {
		EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.MySQLPlatform");
		vendorAdapter.setGenerateDdl(false);
		vendorAdapter.setShowSql(true);
		return vendorAdapter;
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactory.setJpaVendorAdapter(getEclipseLinkJpaVendorAdapter());
	    Map<String, Object> jpaProperties = new Hashtable<>();
	    jpaProperties.put("eclipselink.logging.level","fine");
	    jpaProperties.put("eclipselink.weaving","false");
	    entityManagerFactory.setJpaPropertyMap(jpaProperties);
	    entityManagerFactory.setPackagesToScan("com.holinvan.web.model");
	    try {
			entityManagerFactory.setDataSource(dataSource());
		} catch (URISyntaxException e) {
			LOG.error("Exception opening dataSource: ", e);
		}
	    entityManagerFactory.afterPropertiesSet();
	    return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
	    return txManager;
	}

}
