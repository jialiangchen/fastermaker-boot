## 项目简介

fastermaker-boot为开发者提供一个代码简洁、结构清晰、开发高效、模块可扩展的单体项目的基础开发框架，适合初级开发者特别是大学生学习研究使用,也是中小型系统快速开发的利器。

- **开发技术**: JDK 17、Spring Boot3 、Vue3、Element-Plus、Spring Security、JWT 、Swagger、Validation等

- **功能模块**: 用户管理、角色管理、菜单管理、部门管理、字典管理、系统参数，系统日志、SQL监控、代码生成(包括前后端)。

- **接口文档**: 自动生成接口文档，支持在线调试，提高开发效率。

- **代码生成**: 可在线生成controller、entity、dao、service、mapper、vue、sql等代码，减少70%以上的开发任务。

## 📁 项目目录

fastermaker-boot
├── sql                                 # SQL脚本
    ├── fastermaker.sql                 # MySQL版本的脚本
├── src                                 # 源码目录
    ├── common                          # 公共模块
    ├── config                          # 自动装配配置
    ├── modules   						# 功能模块
        ├── system						# 系统管理模块
			├── controller              # 控制层
			├── converter               # MapStruct转换工具
			├── mapper                  # 数据库访问层
			├── service                 # 业务逻辑层
			├── model                   # 模型层
				├── bo                  # 业务对象
				├── dto                 # 对外服务的数据传输对象
				├── entity              # 数据库实体对象
				├── form                # 前端表单对象
				├── query               # 前端查询参数对象
				├── vo                  # 视图对象
		├── tool						# 系统工具模块
    ├── filter                          # 过滤器
    ├── plugin                          # 插件集成
    ├── security                        # Spring Security 工具类     


## 🌺 前端工程

欢迎加QQ群 973170278 免费获取。

## 🚀 项目启动

1. **数据库初始化**

    执行 [fastermaker.sql](sql/fastermaker.sql) 脚本完成数据库创建、表结构和基础数据的初始化。

2. **修改配置**

    [application-dev.yml](src/main/resources/application-dev.yml) 修改MySQL连接配置；

3. **启动项目**

    执行 [FastermakerApplication.java](src/main/java/com/fastermaker/modules/system/FastermakerApplication.java) 的 main 方法完成后端项目启动；


## 💖 加交流群

如需关注项目最新动态，请Watch、Star项目，同时也是对项目最好的支持,欢迎加 QQ群 973170278 进行技术讨论、咨询和建议。

