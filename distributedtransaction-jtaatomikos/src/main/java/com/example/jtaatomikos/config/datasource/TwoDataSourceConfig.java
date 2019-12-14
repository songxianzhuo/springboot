package com.example.jtaatomikos.config.datasource;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * @ClassName: TwoDataSourceConfig
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/18 15:15
 * @Version: 1.0
 */

@Configuration
@MapperScan(basePackages = "com.example.jtaatomikos.dao.two",
        sqlSessionTemplateRef = "twoSqlSessionTemplate")
public class TwoDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSourceParam twoDataSourceParam(){
        return new DataSourceParam();
    }

    @Bean
    public DataSource twoDataSource(@Qualifier("twoDataSourceParam") DataSourceParam dataSourceParam) {
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
        atomikosDataSource.setUniqueResourceName("twoDataSource");
        atomikosDataSource.setMinPoolSize(1);
        atomikosDataSource.setMaxPoolSize(10);
        //atomikosDataSource.setMaxLifetime(60);
        //atomikosDataSource.setTestQuery("select 1");
        return atomikosDataSource;
    }

    @Bean
    public SqlSessionFactory twoSqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            //注入相应的DataSource
            sqlSessionFactoryBean.setDataSource(dataSource);
            // 配置mapper.xml文件位置
            sqlSessionFactoryBean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources("classpath:mappers/two/*.xml"));
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
    public SqlSessionTemplate twoSqlSessionTemplate(@Qualifier("twoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

