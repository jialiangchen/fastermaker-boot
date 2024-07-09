package com.fastermaker.modules.system.model.query;


import com.fastermaker.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="字典数据项分页查询对象")
@Data
public class DictPageQuery extends BasePageQuery {

    @Schema(description="关键字(字典项名称)")
    private String keywords;

    @Schema(description="字典编码")
    private String typeCode;
}