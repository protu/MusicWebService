package hr.dario.musicwebservice.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.adapters.PlaylistRecordAdapter;
import hr.dario.musicwebservice.api.SingleRecordingList;

import static hr.dario.musicwebservice.util.AppConst.RECORDING_SEND;
import static hr.dario.musicwebservice.util.AppConst.RECORDING_UPDATE;

public class PlayListFragment extends Fragment {
    public static PlayListFragment newInstance() {
        return new PlayListFragment();
    }

    @BindView(R.id.rvRecordPlayList)
    RecyclerView rvRecordPlayList;

    private PlaylistRecordAdapter adapter;
    private BroadcastReceiver breceiver;
    SingleRecordingList singleRecordingList = SingleRecordingList.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.playlist_fragment, container, false);
        ButterKnife.bind(this, rootView);

        adapter = new PlaylistRecordAdapter(singleRecordingList.getRecordingList());
        RecyclerView.LayoutManager rvLayout;
        rvLayout = new LinearLayoutManager(getContext());
        rvRecordPlayList.setLayoutManager(rvLayout);
        rvRecordPlayList.setAdapter(adapter);

        return rootView;
    }

    private void updateUi() {
        adapter.changeData(singleRecordingList.getRecordingList());
    }

    private void registerUiWatcher() {
        IntentFilter intentFilter = new IntentFilter(RECORDING_UPDATE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        breceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateUi();
            }
        };
        getActivity().registerReceiver(breceiver, intentFilter);
    }

    private void unRegisterUiWatcher() { getActivity().unregisterReceiver(breceiver);}

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
        registerUiWatcher();
    }

    @Override
    public void onPause() {
        super.onPause();
        unRegisterUiWatcher();
    }

}
