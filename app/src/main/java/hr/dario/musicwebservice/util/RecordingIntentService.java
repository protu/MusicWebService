package hr.dario.musicwebservice.util;

import android.app.IntentService;
import android.content.Intent;

import org.jetbrains.annotations.Nullable;

import static hr.dario.musicwebservice.util.AppConst.RECORDING_UPDATE;


public class RecordingIntentService extends IntentService {

    public RecordingIntentService() {
        super("RecordingIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            sendBroadcast(new Intent(RECORDING_UPDATE));
        }
    }
}
