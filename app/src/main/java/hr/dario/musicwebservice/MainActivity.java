package hr.dario.musicwebservice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.dario.musicwebservice.adapters.RecordAdapter;
import hr.dario.musicwebservice.api.IRecord;
import hr.dario.musicwebservice.model.Record;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Record> {
    IRecord iRecord;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.etSearch)
    EditText etSearch;

    @BindView(R.id.rvRecordList)
    RecyclerView rvRecordList;

    Record record;

    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupRestAdapter();
        rvLayout = new LinearLayoutManager(this);
        rvRecordList.setLayoutManager(rvLayout);

    }

    private void setupRestAdapter() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(IRecord.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iRecord = restAdapter.create(IRecord.class);
    }

    private void getRecord(String title) {
        iRecord.getRecord(title).enqueue(this); // send request (async) and notify callback (this)
    }

    @OnClick(R.id.ibtnSearch)
    public void ibtnSearchClick() {
        if (etSearch.getText().length() > 0) {
            hideKeyboard();
            getRecord(etSearch.getText().toString());
        } else {
            Toast.makeText(this, getString(R.string.please_enter_search_string), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(@NonNull Call<Record> call, @NonNull Response<Record> response) {
        record = response.body();
        if (record != null) {
            if (record.getCount() == 0) {
                tvResult.setText(R.string.no_match);
            } else {
                tvResult.setText(getString(R.string.records_found) + ": " + String.valueOf(record.getCount()));
                rvAdapter = new RecordAdapter(record);
                rvRecordList.setAdapter(rvAdapter);
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<Record> call, @NonNull Throwable t) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show();
        tvResult.setText("");
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.actionExit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
