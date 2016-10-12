package com.carloseachaves.retrofit.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carloseachaves.retrofit.R;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();

        //TODO melhorar click recycle view
        //TODO delete reclycle view swipe
        //TODO mensagens de callback
        //TODO layout
        //TODO outra entidade
        //TODO code review
    }

    private void findViewById(){
        Button mButtonBooks =(Button)findViewById(R.id.activity_main_btn_books);
        mButtonBooks.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.activity_main_btn_books:
                Intent it = new Intent(MainActivity.this, BookListActivity.class);
                startActivity(it);
                break;
        }
    }
}
