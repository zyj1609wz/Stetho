## Stetho 简介
Stetho是Facebook开源的Andorid调试工具。当你的应用集成Stetho时，开发者可以访问Chrome，在Chrome Developer Tools中查看应用布局，网络请求，sqlite，preference等等，可视化一切应用操作（更重要的是不用root）。

- 官网： http://facebook.github.io/stetho/

- 集成
```
dependencies {
    compile 'com.facebook.stetho:stetho-okhttp3:1.4.2'
}
```

## 其他说明

- 本例子中的数据库用的是：`lite-orm-1.9.2,jar` 
  地址是：[https://github.com/litesuits/android-lite-orm](https://github.com/litesuits/android-lite-orm)
博客地址：[http://www.cnblogs.com/zhaoyanjun/p/5640788.html](http://www.cnblogs.com/zhaoyanjun/p/5640788.html)

- 本例字中使用的网络请求框架为：`okhttp`
地址：[https://github.com/square/okhttp](https://github.com/square/okhttp)

- 这个文章所有的代码以上传至Github：[https://github.com/zyj1609wz/Stetho](https://github.com/zyj1609wz/Stetho)
