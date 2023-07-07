package com.jingyi.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

/**
 * 生成 controller service dao 层代码
 * <a href="https://baomidou.com/pages/981406/#%E6%95%B0%E6%8D%AE%E5%BA%93%E9%85%8D%E7%BD%AE-datasourceconfig">参考链接</a>
 */
public class GenerateCode {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = GenerateCode.class.getClassLoader().getResourceAsStream("application.yml");
        Properties prop = new Properties();
        prop.load(inputStream);

        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(
                prop.getProperty("db-url"),
                prop.getProperty("db-username"),
                prop.getProperty("db-password")
        )
                .dbQuery(new MySqlQuery())
                .schema("mybatis-plus")
                .build();

        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .fileOverride()
                .outputDir(prop.getProperty("work-dir"))
                .author(prop.getProperty("author"))
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .disableOpenDir()
                .build();

        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent(prop.getProperty("package"))
                .moduleName(prop.getProperty("module-name"))
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("dao")
                .xml("mapper")
                .controller("controller")
                .pathInfo(Collections.singletonMap(OutputFile.xml, prop.getProperty("xml-dir")))
                .build();

        new StrategyConfig.Builder()
                .entityBuilder()
                .enableFileOverride()
                .build();

        new AutoGenerator(dataSourceConfig)
                .global(globalConfig)
                .packageInfo(packageConfig)
                .execute();
    }
}
