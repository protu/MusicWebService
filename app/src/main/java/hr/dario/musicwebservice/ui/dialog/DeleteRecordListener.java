package hr.dario.musicwebservice.ui.dialog;

import hr.dario.musicwebservice.db.model.DbRecording;

public interface DeleteRecordListener {

    void deleteRecordDialogActionPerformed(DbRecording dbRecording);
}
