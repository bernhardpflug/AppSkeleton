package at.bernhardpflug.appskeleton.service;

import java.util.List;

import at.bernhardpflug.appskeleton.model.Repository;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by bernhardpflug on 18.09.16.
 */
public interface GithubService {

    @GET("users/{user}/repos")
    Observable<List<Repository>> listRepos(@Path("user") String user);
}
