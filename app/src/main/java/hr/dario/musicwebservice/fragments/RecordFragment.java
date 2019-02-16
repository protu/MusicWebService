package hr.dario.musicwebservice.fragments;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.adapters.RecordAdapter;
import hr.dario.musicwebservice.api.ItemTouchedAdapter;
import hr.dario.musicwebservice.model.Record;
import hr.dario.musicwebservice.util.RecordingIntentService;
import hr.dario.musicwebservice.views.RecordViewModel;

import static hr.dario.musicwebservice.util.AppConst.RECORDING_SEND;


public class RecordFragment extends Fragment {

    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.etSearch)
    EditText etSearch;

    @BindView(R.id.rvRecordList)
    RecyclerView rvRecordList;

    private RecordViewModel recordViewModel;
    private Observer<Record> observer;

    public static RecordFragment newInstance() {
        return new RecordFragment();
    }

    private ItemTouchedAdapter itemTouchedAdapter;

    public void setItemTouchedAdapter(ItemTouchedAdapter itemTouchedAdapter) {
        this.itemTouchedAdapter = itemTouchedAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.records_fragment, container, false);
        ButterKnife.bind(this, rootView);
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
                intent.putExtra(RECORDING_SEND, recordViewModel.getRecord().getValue().getRecordings().get(position));
                getActivity().startService(intent);
                itemTouchedAdapter.onItemSwiped(position);
                updateRecords(recordViewModel.getRecord().getValue());

            }
        });
        itemTouchHelper.attachToRecyclerView(rvRecordList);

        return rootView;
    }

    @OnClick(R.id.ibtnSearch)
    public void ibtnSearchClick() {
        if (etSearch.getText().length() > 0) {
            hideKeyboard();
            recordViewModel.searchRecord(etSearch.getText().toString());
            recordViewModel.getRecord().observe(this, observer);
        } else {
            Toast.makeText(getContext(), getString(R.string.please_enter_search_string), Toast.LENGTH_SHORT).show();
        }
    }


    private void updateRecords(Record record) {
        if (record != null) {
            if (record.getCount() == 0) {
                tvResult.setText(R.string.no_match);
            } else {
                tvResult.setText(getString(R.string.records_found) + ": " + String.valueOf(record.getCount()));
            }
            RecyclerView.Adapter rvAdapter = new RecordAdapter(record);
            setItemTouchedAdapter((ItemTouchedAdapter) rvAdapter);
            rvRecordList.setAdapter(rvAdapter);
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
        Log.d("ACTION_BUTTON", String.valueOf(action));
        if (action == EditorInfo.IME_ACTION_DONE) {
            ibtnSearchClick();
        }
        return true;
    }


}
