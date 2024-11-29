# POM
## 项目基本信息
``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com</groupId>
    <artifactId>mmall</artifactId>
    <packaging>war</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>mmall Maven Webapp</name>
    <url>http://maven.apache.org</url>
</project>
```
- modelVersion: POM 文件的版本，通常固定为 4.0.0。
- groupId: 项目的组织标识符（通常是公司或组织的域名反转形式）。
- artifactId: 当前项目的唯一标识符，通常是模块名。
- packaging: 打包方式，此处是 war（用于 Web 应用程序）。其他常见值有 jar 和 pom。
- version: 项目的版本号，1.0-SNAPSHOT 表示开发中的快照版本。
- name: 项目名称。
- url: 项目的相关 URL。

## 项目属性（Properties）
``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com</groupId>
    <artifactId>mmall</artifactId>
    <packaging>war</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>mmall Maven Webapp</name>
    <url>http://maven.apache.org</url>
</project>
```
- 定义全局的属性值，方便维护和复用。
    - 编码配置: 设置项目源码、报告和编译的字符编码为 UTF-8。
    - 版本号管理: 将依赖的版本信息定义为变量，如 Spring 和 MyBatis 的版本。这种方式有利于集中管理版本。

## 项目依赖（Dependencies）
``` xml
<dependencies>
    <dependency>
        <groupId>org.apache.tomcat</groupId>
        <artifactId>tomcat-servlet-api</artifactId>
        <version>7.0.64</version>
    </dependency>
    ...
</dependencies>
```
- 依赖是 Maven 项目的核心，用于引入其他库。每个依赖项通常包括以下部分：
    - groupId: 依赖的组织标识符。
    - artifactId: 依赖的唯一标识符。
    - version: 依赖的版本号。
    - scope（可选）: 指定依赖的作用范围，如 compile（默认值）、test、provided 等。

主要依赖解释：

- Spring 系列: 包括 Spring MVC、Spring JDBC、Spring Test 等，版本通过 ${org.springframework.version} 控制。
- MyBatis 系列: MyBatis 和 MyBatis-Spring，用于数据持久化操作。
- 日志依赖: logback-classic 和 logback-core，用于日志记录。
- 数据库驱动: mysql-connector-java，MySQL 数据库驱动。
- 测试库: junit，用于单元测试。
- 工具库:
  - guava: Google 提供的通用工具库。
  - commons-lang3: Apache 提供的字符串和对象处理工具。
  -  joda-time: 时间处理库。
  - 分页插件: pagehelper 和 mybatis-paginator，为 MyBatis 提供分页支持。

## 构建配置（Build）
``` xml
<build>
    <finalName>mmall</finalName>
    <plugins>
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.2</version>
            <configuration>
                <verbose>true</verbose>
                <overwrite>true</overwrite>
            </configuration>
        </plugin>
        ...
    </plugins>
</build>
```
- finalName: 指定最终生成的打包文件名，此处为 mmall.war。
- 插件（Plugins）:
  - MyBatis Generator: 自动生成 MyBatis 的映射文件和实体类。
  - verbose: 是否输出详细信息。
  - overwrite: 是否覆盖已有文件。
  - Maven Compiler Plugin: 用于指定 Java 编译版本和其他编译选项。
  - source 和 target: 指定编译的 Java 版本为 1.7。
  - encoding: 设置编码为 UTF-8。

# 文件夹
pojo(数据库对象) -> bo(business object) -> vo(view object) -> controller(前端交互)

# mybatis
## mybatis-generator

链接数据库