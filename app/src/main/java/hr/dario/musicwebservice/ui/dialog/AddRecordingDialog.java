package hr.dario.musicwebservice.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.api.ItemTouchedAdapter;
import hr.dario.musicwebservice.util.RecordingIntentService;

public class AddRecordingDialog extends DialogFragment {

    private ItemTouchedAdapter adapter;
    private int position;

    public void setRecordingPosition(int position) {
        this.position = position;
    }

    public void setAdapter(ItemTouchedAdapter adapter) {
        this.adapter = adapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.add_recording));
        builder.setMessage(adapter.getRecordingItem(position).toString());
        builder.setCancelable(false);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), RecordingIntentService.class);
                getActivity().startService(intent);
                adapter.onItemSwiped(position);
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
