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
Loaders是Android3.0开始加入，其主要特点如下:
  1. 可以在每个Activity和Fragment中使用.
  2. 用于异步加载数据.
  3. 监视数据源改变，并发送通知.
  4. 配置改变的时候会自动重新连接数据源，不需要重新查询．
Loader是从ContentProvider中异步加载数据的最佳方式．不过可以使用RxJava替换.
##Tasks and Back Stack
Task是一个或者多个Activity组成，Task中的Activity以Stack的形式进出Task的.
###launchMode
  * standard
    默认情况下，每次startActivity系统都会创建一个全新的Activity.

  * singleTop
    应该叫single if at top.只有该Activity在Task堆栈顶端的时候，不会创建一个全新的，而是回调其onNewIntent
    ，如果该Activity不在顶端，则其行为与standard一致，会创建一个全新的．
    
  * singleTask
    系统创建一个全新的Task并将该Activity作为根Activity,但之后再启动的Activity可以进入
    该Task.
    
  * singleInstance
    与singleTask类似，但是系统不会再向其所在的Task插入任何Activity.
默认情况下，一个Activity会与启动它的Activity在同一个Task,但如果使用ApplicationContext启动
一个Activity,则没有Task与之关联，需要给定FLAG_ACTIVITY_NEW_TASK标志位.

###Affinity
相当于Task的标识，如果某个Activity指定了自己所属的Affinity，启动的时候使用FLAG_ACTIVITY_NEW_TASK标志且
当前已经存在一个有同样Affinity的Task,则不会启动一个新的Task,而是使用该Task.
###allowTaskReparenting
###Clear Back Stack
默认情况下，如果用户离开一个Task比较长的时间，系统就会清除除了root task之外的所有Task. 当用户返回该Task的时候，
只有Root Task会被Restored.

以下属性只有设置为Task的root activity上才会生效．
  * alwayRetainTaskState
    作用于Task的Root Activity.即使用户离开了很长时间,系统也不会清除Task.

  * clearTaskOnLaunch
    作用于Task的Root Activity.另一个极端，只要用户离开（即使不是很长时间），系统也会清除Task(除跟节点)．
    
  * finishOnTaskLaunch
    作用于单个Activity而不是整个Task,它可以作用于包括根节点在内的所有Activity.
##Overview Screen
