package com.fastermaker.modules.tool.model.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

/**
 * 代码生成
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
public class GeneratorBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 基础包名
     */
    private String packageName;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 修改人ID
     */
    private Long updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
