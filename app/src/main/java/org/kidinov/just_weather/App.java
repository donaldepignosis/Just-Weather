package org.kidinov.just_weather;

import android.app.Application;

import org.kidinov.just_weather.common.injection.component.DaggerRepositoryComponent;
import org.kidinov.just_weather.common.injection.component.RepositoryComponent;
import org.kidinov.just_weather.common.injection.module.ApplicationModule;
import org.kidinov.just_weather.common.injection.module.LocalDataSourceModule;
import org.kidinov.just_weather.common.injection.module.RemoteDataSourceModule;

import timber.log.Timber;

public class App extends Application {
    private RepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        repositoryComponent = DaggerRepositoryComponent.builder()
                .remoteDataSourceModule(new RemoteDataSourceModule())
                .localDataSourceModule(new LocalDataSourceModule())
                .applicationModule(new ApplicationModule(this))
                .build();
        repositoryComponent.inject(this);
    }

    public RepositoryComponent getComponent() {
        return repositoryComponent;
    }

}