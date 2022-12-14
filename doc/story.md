## 遇到的问题

### 在android 12上不能安装

参考[(1条消息) apk在android12设备无法安装(兼容android 12或更高版本)_梵天麟的博客-CSDN博客](https://blog.csdn.net/qq_39493848/article/details/122662164)

`app/src/main/AndroidManifest.xml`文件中，添加`android:exported="true"`，如下：

```xml
<activity android:name=".MainActivity" android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```

就是这个玩意儿害我一晚上没睡好觉。

感谢朱海龙同学的android 11设备测试，让我通过控制变量法发现这是android 12特有的问题。

## 图标缺失危机

查看记录等界面，只要是显示记账图标的都无法显示，只有首页输入的时候有图标。

因为之前是有两个image，未选中的和选中之后，这样选中之前是灰色，选中之后有了颜色。我做图标改革想把图标全部改为彩色的，就不能用之前的体系了，必须革命。把typetb的sImageId属性删去，牵一发而动全身，需要改的地方很多很多，直接全局搜索sImageId，改为ImageId。改完之后就发现图标不能显示了。

这个时候没有继续管它，去找彩色图标更换去了，想着别的界面不能显示就算了，留个bug之后再修。彩色图标换完之后又回头看，想办法解决这个bug。

最初想到的是因为大小写不一样的问题，有的是ImageId，有的是imageId，于是统一改为首字母大写。全局搜索了所有的ImageId来统一大小写，改完之后还是不行。

其次想到了可能是因为某个部件设置的问题，因为出问题的都是使用的item_main_lv.xml界面，这个布局的最左边图标缺少。就开始找xml里面有没有指定图片为sImageId的，仔仔细细检查发现并没有。那就去找适配器，看看是不是在设置imageView 的 source的时候设置错误，这个时候一直反复在DB和adapter的代码中反复查找，代码读了一遍又一遍，还是没发现问题。

最后在DBManager中发现读取数据库内容的时候有个地方还是imageId，首字母小写的，很激动，以为找到了问题所在。结果统一改为大写之后程序打不开了，改回来就可以打开，反复尝试确定是改这个大写的问题。这就很难受了，找到了一个不统一的地方竟然不能改，那只好把全部的大写改为小写了。

最后的最后，DBManager中，就在紧挨着上面那个读取数据库内容下面有个插入数据的函数，也就是首页的记账会调用的这个插入函数。突然发现它并没有传入ImageId！应该是之前传入的是sImageId，我没有仔细分析这个函数的作用，一看到sImageId就想现在都没有sImageId了，那就直接删了吧，导致记录的时候ImageId字段是空的，所以别的界面都不会显示图标。

从昨天晚上到现在（2022 12.16 13:23），历经三四个小时，不断的读代码检查代码，终于解决了这个问题！

原来就是插入的时候根本没加入这个ImageId，坏在根上了，我一直查的是哪里有sImageId和ImageId，当然找不到这个缺了ImageId的地方。最终能够发现这个错误就在于我是下定决心一行代码一行代码去检查，终于搞定了。

遗留一个问题，最初读取的时候imageId，是小写，之所以不影响功能应该是因为数据库里面大小写不区分，而别的地方只要做到内部大小写统一就可以，反正都是直接调用传参，大小写不必统一。但是大小写统一确实是个好习惯，然而在这里改了软件就无法打开了，不知道这个的深层原因是什么。可能是因为有之前遗留的数据（之前更改图标的时候遇到过这个问题）？但是我已经完全卸载重装了。可能是数据库创建只能小写字母？这个查了一些资料也没搞明白。

不过，一旦代码能跑，那就不要再动它了。