package hr.dario.musicwebservice.api;

import java.util.Map;

import hr.dario.musicwebservice.model.Record;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface IRecord {
    String URL = "http://musicbrainz.org";

    @GET("/ws/2/recording?fmt=json")
    Call<Record> getRecord(@QueryMap Map<String, String> searchOptions);
}
