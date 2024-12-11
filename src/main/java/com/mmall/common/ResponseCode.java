package com.mmall.common;


public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    BUSINESS_ERROR(1001, "业务处理异常 请联系开发人员"),
    DATABASE_ERROR(2001,"DATABASE_ERROR");


    // 实例字段 private final 修饰符表示这些字段是只读的，值一旦被初始化后不能修改。
    private final int code;
    private final String desc;

    // 构造函数 枚举的构造函数是 private 的（隐式的，不能显式声明为 public 或 protected），只能在枚举类内部使用。
    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    // 访问器方法 getCode()：返回枚举常量的状态码。
    public int getCode(){
        return code;
    }

    //  访问器方法 getDesc()：返回枚举常量的描述信息
    public String getDesc(){
        return desc;
    }
}
