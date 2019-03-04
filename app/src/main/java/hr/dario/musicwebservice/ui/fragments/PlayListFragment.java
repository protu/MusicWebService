package hr.dario.musicwebservice.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.db.model.DbRecording;
import hr.dario.musicwebservice.ui.adapters.OnListItemClickListener;
import hr.dario.musicwebservice.ui.adapters.PlaylistRecordAdapter;
import hr.dario.musicwebservice.ui.dialog.DeleteAllRecordingDialog;
import hr.dario.musicwebservice.ui.dialog.DeleteRecordListener;
import hr.dario.musicwebservice.ui.dialog.DeleteRecordingDialog;

import static hr.dario.musicwebservice.MusicWebServiceApp.database;
import static hr.dario.musicwebservice.R.menu.menu_playlist;
import static hr.dario.musicwebservice.util.AppConst.RECORDING_UPDATE;

public class PlayListFragment extends Fragment implements DeleteRecordListener, OnListItemClickListener {
    public static PlayListFragment newInstance() {
        return new PlayListFragment();
    }

    @BindView(R.id.rvRecordPlayList)
    RecyclerView rvRecordPlayList;

    private PlaylistRecordAdapter adapter;
    private BroadcastReceiver breceiver;
    private Intent shareIntent;
    private ShareActionProvider shareActionProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.playlist_fragment, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        adapter = new PlaylistRecordAdapter(database.recTable().selectAll(), this);
        RecyclerView.LayoutManager rvLayout;
        rvLayout = new LinearLayoutManager(getContext());
        rvRecordPlayList.setLayoutManager(rvLayout);
        rvRecordPlayList.setAdapter(adapter);
        return rootView;
    }

    private void updateUi() {
        adapter.changeData(database.recTable().selectAll());
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

    private void unRegisterUiWatcher() {
        getActivity().unregisterReceiver(breceiver);
    }

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


    @Override
    public void deleteRecordDialogActionPerformed(DbRecording dbRecording) {
        database.recTable().delete(dbRecording);
        updateUi();
    }

    @Override
    public void deleteAllRecordsDialogActionPerfomed() {
        database.recTable().deleteAll();
        updateUi();
    }

    @Override
    public void listItemClicked(int position) {
        DeleteRecordingDialog deleteRecordingDialog = new DeleteRecordingDialog();
        deleteRecordingDialog.setListener(this);
        deleteRecordingDialog.setDbRecording(database.recTable().selectAll().get(position));
        deleteRecordingDialog.show(getActivity().getSupportFragmentManager(), "DeleteRecordDialog");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(menu_playlist, menu);

        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.actionShare));
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.play_list);
        shareIntent.putExtra(Intent.EXTRA_TEXT, getAllRecordings());
        shareActionProvider.setShareIntent(shareIntent);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.actionClear) {
            DeleteAllRecordingDialog deleteAllRecordingDialog = new DeleteAllRecordingDialog();
            deleteAllRecordingDialog.setListener(this);
            deleteAllRecordingDialog.show(getActivity().getSupportFragmentManager(), "DeleteAllRecordDialog");
        } else if (i == R.id.actionShare) {
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.play_list);
            shareIntent.putExtra(Intent.EXTRA_TEXT, getAllRecordings());
            shareActionProvider.setShareIntent(shareIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    public String getAllRecordings() {
        StringBuilder sb = new StringBuilder();
        for (DbRecording recording : database.recTable().selectAll()) {
            sb.append(recording.getTitle());
            sb.append(";");
            sb.append(recording.getArtistCredit());
            sb.append(";");
            sb.append(recording.getRelease());
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
