package com.fastermaker.common.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应码枚举
 * <p>
 * 参考阿里巴巴开发手册响应码规范
 *
 *
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {

    SUCCESS("200", "操作成功"),

    USER_ERROR("500", "用户端错误"),
    REPEAT_SUBMIT_ERROR("500", "您的请求已提交，请不要重复提交或等待片刻再尝试。"),

    USER_LOGIN_ERROR("500", "用户登录异常"),

    USER_NOT_EXIST("500", "用户不存在"),
    USER_ACCOUNT_LOCKED("500", "用户账户被冻结"),
    USER_ACCOUNT_INVALID("500", "用户账户已作废"),

    USERNAME_OR_PASSWORD_ERROR("500", "用户名或密码错误"),
    PASSWORD_ENTER_EXCEED_LIMIT("500", "用户输入密码次数超限"),
    CLIENT_AUTHENTICATION_FAILED("500", "客户端认证失败"),

    VERIFY_CODE_TIMEOUT("500", "验证码已过期"),
    VERIFY_CODE_ERROR("500", "验证码错误"),

    TOKEN_INVALID("401", "token无效或已过期"),
    TOKEN_ACCESS_FORBIDDEN("403", "没有权限访问"),

    AUTHORIZED_ERROR("401", "访问权限异常"),
    ACCESS_UNAUTHORIZED("401", "访问未授权"),
    FORBIDDEN_OPERATION("500", "演示环境禁止新增、修改和删除数据，请本地部署后测试"),


    PARAM_ERROR("500", "用户请求参数错误"),
    RESOURCE_NOT_FOUND("500", "请求资源不存在"),
    PARAM_IS_NULL("500", "请求必填参数为空"),

    USER_UPLOAD_FILE_ERROR("500", "用户上传文件异常"),
    USER_UPLOAD_FILE_TYPE_NOT_MATCH("500", "用户上传文件类型不匹配"),
    USER_UPLOAD_FILE_SIZE_EXCEEDS("500", "用户上传文件太大"),
    USER_UPLOAD_IMAGE_SIZE_EXCEEDS("500", "用户上传图片太大"),

    SYSTEM_EXECUTION_ERROR("500", "系统执行出错"),
    SYSTEM_EXECUTION_TIMEOUT("500", "系统执行超时"),
    SYSTEM_ORDER_PROCESSING_TIMEOUT("500", "系统订单处理超时"),

    SYSTEM_DISASTER_RECOVERY_TRIGGER("500", "系统容灾功能被出发"),
    FLOW_LIMITING("500", "系统限流"),
    DEGRADATION("500", "系统功能降级"),

    SYSTEM_RESOURCE_ERROR("500", "系统资源异常"),
    SYSTEM_RESOURCE_EXHAUSTION("500", "系统资源耗尽"),
    SYSTEM_RESOURCE_ACCESS_ERROR("500", "系统资源访问异常"),
    SYSTEM_READ_DISK_FILE_ERROR("500", "系统读取磁盘文件失败"),

    CALL_THIRD_PARTY_SERVICE_ERROR("500", "调用第三方服务出错"),
    MIDDLEWARE_SERVICE_ERROR("500", "中间件服务出错"),
    INTERFACE_NOT_EXIST("500", "接口不存在"),

    MESSAGE_SERVICE_ERROR("500", "消息服务出错"),
    MESSAGE_DELIVERY_ERROR("500", "消息投递出错"),
    MESSAGE_CONSUMPTION_ERROR("500", "消息消费出错"),
    MESSAGE_SUBSCRIPTION_ERROR("500", "消息订阅出错"),
    MESSAGE_GROUP_NOT_FOUND("500", "消息分组未查到"),

    DATABASE_ERROR("500", "数据库服务出错"),
    DATABASE_TABLE_NOT_EXIST("500", "表不存在"),
    DATABASE_COLUMN_NOT_EXIST("500", "列不存在"),
    DATABASE_DUPLICATE_COLUMN_NAME("500", "多表关联中存在多个相同名称的列"),
    DATABASE_DEADLOCK("500", "数据库死锁"),
    DATABASE_PRIMARY_KEY_CONFLICT("500", "主键冲突");

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    private String code;

    private String msg;

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }


    public static ResultCode getValue(String code) {
        for (ResultCode value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return SYSTEM_EXECUTION_ERROR; // 默认系统执行错误
    }
}
