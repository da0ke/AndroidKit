# AndroidKit

## 安装
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
```
dependencies {
	implementation 'com.github.da0ke:AndroidKit:{version}'
}
```

## 使用
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

## 更新说明
### v1.2.1
- Add ConfirmDialog's new style;

### v1.2.0
- Upgrade appcompat to 1.0.2;
- Add ConfirmDialog;
