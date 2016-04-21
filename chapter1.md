# App Components

Android官方文档中，组件包括Activity,Service,ContentProvider以及BroadcastReceiver. 各组件是可以独立执行并提供了相互调用的机制--Intent机制。
##Intent
##Activity
Activity意为“活动”，在Android中代表着一个独立的屏幕相关的活动，可以使用startActivity或者startActivityForResult启动。
##Service
Service是一个UI无关的后台活动，但可以为其他UI相关的组件服务。可以使用startService或者bindService启动一个Service并与其通信。
##BroadcastReceiver
Broadcast意为“广播消息”，是所有应用程序都可以使用和接收的。可以使用sendBroadcast, sendOrderedBroadcast, sendStickyBroadcast发送广告消息。（sendStickyBroadcast是不安全的，sdk 21之后已将其废弃）。
##ContentProvider
##App Widgets
