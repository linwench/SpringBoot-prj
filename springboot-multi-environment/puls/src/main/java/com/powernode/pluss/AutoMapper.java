package com.powernode.pluss;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class AutoMapper {
    public static void main(String[] args){
        //创建AutoGenerator，MP中对象
        AutoGenerator ag = new AutoGenerator();

        //设置全局配置
        GlobalConfig gc = new GlobalConfig();
        //设置代码的生成位置，磁盘的目录
        String path = System.getProperty("user.dir");
        gc.setOutputDir(path+"/src/main/java");
        //设置生成的类的名称
        gc.setMapperName("%sMapper");
        //设置Service接口的命名
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        //设置Controller类的命名
        gc.setControllerName("%sController");
        //设置作者
        gc.setAuthor("changming");
        //设置主键id的配置
        gc.setIdType(IdType.ID_WORKER);
        ag.setGlobalConfig(gc);

        //设置数据源DataSource
        DataSourceConfig ds = new DataSourceConfig();
        //驱动
        ds.setDriverName("com.mysql.cj.jdbc.Driver");
        //设置url
        ds.setUrl("jdbc:mysql://localhost:3306/bjpowernode");
        ds.setUsername("root");
        ds.setPassword("20030508ll");
        //把DataSourceConfig赋值给AutoGenerator
        ag.setDataSource(ds);

        //设置package信息
        PackageConfig pc = new PackageConfig();
        //设置模块名
        pc.setModuleName("order");
        //设置父包名
        pc.setParent("com.powernode");
        ag.setPackageInfo(pc);

        //设置策略
        StrategyConfig sc = new StrategyConfig();
        //设置支持驼峰的命名规则
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        ag.setStrategy(sc);

        //执行代码的生成
        ag.execute();
    }
}
