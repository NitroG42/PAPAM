package fr.openium.pam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface ChuckNorrisApi {
    @GET("api/get")
    Call<List<ChuckNorrisFact>> getFacts();
}
