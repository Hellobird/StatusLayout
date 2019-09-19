# StatusView

A layout that makes it easy to manage different status view

# How to use

In the layout

```xml
<io.hellobird.statuslayout.StatusLayout
        android:id="@+id/layout_status"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:empty="@id/layout_empty"
        app:error="@id/layout_error"
        app:init="loading"
        app:loading="@id/layout_loading"
        app:success="@id/list_data">

        <ListView
            android:id="@+id/list_data"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

        </ListView>

        <LinearLayout
            android:id="@+id/layout_loading"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:gravity="center"
            android:orientation="vertical">

            <ProgressBar
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="loading" />
        </LinearLayout>

        <LinearLayout
            android:id="@+id/layout_error"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:gravity="center"
            android:orientation="vertical">

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@drawable/ic_error" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Load error" />

            <Button
                android:id="@+id/btn_reload"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="16dp"
                android:text="RELOAD" />
        </LinearLayout>

        <LinearLayout
            android:id="@+id/layout_empty"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:gravity="center"
            android:orientation="vertical">

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@drawable/ic_empty" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="No data" />
        </LinearLayout>
    </io.hellobird.statuslayout.StatusLayout>
```

In java code

```java
mStatusLayout.setStatus(status);
```

