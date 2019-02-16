package hr.dario.musicwebservice.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hr.dario.musicwebservice.model.Recording;

public class SingleRecordingList {
    private static SingleRecordingList instance = null;
    private List<Recording> recordingList;

    private SingleRecordingList() {
        recordingList = new ArrayList<>();
    }

    public List<Recording> getRecordingList() {
        return recordingList;
    }

    public static SingleRecordingList getInstance() {
        if (instance == null) {
            instance = new SingleRecordingList();
        }
        return instance;
    }

    public void addRecording(Recording recording) {
        recordingList.add(recording);
        Log.d("RECORD_LIST", "ADD: " + recording.toString());
    }

    public void delRecording(int index) {
        Log.d("RECORD_LIST", "DEL: " + recordingList.get(index).toString());
        recordingList.remove(index);
    }

}
