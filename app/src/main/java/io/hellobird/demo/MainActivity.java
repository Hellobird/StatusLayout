package io.hellobird.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import io.hellobird.statuslayout.StatusLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StatusLayout mStatusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStatusLayout = findViewById(R.id.layout_status);
        findViewById(R.id.btn_success).setOnClickListener(this);
        findViewById(R.id.btn_loading).setOnClickListener(this);
        findViewById(R.id.btn_error).setOnClickListener(this);
        findViewById(R.id.btn_empty).setOnClickListener(this);
        findViewById(R.id.btn_reload).setOnClickListener(this);

        // match data
        ListView listView = findViewById(R.id.list_data);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                Arrays.asList("Astro", "Bender", "Petit Four", "Cupcake", "Donut", "Eclair", "Froyo",
                        "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT",
                        "Lollipop", "Marshmallow", "Nougat", "Oreo", "Pie")));
    }


    @Override
    public void onClick(View v) {
        int status = StatusLayout.STATUS_OTHER;
        switch (v.getId()) {
            case R.id.btn_success:
                status = StatusLayout.STATUS_SUCCESS;
                break;
            case R.id.btn_loading:
            case R.id.btn_reload:
                status = StatusLayout.STATUS_LOADING;
                break;
            case R.id.btn_error:
                status = StatusLayout.STATUS_ERROR;
                break;
            case R.id.btn_empty:
                status = StatusLayout.STATUS_EMPTY;
                break;
        }
        mStatusLayout.setStatus(status);
    }
}
