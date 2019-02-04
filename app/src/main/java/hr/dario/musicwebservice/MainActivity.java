package hr.dario.musicwebservice;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import hr.dario.musicwebservice.model.Record;
import hr.dario.musicwebservice.views.RecordViewModel;

public class MainActivity extends AppCompatActivity  {
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.etSearch)
    EditText etSearch;

    @BindView(R.id.rvRecordList)
    RecyclerView rvRecordList;


    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayout;
    private RecordViewModel recordViewModel;
    private Observer<Record> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rvLayout = new LinearLayoutManager(this);
        rvRecordList.setLayoutManager(rvLayout);

        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        observer = new Observer<Record>() {
            @Override
            public void onChanged(@Nullable Record record) {
                updateRecords(record);
            }
        };
        recordViewModel.getRecord().observe(this, observer);

    }


    @OnClick(R.id.ibtnSearch)
    public void ibtnSearchClick() {
        if (etSearch.getText().length() > 0) {
            hideKeyboard();
            recordViewModel.searchRecord(etSearch.getText().toString());
            recordViewModel.getRecord().observe(this, observer);
        } else {
            Toast.makeText(this, getString(R.string.please_enter_search_string), Toast.LENGTH_SHORT).show();
        }
    }


    private void updateRecords(Record record) {
        if (record != null) {
            if (record.getCount() == 0) {
                tvResult.setText(R.string.no_match);
            } else {
                tvResult.setText(getString(R.string.records_found) + ": " + String.valueOf(record.getCount()));
            }
            rvAdapter = new RecordAdapter(record);
            rvRecordList.setAdapter(rvAdapter);
        }
    }


    private void hideKeyboard() {
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
