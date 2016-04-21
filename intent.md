Intent是不同组件之间通信的消息对象，主要有三大作用
　* 启动一个activity
  * 启动一个service
　* 发送一个broadcast
##Intent类型
  * explicit intent:指定目标组件类名.示例代码如下:
~~~
// Executed in an Activity, so 'this' is the Context
// The fileUrl is a string URL, such as "http://www.example.com/image.png"
Intent downloadIntent = new Intent(this, DownloadService.class);
downloadIntent.setData(Uri.parse(fileUrl));
startService(downloadIntent);
~~~

  * implicit intent:指定action,不指定组件类名.在AndroidManifest.xml声明了包含该action的intent-filter的组件如果只有一个，
则会直接将intent发给他，否则系统会提供一个选择框让用户选择将该intent发给谁．
  **注意**:　
    1. 如果使用implicit intent 执行bindservice是会有安全问题的，从sdk 21开始如果你这么用，会抛出异常的.
    2. 如果没有任何组件处理该Intent，则系统会抛出异常，所以在时候使用implicit intent之前都应当使用resolveActivity检查是否
    有组件可以处理该Intent,示例代码如下:
~~~
// Create the text message with a string
Intent sendIntent = new Intent();
sendIntent.setAction(Intent.ACTION_SEND);
sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
if (sendIntent.resolveActivity(getPackageManager()) != null) {
    startActivity(sendIntent);
}
~~~
另外，如果希望每次发起implicit　intent的时候都弹出应用选择提示框，则需要显式的调用createChooser,示例如下:
~~~
Intent sendIntent = new Intent(Intent.ACTION_SEND);
...

// Always use string resources for UI text.
// This says something like "Share this photo with"
String title = getResources().getString(R.string.chooser_title);
// Create intent to show the chooser dialog
Intent chooser = Intent.createChooser(sendIntent, title);

// Verify the original intent will resolve to at least one activity
if (sendIntent.resolveActivity(getPackageManager()) != null) {
    startActivity(chooser);
}
~~~
/cat
##Intent组成
Intent由两种信息组成，一种是给操作系统决定将Intent发给谁的信息，另一种是给接受者用于辅助处理该Intent的信息.
详细包括以下几种:
  * Component name: 用于指定Intent的接收者.
  * Action: 行为的唯一标识符.常用的如ACTION_VIEW, ACTION_SEND.通用的Action定义在Intent类中，其他的则定义在各个子模块中.
  * Data: 指定待处理数据的URI以及MIME类型．
  　**注意**:　setData()和setType()两个方法会互相覆盖，如果要同时设置data和type，应该使用setDataAndType().
  * Category: 指定处理该Intent的组件的类型．常用的为CATEGORY_BROWSABLE和CATEGORY_LAUNCHER．
  **注意**: 包含CATEGORY_LAUNCHER的Activity的icon会被作为该应用在系统launcher上的icon. CATEGORY_LAUNCHER和CATEGORY_BROWSABLE
  必须结对使用，标识该Activity将会被展示在系统launcher上．
  * Extra: Key-Value形式的附加参数信息.
  * Flags: 指定Activity的启动模式等.

##怎样接收一个implicit intent
可以通过在AndroidManifest.xml中设置Intent-filter的方式接收implicit intent.同时，也可以通过以下标签设置一intent的type:
  * <action>
  * <data>
  * <category>
  **注意**:
    1. 如果你不想其他应用接收你的implicit intent, 应当exported属性设置为false.
    2. Android自动将CATEGORY_DEFAULT加入所有使用startActivity和startActivityForResult的Intent,所以必须将CATEGORY_DEFAULT加入intent-filter，否则不能接收implicit intent.
示例如下:
~~~
<activity android:name="ShareActivity">
    <intent-filter>
        <action android:name="android.intent.action.SEND"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <data android:mimeType="text/plain"/>
    </intent-filter>
</activity>
~~~


##Pending Intent
Pending Intent意为未来发起的Intent,即被延时执行，当前使用场景为：
  1. 被NotificationManager使用．
  2. 被App Widget使用．
  3. 被AlarmManager使用．
其构建也是通过工厂方法构建：
PendingIntent.getActivity()用于启动一个Activity.
PendingIntent.getService()用于启动一个Service.
PendingIntent.getBroadcast()用于启动一个BroadcastReciever.
构建参数需要使用Application 的Context.

##Intent解析
当系统收到用户发出的Intent的时候，会基于以下三个方面查找最合适的处理该Intent的组件．
  1. action
  2. intent data( url and type)
  3. category
 
##Intent匹配
Intent不仅是为了激活一个组件，也用于查找设备上的组件相关的信息．例如，Android系统
通过查找所有声明指明了ACTION_MAIN和CATEGORY_LAUNCHER两个类型的intent-filter来找出
应用程序的launcher图标．

不仅是系统，你的应用也可以这么做．PackageManager提供了一系列的query...和resolve...
方法，例如queryIntentActivities(), queryIntentServices(), queryBroadcastReceivers()等．
