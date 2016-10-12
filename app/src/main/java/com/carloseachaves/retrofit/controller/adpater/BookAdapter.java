package com.carloseachaves.retrofit.controller.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carloseachaves.retrofit.R;
import com.carloseachaves.retrofit.model.Book;
import com.carloseachaves.retrofit.util.Constants;
import com.carloseachaves.retrofit.view.BookEditActivity;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    public Context context;
    private List<Book> listBooks;

    public BookAdapter(List<Book> books, Context context) {
        this.listBooks = books;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_book_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Book book = listBooks.get(position);

        holder.txtAuthor.setText(book.getAuthor());
        holder.txtName.setText(book.getName());
        holder.txtYear.setText(String.valueOf(book.getYear()));


        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(context, BookEditActivity.class);
                it.putExtra(Constants.EXTRA_BOOK_UUID, book.getUuid());
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBooks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final public TextView txtAuthor;
        final public TextView txtName;
        final public TextView txtYear;

        public MyViewHolder(View view) {
            super(view);
            txtAuthor = (TextView) view.findViewById(R.id.author);
            txtName = (TextView) view.findViewById(R.id.name);
            txtYear = (TextView) view.findViewById(R.id.year);
        }
    }


}