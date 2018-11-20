# AndroidKit

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


## 更新说明
### v1.2.0
- Upgrade appcompat to 1.0.2;
- Add ConfirmDialog;
