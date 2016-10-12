package com.carloseachaves.retrofit.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carloseachaves.retrofit.api.APIgeeInterface;
import com.carloseachaves.retrofit.api.ApiClient;
import com.carloseachaves.retrofit.model.APIgeeResponseError;
import com.carloseachaves.retrofit.model.APIgeeResponseSuccess;
import com.carloseachaves.retrofit.model.Book;
import com.carloseachaves.retrofit.util.Constants;
import com.carloseachaves.retrofit.util.ErrorUtils;

import com.carloseachaves.retrofit.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookEditActivity extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener {

    private final String TAG = this.getClass().getName();

    private APIgeeInterface apiServiceAPIgee;

    private Validator validator;

    @NotEmpty(messageResId = R.string.general_error_required_field)
    private EditText editTextYear;

    @NotEmpty(messageResId = R.string.general_error_required_field)
    private EditText editTextName;

    @NotEmpty(messageResId = R.string.general_error_required_field)
    private EditText editTextAuthor;

    private Button btnSave, btnRemove;

    private boolean edit;
    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);
        findViewById();

        apiServiceAPIgee = ApiClient.getClientAPIgee().create(APIgeeInterface.class);

        uuid = getIntent().getStringExtra(Constants.EXTRA_BOOK_UUID);

        if(uuid!=null && !uuid.isEmpty()){
            edit = true;
            getBook(uuid);
            btnRemove.setVisibility(View.VISIBLE);
            editTextName.setEnabled(false);
            editTextAuthor.requestFocus();
        }

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void findViewById(){
        btnSave = (Button)  findViewById(R.id.activity_book_btn_save);
        btnRemove = (Button)  findViewById(R.id.activity_book_btn_remove);

        btnSave.setOnClickListener(this);
        btnRemove.setOnClickListener(this);


        editTextYear = (EditText)findViewById(R.id.activity_book_edt_year);
        editTextName = (EditText)findViewById(R.id.activity_book_edt_name);
        editTextAuthor =(EditText)findViewById(R.id.activity_book_edt_author);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_book_btn_save:
                validator.validate();
                break;
            case R.id.activity_book_btn_remove:
                removeBook(uuid);
                break;
        }
    }

    private void removeBook(String uuid){
        btnRemove.setEnabled(false);

        Call<APIgeeResponseSuccess> call;

        call = apiServiceAPIgee.removeBook(uuid);


        call.enqueue(new Callback<APIgeeResponseSuccess>() {
            @Override
            public void onResponse(Call<APIgeeResponseSuccess>call, Response<APIgeeResponseSuccess> response) {

                if(response.isSuccessful()){
                    Toast.makeText(BookEditActivity.this, "Sucesso", Toast.LENGTH_LONG).show();
                    clearForm((ViewGroup) findViewById(R.id.activity_book__main_layout));
                    finish();
                }else{
                    APIgeeResponseError error = ErrorUtils.parseError(response);
                    Toast.makeText(BookEditActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }

                btnRemove.setEnabled(true);
            }

            @Override
            public void onFailure(Call<APIgeeResponseSuccess>call, Throwable t) {
                Log.e(TAG, t.toString());
                btnRemove.setEnabled(true);

            }
        });

    }

    private void getBook(String uuid){

        Call<APIgeeResponseSuccess> call = apiServiceAPIgee.getById(uuid);

        call.enqueue(new Callback<APIgeeResponseSuccess>() {
            @Override
            public void onResponse(Call<APIgeeResponseSuccess>call, Response<APIgeeResponseSuccess> response) {

                if(response.isSuccessful()){
                    Book book = response.body().getEntities().get(0);
                    //TODO check 0
                    editTextAuthor.setText(book.getAuthor());
                    editTextName.setText(book.getName());
                    editTextYear.setText(String.valueOf(book.getYear()));

                    editTextYear.setSelection(editTextYear.getText().length());
                    editTextName.setSelection(editTextName.getText().length());
                    editTextAuthor.setSelection(editTextAuthor.getText().length());
                }else{
                    APIgeeResponseError error = ErrorUtils.parseError(response);
                    Toast.makeText(BookEditActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<APIgeeResponseSuccess>call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void registerBook(){

        btnSave.setEnabled(false);

        String name = editTextName.getText().toString();
        String author = editTextAuthor.getText().toString();
        int year = Integer.parseInt((editTextYear.getText().toString()));

        Call<APIgeeResponseSuccess> call;

        if(edit){
            call = apiServiceAPIgee.updateBook(new Book(name, author, year), uuid);
        }else{
            call = apiServiceAPIgee.addBook(new Book(name, author, year));
        }

        call.enqueue(new Callback<APIgeeResponseSuccess>() {
            @Override
            public void onResponse(Call<APIgeeResponseSuccess>call, Response<APIgeeResponseSuccess> response) {

                if(response.isSuccessful()){
                    Toast.makeText(BookEditActivity.this, "Sucesso", Toast.LENGTH_LONG).show();
                    clearForm((ViewGroup) findViewById(R.id.activity_book__main_layout));
                }else{
                    APIgeeResponseError error = ErrorUtils.parseError(response);
                    Toast.makeText(BookEditActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }

                btnSave.setEnabled(true);
            }

            @Override
            public void onFailure(Call<APIgeeResponseSuccess>call, Throwable t) {
                Log.e(TAG, t.toString());
                btnSave.setEnabled(true);

            }
        });
    }

    private void clearForm(ViewGroup group) {
        if(!edit){
            for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                View view = group.getChildAt(i);
                if (view instanceof EditText) {

                    if(i==0){
                        view.requestFocus();
                    }

                    ((EditText)view).setText("");
                }

                if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                    clearForm((ViewGroup)view);
            }
        }
    }

    public void onValidationSucceeded() {
        registerBook();
    }

    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}