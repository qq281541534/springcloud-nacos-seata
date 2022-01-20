/*
 *  Copyright 1999-2021 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.work.common.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author liuyu
 * @Description mybatis配置中心
 * @createTime 2022/1/20 16:02
 */
@Configuration
public class MyBatisConfig {

/*******start****************************************************/
    /**
     * hikari数据源配置
     * 如果使用hikari数据源，只用增加这一个配置项即可，不要手动配置DataSourceProxy，会有兼容性问题
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return new HikariDataSource();
    }
/********end************************************************************/

/********start*****************************************************************/
    /**
     *  durid数据源配置（默认seata使用的就是durid，durid没有特殊配置也可以不使用）
     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return new DruidDataSource();
//    }
//
//    @Bean
//    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
//        return new DataSourceProxy(dataSource);
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
//        return sqlSessionFactoryBean.getObject();
//    }

/*************end*************************************************************/

    /**
     * MP 自带分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
