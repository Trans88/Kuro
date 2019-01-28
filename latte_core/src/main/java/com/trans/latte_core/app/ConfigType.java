package com.trans.latte_core.app;

/**
 * 整个应用程序中唯一的单例，并且只被初始化一次
 * 安全的惰性单例初始化
 * 线程安全的懒汉模式
 */
public enum ConfigType {
    API_HOST,   //配置网络请求的域名
    APPLICATION_CONTEXT,    //全局的上下文
    CONFIG_READY,   //控制初始化或者配置是否完成
    ICON    //存储自己的初始化项目
}
