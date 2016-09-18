package at.bernhardpflug.appskeleton.model;

import com.google.gson.Gson;

import java.util.List;

import at.bernhardpflug.appskeleton.service.GithubService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bernhardpflug on 18.09.16.
 */
public class DataManager {

    private GithubService githubService;

    public DataManager() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        githubService = retrofit.create(GithubService.class);
    }

    public Observable<List<Repository>> getRepos(String username) {
        return githubService.listRepos(username)
                .compose(applySchedulers());
    }

    // ================================================
    // schedulers transformer
    // ================================================

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
