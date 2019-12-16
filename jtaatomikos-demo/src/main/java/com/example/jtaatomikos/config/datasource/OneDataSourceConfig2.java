//package com.example.jtaatomikos.config.datasource;
//
//import com.alibaba.druid.pool.xa.DruidXADataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.Properties;
//
///**
// * @ClassName: OneDataSourceConfig2
// * @Description: TODO
// * @Author: SONG
// * @Date: 2019/11/26 19:34
// * @Version: 1.0
// */
//@Configuration
//@MapperScan(basePackages = "com.example.jtaatomikos.dao.one",
//        sqlSessionTemplateRef = "oneSqlSessionTemplate")
//public class OneDataSourceConfig2 {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    @Primary
//    public DataSource oneDataSource() {
//        //创建atomikos全局事务
//        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
//        atomikosDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
//        atomikosDataSource.setUniqueResourceName("oneDataSource");
//        atomikosDataSource.setMinPoolSize(1);
//        atomikosDataSource.setMaxPoolSize(8);
//        Properties prop = build("spring.datasource.one.");
//        atomikosDataSource.setXaProperties(prop);
//        return atomikosDataSource;
//    }
//
//    @Bean
//    @Primary
//    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource datasource) {
//        SqlSessionFactory sqlSessionFactory = null;
//        try {
//            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//            //注入相应的DataSource
//            sqlSessionFactoryBean.setDataSource(datasource);
//            // 配置mapper.xml文件位置，mapper.xml文件可以不分目录，但是最好分目录保存，这样容易区分，一目了然
//            sqlSessionFactoryBean.setMapperLocations(
//                    new PathMatchingResourcePatternResolver().getResources("classpath:mappers/one/*.xml"));
//            // 开启驼峰下划线默认转换
//            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//            configuration.setMapUnderscoreToCamelCase(true);
//            sqlSessionFactoryBean.setConfiguration(configuration);
//            // 配置别名包
//            sqlSessionFactoryBean.setTypeAliasesPackage("com.example.jtaatomikos.model");
//            sqlSessionFactory = sqlSessionFactoryBean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sqlSessionFactory;
//    }
//
//    @Bean
//    @Primary
//    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    private Properties build(String prefix) {
//        Properties prop = new Properties();
//        prop.put("url", env.getProperty(prefix + "url"));
//        prop.put("username", env.getProperty(prefix + "username"));
//        prop.put("password", env.getProperty(prefix + "password"));
//        prop.put("driverClassName", env.getProperty(prefix + "driverClassName"));
//        prop.put("initialSize", 0);
//        prop.put("maxActive", 8);
//        prop.put("minIdle", 0);
//        // 已经不再使用，配置了也没效果
//        //prop.put("maxIdle", 8);
//        prop.put("maxWait", 60000);
//        prop.put("poolPreparedStatements", true);
//        prop.put("maxPoolPreparedStatementPerConnectionSize", 20);
//        prop.put("validationQuery", "select 1");
//        prop.put("validationQueryTimeout", 10000);
//        prop.put("testOnBorrow", false);
//        prop.put("testOnReturn", false);
//        prop.put("testWhileIdle", true);
//        prop.put("timeBetweenEvictionRunsMillis", 60000);
//        prop.put("minEvictableIdleTimeMillis", 100000);
//        prop.put("maxEvictableIdleTimeMillis",250000);
//        prop.put("filters", "stat,wall");
//        return prop;
//    }
//}
