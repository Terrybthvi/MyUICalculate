# MyUICalculate
一种非常简单的屏幕适配方案

在代码中使用可以说非常简单，就两句话，

1、先初始化，

2、然后如果适配Activity再传入需要适配的Activity的上下文；
如果是适配布局则直接传入布局就OK了。
就是这么简单。
```
    //初始化
    UIUtils.getInstance(this);
    //根据布局适配
    UIUtils.getInstance().register(linearLayout);
    //根据Activity适配
    UIUtils.getInstance().register(this);
```
博客链接：[一种非常简单的屏幕适配方案](https://bthvi-leiqi.blog.csdn.net/article/details/95076522)
