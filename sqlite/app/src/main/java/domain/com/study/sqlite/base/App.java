package domain.com.study.sqlite.base;

import android.app.Application;
import android.content.Context;


public class App extends Application {

    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        app = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
