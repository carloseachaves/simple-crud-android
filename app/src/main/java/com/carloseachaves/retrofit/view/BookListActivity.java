package com.carloseachaves.retrofit.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.carloseachaves.retrofit.R;
import com.carloseachaves.retrofit.api.APIgeeInterface;
import com.carloseachaves.retrofit.api.ApiClient;
import com.carloseachaves.retrofit.controller.adpater.BookAdapter;
import com.carloseachaves.retrofit.model.APIgeeResponseSuccess;
import com.carloseachaves.retrofit.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookListActivity extends AppCompatActivity implements  View.OnClickListener {

    private final String TAG = this.getClass().getName();
    private APIgeeInterface apiServiceAPIgee;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        findViewById();

        apiServiceAPIgee = ApiClient.getClientAPIgee().create(APIgeeInterface.class);

        loadBooks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
    }

    private void loadBooks(){

        Call<APIgeeResponseSuccess> call = apiServiceAPIgee.listBooks();

        call.enqueue(new Callback<APIgeeResponseSuccess>() {
            @Override
            public void onResponse(Call<APIgeeResponseSuccess>call, Response<APIgeeResponseSuccess> response) {
                List<Book> books = response.body().getEntities();
                mAdapter = new BookAdapter(books, BookListActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<APIgeeResponseSuccess>call, Throwable t) {
                if(call.isCanceled()){
                    Log.e(TAG, "Cancelado");
                    return;
                }

                Log.e(TAG, t.toString());
            }
        });
    }

    private void findViewById(){
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activity_list_book_fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.activity_list_book_fab:
                Intent it = new Intent(BookListActivity.this, BookEditActivity.class);
                startActivity(it);
                break;
        }
    }
}
