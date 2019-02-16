package hr.dario.musicwebservice.util;

import android.app.IntentService;
import android.content.Intent;

import org.jetbrains.annotations.Nullable;

import hr.dario.musicwebservice.api.SingleRecordingList;
import hr.dario.musicwebservice.model.Recording;

import static hr.dario.musicwebservice.util.AppConst.RECORDING_SEND;
import static hr.dario.musicwebservice.util.AppConst.RECORDING_UPDATE;


public class RecordingIntentService extends IntentService {

    public RecordingIntentService() {
        super("RecordingIntentService");
    }

    SingleRecordingList singleRecordingList = SingleRecordingList.getInstance();

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final Recording recording = (Recording)intent.getSerializableExtra(RECORDING_SEND);
            singleRecordingList.addRecording(recording);
            sendBroadcast(new Intent(RECORDING_UPDATE));
        }
    }
}
