package hr.dario.musicwebservice.api;

import hr.dario.musicwebservice.model.Record;
import hr.dario.musicwebservice.model.Recording;

public interface ItemTouchedAdapter {

    void onItemSwiped(int itemId);

    void onItemAdd(Record record);

    void clearData();

    Recording getRecordingItem(int itemId);
}
