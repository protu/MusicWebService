package hr.dario.musicwebservice.ui.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import hr.dario.musicwebservice.api.IRecord;
import hr.dario.musicwebservice.model.Record;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecordViewModel extends ViewModel implements Callback<Record> {

    private MutableLiveData<Record> records;

    private IRecord iRecord;

    public RecordViewModel() {
        setupRestAdapter();
    }

    private void setupRestAdapter() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(IRecord.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iRecord = restAdapter.create(IRecord.class);
    }

    public LiveData<Record> getRecord() {
        if (records == null) {
            records = new MutableLiveData<>();
        }
        return records;
    }

    public void searchRecord(String title) {
        records = new MutableLiveData<>();
        iRecord.getRecord(title).enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<Record> call, @NonNull Response<Record> response) {
        records.setValue(response.body());
    }

    @Override
    public void onFailure(@NonNull Call<Record> call, @NonNull Throwable t) {

    }
}
