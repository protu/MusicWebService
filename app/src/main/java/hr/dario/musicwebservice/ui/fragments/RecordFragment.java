package hr.dario.musicwebservice.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.api.ItemTouchedAdapter;
import hr.dario.musicwebservice.model.Record;
import hr.dario.musicwebservice.ui.adapters.OnListItemClickListener;
import hr.dario.musicwebservice.ui.adapters.RecordAdapter;
import hr.dario.musicwebservice.ui.dialog.AddRecordingDialog;
import hr.dario.musicwebservice.ui.views.RecordViewModel;
import hr.dario.musicwebservice.util.RecordingIntentService;

import static hr.dario.musicwebservice.R.menu.menu_recordings;
import static hr.dario.musicwebservice.util.AppConst.LIMIT;


public class RecordFragment extends Fragment implements OnListItemClickListener {

    private String searchString;
    private RecordViewModel recordViewModel;
    private Observer<Record> observer;
    private ItemTouchedAdapter itemTouchedAdapter;
    private long myOffset = 0;
    private long recordingsCount;
    private boolean isLoading = false;
    private RecyclerView.Adapter rvAdapter;

    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.etSearch)
    EditText etSearch;

    @BindView(R.id.rvRecordList)
    RecyclerView rvRecordList;

    @BindView(R.id.pbSpinner)
    ProgressBar pbSpinner;


    public static RecordFragment newInstance() {
        return new RecordFragment();
    }


    public void setItemTouchedAdapter(ItemTouchedAdapter itemTouchedAdapter) {
        this.itemTouchedAdapter = itemTouchedAdapter;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.records_fragment, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        RecyclerView.LayoutManager rvLayout;
        rvLayout = new LinearLayoutManager(getContext());
        rvRecordList.setLayoutManager(rvLayout);

        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        observer = new Observer<Record>() {
            @Override
            public void onChanged(@Nullable Record record) {
                updateRecords(record);
            }
        };
        recordViewModel.getRecord().observe(this, observer);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(getActivity(), RecordingIntentService.class);
                getActivity().startService(intent);
                itemTouchedAdapter.onItemSwiped(position);
            }

        });
        itemTouchHelper.attachToRecyclerView(rvRecordList);

        rvRecordList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                int firstPosition = ((LinearLayoutManager) rvRecordList.getLayoutManager()).findFirstVisibleItemPosition();
                int visibleItemCount = rvRecordList.getLayoutManager().getChildCount();
                int totalItemCount = rvRecordList.getLayoutManager().getItemCount();

                if (!isLoading && visibleItemCount + firstPosition >= totalItemCount) {
                    myOffset += LIMIT;
                    if (myOffset < recordingsCount) {
                        searchRecords();
                    } else {
                        myOffset -= LIMIT;
                    }
                }
            }

        });
        return rootView;
    }

    @OnClick(R.id.ibtnSearch)
    public void ibtnSearchClick() {
        if (etSearch.getText().length() > 0) {
            hideKeyboard();
            myOffset = 0;
            searchString = etSearch.getText().toString();
            clearRecords();
            searchRecords();
        } else {
            Toast.makeText(getContext(), getString(R.string.please_enter_search_string), Toast.LENGTH_SHORT).show();
        }
    }

    private void searchRecords() {
        isLoading = true;
        pbSpinner.setVisibility(View.VISIBLE);
        Map<String, String> searchOptions = new HashMap<>();
        searchOptions.put("query", searchString);
        searchOptions.put("limit", String.valueOf(LIMIT));
        searchOptions.put("offset", String.valueOf(myOffset));
        recordViewModel.searchRecord(searchOptions);
        recordViewModel.getRecord().observe(this, observer);
    }


    private void updateRecords(Record record) {
        if (record != null) {
            if (record.getCount() == 0) {
                tvResult.setText(R.string.no_match);
            } else {
                recordingsCount = record.getCount();
                tvResult.setText(getString(R.string.records_found) + ": " + String.valueOf(recordingsCount));
            }
            if (rvAdapter == null) {
                rvAdapter = new RecordAdapter(record, this);
                setItemTouchedAdapter((ItemTouchedAdapter) rvAdapter);
                rvRecordList.setAdapter(rvAdapter);
            } else {
                itemTouchedAdapter.onItemAdd(record);
            }

            pbSpinner.setVisibility(View.GONE);
            isLoading = false;
        }
    }

    private void clearRecords() {
        if (itemTouchedAdapter != null) {
            itemTouchedAdapter.clearData();
        }
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @OnEditorAction(R.id.etSearch)
    protected boolean etSearchEditor(int action) {
        if (action == EditorInfo.IME_ACTION_DONE) {
            ibtnSearchClick();
        }
        return true;
    }

    @Override
    public void listItemClicked(int position) {
        AddRecordingDialog addRecordingDialog = new AddRecordingDialog();
        addRecordingDialog.setAdapter(itemTouchedAdapter);
        addRecordingDialog.setRecordingPosition(position);
        addRecordingDialog.show(getActivity().getSupportFragmentManager(), "AddRecordingDialog");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(menu_recordings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionClear) {
            itemTouchedAdapter.clearData();
        }
        return super.onOptionsItemSelected(item);
    }
}
