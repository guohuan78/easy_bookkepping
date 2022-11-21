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