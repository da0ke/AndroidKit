# AndroidKit
[![](https://jitpack.io/v/da0ke/AndroidKit.svg)](https://jitpack.io/#da0ke/AndroidKit)
封装部分常用工具类

## 集成方式
Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
	...
	maven { url 'https://www.jitpack.io' }
	}
}
```

Step 2. Add the dependency
[![](https://jitpack.io/v/da0ke/AndroidKit.svg)](https://jitpack.io/#da0ke/AndroidKit)
```
dependencies {
	implementation 'com.github.da0ke:AndroidKit:{version}'
}
```

## 使用方法
### ConfirmDialog
```
new ConfirmDialog.Builder(this)
                .message("确定要删除吗？")
                .positive("删除")
                .negative("取消")
                .onClickPositiveListener(new ConfirmDialog.OnClickPositiveListener() {
                    @Override
                    public void onClick() {
                        AndroidUtils.showMsg("删除");
                    }
                })
                .show();
```
 ![ConfirmDialog.style1](https://raw.githubusercontent.com/da0ke/image/master/1.jpg)
```
new ConfirmDialog.Builder(this, ConfirmDialogStyle.Style2)
                .message("您好，邀请面试需要升级VIP会员")
                .positive("立即升级")
                .negative("取消")
                .onClickPositiveListener(new ConfirmDialog.OnClickPositiveListener() {
                    @Override
                    public void onClick() {
                        AndroidUtils.showMsg("立即升级");
                    }
                }).show();
```
 ![ConfirmDialog.style2](https://raw.githubusercontent.com/da0ke/image/master/2.jpg)

