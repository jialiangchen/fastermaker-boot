package com.fastermaker.modules.system.generator;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.fastermaker.plugin.mybatis.generator.MyVelocityTemplate;
import com.fastermaker.modules.system.model.entity.SysMenu;

import java.util.*;

/**
 * 代码交互式生成
 * <p>
 * 代码生成、MySQL表生成代码、自动代码生成
 *
 *  Hao
 * @see <a href="https://baomidou.com/pages/981406/">代码生成器配置新</a>
 */
public class FastAutoGeneratorTest {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/fastermaker?serverTimezone=Asia/Shanghai", "root", "1234");

    /**
     * 执行 run
     */
    public static void main(String[] args) {
        String module="tool";
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> {
                    builder.outputDir(System.getProperty("user.dir") + "/src/main/java")
                            .author("fastermaker").dateType(DateType.TIME_PACK) // 设置作者
                    ;
                })
                // 包配置
                .packageConfig(builder -> {
                            builder
                                    .parent("com.fastermaker.modules")
                                    .moduleName(module)
                                    .entity("model.entity")
                                    .mapper("mapper")
                                    .service("service")
                                    .serviceImpl("service.impl")
                                    .controller("controller")
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper"));
                        }
                )
                // 注入配置(设置扩展类的模板路径和包路径)
                .injectionConfig(consumer -> {
                    List<CustomFile> customFiles = new ArrayList<>();
                    customFiles.add(new CustomFile.Builder().fileName("DTO.java").templatePath("/templates/dto.java.vm").packageName("model/dto").build());
                   // customFiles.add(new CustomFile.Builder().fileName("VO.java").templatePath("/templates/vo.java.vm").packageName("model/vo").build());
                    customFiles.add(new CustomFile.Builder().fileName("BO.java").templatePath("/templates/bo.java.vm").packageName("model/bo").build());
                    customFiles.add(new CustomFile.Builder().fileName("PageQuery.java").templatePath("/templates/pageQuery.java.vm").packageName("model/query").build());
                    customFiles.add(new CustomFile.Builder().fileName("PageVO.java").templatePath("/templates/pageVO.java.vm").packageName("model/vo").build());
                    customFiles.add(new CustomFile.Builder().fileName("Form.java").templatePath("/templates/form.java.vm").packageName("model/form").build());
                    customFiles.add(new CustomFile.Builder().fileName("Menu.sql").templatePath("/templates/menu.sql.vm").packageName("sql").build());
                    customFiles.add(new CustomFile.Builder().fileName("Converter.java").templatePath("/templates/converter.java.vm").packageName("converter").build());
                    customFiles.add(new CustomFile.Builder().fileName("index.vue").templatePath("/templates/index.vue.vm").packageName("vue/views/"+module).build());
                    customFiles.add(new CustomFile.Builder().fileName(".ts").templatePath("/templates/api.ts.vm").packageName("vue/api").build());
                    consumer.customFile(customFiles);
                    consumer.beforeOutputFile((tableInfo, objectMap) -> {
                        // 为每个表生成首字母小写的实体名
                        String entityName = tableInfo.getEntityName();
                        String lowerCaseEntity = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
                        // 注入自定义参数
                        objectMap.put("firstCharLowerCaseEntity", lowerCaseEntity);
                        objectMap.put("upperCaseEntity", lowerCaseEntity.toUpperCase());
                        objectMap.put("superEntityClass", "BaseEntity");
                        objectMap.put("module", module);
                        Long menuId=IdWorker.getId(SysMenu.class);
                        objectMap.put("menuId", menuId);
                    });

                }).templateEngine(new MyVelocityTemplate(System.getProperty("user.dir")))
                // 策略配置
                .strategyConfig((scanner, builder) -> {

                            builder.entityBuilder()
                                    .enableLombok() // 是否使用lombok
                                    //.enableFileOverride() // 开启覆盖已生成的文件
                                    .logicDeleteColumnName("deleted") // 逻辑删除字段名
                                    .enableRemoveIsPrefix() // 开启移除is前缀
                            ;

                            builder.mapperBuilder()
                                    .enableBaseColumnList()
                                    .enableBaseResultMap()
                            ;

                            builder.serviceBuilder()
                                    .formatServiceFileName("%sService"
                                    );

                            builder.addTablePrefix("tool_") // 过滤移除表前缀 sys_user 表生成的实体类 User.java
                    .addInclude("tool_generator");
                    //.addInclude(scanner.apply("请输入表名，多个表名用,隔开"));
                        }
                )
                .execute();
    }
}
