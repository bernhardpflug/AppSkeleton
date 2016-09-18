package at.bernhardpflug.appskeleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import at.bernhardpflug.appskeleton.model.DataManager;
import at.bernhardpflug.appskeleton.model.Repository;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager = new DataManager();

        dataManager.getRepos("bernhardpflug").subscribe(new Subscriber<List<Repository>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.d("test","error");
            }

            @Override
            public void onNext(List<Repository> repositories) {
                Log.d("test","check");
            }
        });
    }
}
