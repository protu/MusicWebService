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
import hr.dario.musicwebservice.model.ArtistCredit;
import hr.dario.musicwebservice.model.Record;
import hr.dario.musicwebservice.model.Recording;
import hr.dario.musicwebservice.model.Release;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private Record record;

    public RecordAdapter(Record record) {
        this.record = record;
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
            Recording recording = record.getRecordings().get(i);
            recordViewHolder.getTvRecordTitle().setText(recording.getTitle());
            List<ArtistCredit> artistList = recording.getArtistCredit();
            StringBuilder sbArtists = new StringBuilder();
            for (ArtistCredit artistCredit : artistList) {
                String artistName = artistCredit.getArtist().getName();
                if (artistName != null && !artistName.isEmpty()) {
                    sbArtists.append(artistName);
                    sbArtists.append(System.getProperty("line.separator"));
                }
            }
            recordViewHolder.getTvArtistCredit().setText(sbArtists.toString().trim());

            List<Release> releaseList = recording.getReleases();
            StringBuilder sbReleases = new StringBuilder();

            for (Release release : releaseList) {
                String releaseTitle = release.getTitle();
                if (releaseTitle != null && !releaseTitle.isEmpty())
                    sbReleases.append(release.getTitle());
                String releaseDate = release.getDate();
                if (releaseDate != null && !releaseDate.isEmpty())
                    sbReleases.append(" (" + release.getDate() + ")");
                if (!sbReleases.toString().isEmpty()) {
                    sbReleases.append(System.getProperty("line.separator"));
                }
            }
            recordViewHolder.getTvRelease().setText(sbReleases.toString().trim());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return record.getRecordings().size();
    }


}
