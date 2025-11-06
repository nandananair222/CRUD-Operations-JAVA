package com.example.config;

import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.hibernate.cfg.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.util.Properties;
import com.example.entity.Student;

@Configuration
@ComponentScan(basePackages = "com.example")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/testdb");
        ds.setUsername("root");
        ds.setPassword("yourpassword");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setAnnotatedClasses(Student.class);

        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        props.put(Environment.HBM2DDL_AUTO, "update");
        props.put(Environment.SHOW_SQL, true);
        factory.setHibernateProperties(props);

        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sf) {
        return new HibernateTransactionManager(sf);
    }
}
