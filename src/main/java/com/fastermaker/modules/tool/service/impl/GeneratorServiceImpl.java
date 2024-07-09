package com.fastermaker.modules.tool.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.fastermaker.common.util.ZipFolderUtils;
import com.fastermaker.plugin.mybatis.generator.MyVelocityTemplate;
import com.fastermaker.modules.system.model.entity.SysMenu;
import com.fastermaker.modules.tool.model.entity.Generator;
import com.fastermaker.modules.tool.mapper.GeneratorMapper;
import com.fastermaker.modules.tool.service.GeneratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fastermaker.security.util.SecurityUtils;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.fastermaker.common.util.DateUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.tool.model.form.GeneratorForm;
import com.fastermaker.modules.tool.model.query.GeneratorPageQuery;
import com.fastermaker.modules.tool.model.bo.GeneratorBO;
import com.fastermaker.modules.tool.model.vo.GeneratorPageVO;
import com.fastermaker.modules.tool.converter.GeneratorConverter;

/**
 * 代码生成服务实现类
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl extends ServiceImpl<GeneratorMapper, Generator> implements GeneratorService {

    private final GeneratorConverter generatorConverter;
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;
    @Value("${spring.datasource.username}")
    private String dataSourceUserName;
    @Value("${spring.datasource.password}")
    private String dataSourceUserPassword;

    /**
     * 获取代码生成分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<GeneratorPageVO>} 代码生成分页列表
     */
    @Override
    public IPage<GeneratorPageVO> listPage(GeneratorPageQuery queryParams) {

        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<GeneratorBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");

        // 查询数据
        Page<GeneratorBO> boPage = this.baseMapper.listPage(page, queryParams);

        // 实体转换
        return generatorConverter.toPageVo(boPage);
    }

    /**
     * 获取代码生成表单数据
     *
     * @param id 代码生成ID
     * @return
     */
    @Override
    public GeneratorForm getFormData(Long id) {
        Generator entity = this.getById(id);
        return generatorConverter.convertToForm(entity);
    }

    /**
     * 新增代码生成
     *
     * @param formData 代码生成表单对象
     * @return
     */
    @Override
    public boolean save(GeneratorForm formData) {
        // 实体转换 form->entity
        Generator entity = generatorConverter.convertToEntity(formData);
        Long userId = SecurityUtils.getUserId();
        entity.setCreateBy(userId);
        return this.save(entity);
    }

    /**
     * 更新代码生成
     *
     * @param formData 代码生成表单对象
     * @return
     */
    @Override
    public boolean update(GeneratorForm formData) {
        Generator entity = generatorConverter.convertToEntity(formData);
        Long userId = SecurityUtils.getUserId();
        entity.setUpdateBy(userId);
        entity.setUpdateTime(LocalDateTime.now());
        return this.updateById(entity);
    }

    /**
     * 删除代码生成
     *
     * @param ids 代码生成ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean delete(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的代码生成数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    public byte[] download(String id) throws IOException {
        DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
                .Builder(dataSourceUrl, dataSourceUserName, dataSourceUserPassword);
        Generator entity = this.getById(id);
        String tempDir=System.getProperty("user.dir")+"/fastermaker/";//System.getProperty("java.io.tmpdir")+"/fastermaker/"
        String zipDir=System.getProperty("user.dir")+"/fastermaker.zip";
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> {
                    builder.outputDir(tempDir + "/src/main/java")
                            .author(entity.getAuthor()).dateType(DateType.TIME_PACK).disableOpenDir();
                })
                // 包配置
                .packageConfig(builder -> {
                            builder
                                    .parent(entity.getPackageName())
                                    .moduleName(entity.getModuleName())
                                    .entity("model.entity")
                                    .mapper("mapper")
                                    .service("service")
                                    .serviceImpl("service.impl")
                                    .controller("controller")
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, tempDir+ "/src/main/resources/mapper"));
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
                    customFiles.add(new CustomFile.Builder().fileName("index.vue").templatePath("/templates/index.vue.vm").packageName("views/"+entity.getModuleName()).build());
                    customFiles.add(new CustomFile.Builder().fileName(".ts").templatePath("/templates/api.ts.vm").packageName("api").build());
                    consumer.customFile(customFiles);
                    consumer.beforeOutputFile((tableInfo, objectMap) -> {
                        // 为每个表生成首字母小写的实体名
                        String entityName = tableInfo.getEntityName();
                        String lowerCaseEntity = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
                        // 注入自定义参数
                        objectMap.put("firstCharLowerCaseEntity", lowerCaseEntity);
                        objectMap.put("upperCaseEntity", lowerCaseEntity.toUpperCase());
                        objectMap.put("superEntityClass", "BaseEntity");
                        objectMap.put("module", entity.getModuleName());
                        Long menuId= IdWorker.getId(SysMenu.class);
                        objectMap.put("menuId", menuId);
                    });

                }).templateEngine(new MyVelocityTemplate(tempDir))
                // 策略配置
                .strategyConfig((scanner, builder) -> {

                            builder.entityBuilder()
                                    .enableLombok() // 是否使用lombok
                                    //.enableFileOverride() // 开启覆盖已生成的文件
                                    .logicDeleteColumnName("deleted") // 逻辑删除字段名
                                    .enableRemoveIsPrefix() // 开启移除is前缀
                            ;

                            builder.enableSkipView().mapperBuilder()
                                    .enableBaseColumnList()
                                    .enableBaseResultMap()
                            ;

                            builder.serviceBuilder()
                                    .formatServiceFileName("%sService"
                                    );

                            builder.addTablePrefix(entity.getTablePrefix()) // 过滤移除表前缀 sys_user 表生成的实体类 User.java
                                    .addInclude(entity.getTableName());
                            //.addInclude(scanner.apply("请输入表名，多个表名用,隔开"));
                        }
                )
                .execute();

        //添加到zip
        ZipFolderUtils.zipFolder(tempDir,zipDir);
        File file = new File(zipDir);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytesArray = new byte[(int) file.length()];
            fis.read(bytesArray);
            FileUtils.deleteDirectory(new File(tempDir));
            return bytesArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
