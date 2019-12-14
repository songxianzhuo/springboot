package com.example.jtaatomikos.config.datasource;


import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName: OneDataSourceConfig
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/18 14:53
 * @Version: 1.0
 */
@Configuration
@MapperScan(basePackages = "com.example.jtaatomikos.dao.one",
        sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class OneDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSourceParam oneDataSourceParam(){
        return new DataSourceParam();
    }

    @Bean
    @Primary
    public DataSource oneDataSource(@Qualifier("oneDataSourceParam") DataSourceParam dataSourceParam) throws SQLException {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        BeanUtils.copyProperties(dataSourceParam, druidXADataSource);
        //druidXADataSource.setValidationQuery("select 1");
        //druidXADataSource.setValidationQueryTimeout(10);
        //druidXADataSource.setTestOnBorrow(true);
        //druidXADataSource.setTestOnReturn(true);
        //druidXADataSource.setTestWhileIdle(true);
        //druidXADataSource.setTimeBetweenEvictionRunsMillis(60000);
        //创建atomikos全局事务
        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
        atomikosDataSource.setXaDataSource(druidXADataSource);
        atomikosDataSource.setUniqueResourceName("oneDataSource");
        //固定连接池，默认都是1，设置后，最大和最小线程池一样
        //atomikosDataSource.setPoolSize(10);
        //范围连接池，默认都是1
        atomikosDataSource.setMinPoolSize(1);
        atomikosDataSource.setMaxPoolSize(10);
        // 获取连接失败重新获取等待最大时间，在这个时间内如果有可用连接，将返回，默认30s
        //atomikosDataSource.setBorrowConnectionTimeout(60);
        // 最大空闲时间，超过最小链接数的的链接将关闭，默认60s
        //atomikosDataSource.setMaxIdleTime(60);
        // 空闲连接最大存活时间,默认0,mysql默认数据库链接的空闲时间是8小时，所以MaxLifetime值不要超过8小时，到达设置值后，链接将销毁，下次调用会新建链接
        //atomikosDataSource.setMaxLifetime(60);
        // 连接回收时间,默认60s
        //atomikosDataSource.setMaintenanceInterval();
        // 最大获取数据时间，默认0，如果不设置这个值，Atomikos使用默认的5分钟，那么在处理大批量数据读取的时候，一旦超过5分钟，就会抛出类似 Resultset is close 的错误
        //atomikosDataSource.setReapTimeout();
        // java数据库连接池，最大可等待获取datasouce的时间,默认0
        //atomikosDataSource.setLoginTimeout();
        // 线程空闲时，进行验证，默认是true
        //atomikosDataSource.setConcurrentConnectionValidation(true);
        //每次申请链接时都会验证链接是否可用，比较耗性能，它和maxLifetime可以2选1，建议使用maxLifetime
        //atomikosDataSource.setTestQuery("select 1");
        return atomikosDataSource;
    }

    @Bean
    @Primary
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource datasource) {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            //注入相应的DataSource
            sqlSessionFactoryBean.setDataSource(datasource);
            // 配置mapper.xml文件位置，mapper.xml文件可以不分目录，但是最好分目录保存，这样容易区分，一目了然
            sqlSessionFactoryBean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources("classpath:mappers/one/*.xml"));
            // 开启驼峰下划线默认转换
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.setMapUnderscoreToCamelCase(true);
            sqlSessionFactoryBean.setConfiguration(configuration);
            // 配置别名包
            sqlSessionFactoryBean.setTypeAliasesPackage("com.example.jtaatomikos.model");
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    @Bean
    @Primary
    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 使用的是jta事务管理器
     * @param platformTransactionManager
     * @return
     */
    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager,DataSource dataSource){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        System.out.println(">>>>>>>>>>" + dataSource.getClass().getName());
        return new Object();
    }
}
