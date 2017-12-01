![](https://raw.githubusercontent.com/machao0727/BigImageSimple/master/simplegif/GIF.gif)
</br>
</br>
USE
====
## manifest
```java
<activity
      android:name="com.mc.libray.BigImageActivity"
      android:theme="@style/BigImageTheme"/>
```
## code

```java
/**default***/
new BigImageBuilder(this)
           .setImages(imageList)
           .setOldImage(oldImgs)
           .setCurrentIndex(i)
           .setPlaceHolderUrl(placeholder)
           .show();
/**if list(listview...)***/
new BigImageBuilder(this)
            .setImages(imageList)
            .setOldImage(oldImgs)
            .setPlaceHolderUrl(placeholder)
            .setCurrentIndex(i)
            .setStartPosition("your list FirstVisiblePosition")
            .show();
