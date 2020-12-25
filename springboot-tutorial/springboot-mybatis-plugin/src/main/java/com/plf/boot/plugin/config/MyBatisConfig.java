package com.plf.boot.plugin.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MyBatisConfig {

	@Value("${mybatis.config.sql-with-param}")
	private boolean sqlWithParam;

	@Bean
	public ConfigurationCustomizer myBatisConfigurationCustomizer() {
		return configuration -> {
			if(sqlWithParam) {
				log.info("MyBatisConfig 注册 MybatisSqlStatementInterceptor");
				configuration.addInterceptor(new MybatisSqlStatementInterceptor());
			}
		};
	}
}
