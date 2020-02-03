package fr.openium.pam;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<ChuckNorrisFact>> facts = new MutableLiveData<>();

    public MyViewModel() {
        ChuckNorrisApi api = initRetrofit();
        getFacts(api);
    }

    public LiveData<List<ChuckNorrisFact>> getFactsLiveData() {
        return facts;
    }

    private ChuckNorrisApi initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.chucknorrisfacts.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ChuckNorrisApi.class);
    }

    private void getFacts(ChuckNorrisApi api) {
        api.getFacts().enqueue(new Callback<List<ChuckNorrisFact>>() {
            @Override
            public void onResponse(Call<List<ChuckNorrisFact>> call, Response<List<ChuckNorrisFact>> response) {
                List<ChuckNorrisFact> results = response.body();
                facts.setValue(results);
            }

            @Override
            public void onFailure(Call<List<ChuckNorrisFact>> call, Throwable t) {
                facts.setValue(new ArrayList<>());
            }
        });
    }
}
