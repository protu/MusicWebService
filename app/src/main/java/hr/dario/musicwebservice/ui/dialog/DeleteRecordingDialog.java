package hr.dario.musicwebservice.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.db.model.DbRecording;

import static hr.dario.musicwebservice.MusicWebServiceApp.database;

public class DeleteRecordingDialog extends DialogFragment {

    private DeleteRecordListener listener;
    private DbRecording dbRecording;

    public void setDbRecording(DbRecording dbRecording) {
        this.dbRecording = dbRecording;
    }

    public void setListener(DeleteRecordListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.delete_recording));
        builder.setMessage(dbRecording.getTitle());
        builder.setCancelable(false);

        builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.deleteRecordDialogActionPerformed(dbRecording);
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }
}
