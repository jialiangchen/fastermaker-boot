package com.fastermaker.plugin.mybatis.generator;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 自定义模板引擎
 * 继承官方，覆写自定义输出的文件名，控制是否附加实体名前缀
 * @author wqliu
 * @date 2023-1-29
 */
public class MyVelocityTemplate extends VelocityTemplateEngine {
    private String rootDir;
    public MyVelocityTemplate(String rootDir){
        this.rootDir=rootDir;
    }
    /**
     * 视图对象模板文件名
     */
    private static final String VUE = ".vue";
    private static final String TS = ".ts";
    private static final String SQL = ".sql";
    /**
     * 输出自定义模板文件
     *
     * @param customFiles 自定义模板文件列表
     * @param tableInfo   表信息
     * @param objectMap   渲染数据
     * @since 3.5.3
     */
    @Override
    protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String parentPath = getPathInfo(OutputFile.parent);
        customFiles.forEach(file -> {
            String filePath = StringUtils.isNotBlank(file.getFilePath()) ? file.getFilePath() : parentPath;
            if (StringUtils.isNotBlank(file.getPackageName())) {
                filePath = filePath + File.separator + file.getPackageName();
            }
            //只有为视图对象时，才附加实体名，对于其他模板，如前端页面，如list.vue/edit.vue,不附加实体名
            String prefix= entityName;
            String module=objectMap.get("module").toString();
            if(file.getFileName().endsWith(VUE))
            {
                prefix=prefix.toLowerCase()+ File.separator;
                filePath=rootDir+"/views/"+module+"/";
            }
            if(file.getFileName().endsWith(SQL))
            {
                prefix=prefix.toLowerCase();
                filePath=rootDir+"/sql/";
            }
            if(file.getFileName().endsWith(TS)){
                prefix=prefix.toLowerCase();
                filePath=rootDir+"/api/";
            }
            String fileName = filePath + File.separator + prefix + file.getFileName();
            outputFile(new File(fileName), objectMap, file.getTemplatePath(), file.isFileOverride());
        });
    }

}

