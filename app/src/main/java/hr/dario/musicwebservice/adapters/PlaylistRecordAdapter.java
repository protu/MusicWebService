package hr.dario.musicwebservice.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.db.model.DbRecording;
import hr.dario.musicwebservice.model.Recording;

public class PlaylistRecordAdapter extends RecyclerView.Adapter<PlaylistRecordAdapter.RecordViewHolder> {

    private List<DbRecording> recordingList;

    public PlaylistRecordAdapter(List<DbRecording> recordingList) {
        this.recordingList = recordingList;
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvRecordTitle;
        private final TextView tvArtistCredit;
        private final TextView tvRelease;
        private final LinearLayout llyRecord;


        public RecordViewHolder(View viewRecord) {
            super(viewRecord);
            tvArtistCredit = viewRecord.findViewById(R.id.tvArtistCredit);
            tvRecordTitle = viewRecord.findViewById(R.id.tvRecordTitle);
            tvRelease = viewRecord.findViewById(R.id.tvRelease);
            llyRecord = viewRecord.findViewById(R.id.llyRecord);
        }

        public TextView getTvRecordTitle() {
            return tvRecordTitle;
        }

        public TextView getTvArtistCredit() {
            return tvArtistCredit;
        }

        public TextView getTvRelease() {
            return tvRelease;
        }

        public LinearLayout getLlyRecord() {
            return llyRecord;
        }

    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        RecordViewHolder recordViewHolder = new RecordViewHolder(view);
        return recordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder recordViewHolder, int i) {

        try {
            DbRecording recording = this.recordingList.get(i);
            recordViewHolder.getTvRecordTitle().setText(recording.getTitle());
            recordViewHolder.getTvArtistCredit().setText(recording.getArtistCredit());
            recordViewHolder.getTvRelease().setText(recording.getRelease());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void changeData(List<DbRecording> recordingList) {
        this.recordingList = recordingList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recordingList.size();
    }


}
