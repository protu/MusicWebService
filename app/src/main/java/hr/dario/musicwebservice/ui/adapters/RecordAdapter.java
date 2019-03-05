package hr.dario.musicwebservice.ui.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.api.ItemTouchedAdapter;
import hr.dario.musicwebservice.db.model.DbRecording;
import hr.dario.musicwebservice.model.Record;
import hr.dario.musicwebservice.model.Recording;

import static hr.dario.musicwebservice.MusicWebServiceApp.database;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> implements ItemTouchedAdapter {

    private Record record;
    private OnListItemClickListener listItemClickListener;

    public RecordAdapter(Record record, OnListItemClickListener listItemClickListener) {
        this.record = record;
        this.listItemClickListener = listItemClickListener;
    }


    public class RecordViewHolder extends RecyclerView.ViewHolder {
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

            ButterKnife.bind(this, itemView);
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

        @OnClick()
        void OnClick() {
            listItemClickListener.listItemClicked(getAdapterPosition());
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
            Recording recording = record.getRecordings().get(i);
            recordViewHolder.getTvRecordTitle().setText(recording.getTitle());
            recordViewHolder.getTvArtistCredit().setText(recording.getStringArtistCredits().trim());
            recordViewHolder.getTvRelease().setText(recording.getStringReleases().trim());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return record.getRecordings().size();
    }

    @Override
    public void onItemSwiped(int itemId) {
        Recording recording = record.getRecording(itemId);

        DbRecording dbRecordings = new DbRecording();
        dbRecordings.setTitle(recording.getTitle().trim());
        dbRecordings.setArtistCredit(recording.getStringArtistCredits().trim());
        dbRecordings.setRelease(recording.getStringReleases().trim());

        database.recTable().insert(dbRecordings);
        record.removeRecording(itemId);

        notifyItemRemoved(itemId);
    }

    @Override
    public void onItemAdd(Record record) {
        this.record.addRecordings(record.getRecordings());
        notifyDataSetChanged();
    }

    @Override
    public void clearData() {
        if (this.record != null) {
            this.record.clearData();
            notifyDataSetChanged();
        }
    }

    @Override
    public Recording getRecordingItem(int itemId) {
        return record.getRecording(itemId);
    }
}
