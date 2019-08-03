# EmptyRecyclerView

[![](https://jitpack.io/v/koji-1009/EmptyRecyclerView.svg)](https://jitpack.io/#koji-1009/EmptyRecyclerView)

Added setEmptyView method to RecyclerView like ListView#setEmptyView.

## Screen

<img src="https://user-images.githubusercontent.com/17231507/51785324-74838500-2199-11e9-9960-1aeb2d033c57.gif" width="200">

## Gradle

NOTE: 2.x only supports Jetpack. If you use appcompat 1.x which is almost stable is the way to go.

Step1. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Step2. Add the dependency

```groovy
dependencies {
    implementation 'com.github.koji-1009:EmptyRecyclerView:x.y.z'
}
```

## How To Use

Add `EmptyRecyclerView` instead of `RecyclerView`.

```
<com.dr1009.app.emptyrecyclerview.EmptyRecyclerView
    android:id="@+id/recycler"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:emptyView="@layout/sample_empty_view"
    app:layoutManager="LinearLayoutManager"/>
```

If you want to add View by code, use `EmptyRecyclerView.setEmptyView(View emptyView)` or `EmptyRecyclerView.setEmptyView(int resId)`.

## License

```
MIT License

Copyright (c) 2018 Koji Wakamiya

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
