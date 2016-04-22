#Activity

***

Activity代表一个桌面活动，可以通过startActivity或者startActivityForResult启动，通过finish或者finishActivity结束．

##Activity的状态
一个Activity可以以三种形式存在：
  * Resumed: 激活状态，处于前台，拥有焦点，可见.
  * Paused: 暂停状态，处于后台，拥有焦点，可见.（多见于上层Activity透明或不完全覆盖屏幕),仍关联在WindowManager上.
  * Stopped: 停止状态，处于后台，不可见，已经脱离与WindowManager的关联了．

在Activity的系统回调事件中，必须首先调用父类的相应函数，然后执行自身函数.
  * onCreate
  * onStart
  * onResume
  * onPause
  * onStop
  * onDestroy
这六个事件对应三个过程：
  1. 整个生命周期　[onCreate, onDestroy]
  2. 可见生命周期　[onStart, onStop]
  3. 前台生命周期　[onResume, onPause]

Activity A 启动B的回调顺序:
  1. A.onPause
  2. B.onCreate->onStart->onResume
  3. A.onPause(if not visiable)

##Fragment
Fragment 在Android 3.0(11)被加入.最初是为了在大屏幕上支持更加动态和可扩展的UI
Fragment可以不提供UI，你只需要在onCreateView中返回null即可．
应当实现如下接口:
  1. onCreate
  2. onCreateView
  3. onPause
方便的子类:
  * DialogFragment
  * ListFrament
  * PreferenceFragment(like PreferenceActivity)


**注意**:
  * 如果用xml配置Fragment,需要为其添加唯一标识，使用android:id|android:tag,如果没有
  显式指定，则系统会使用它父亲的id来标识.




##Loaders
##Tasks and Back Stack
##Overview Screen
