package davidurbina.disasterrelief;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by davidurbina on 25/02/17.
 */


public class MyApplication extends Application {

    private static  MyApplication sInstance;
    private static Boolean loggedIn;
    public static String username;

    @Override
    public void onCreate() {

        sInstance = this;
        super.onCreate();
        Log.i("Application","Loaded");
    }

    public static MyApplication getsInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
    public static void setUsername(String uName){
        username = uName;
    }

    public static String getUsername(){
        return username;
    }

    public static Boolean getLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(Boolean loggedIn) {
        MyApplication.loggedIn = loggedIn;
    }
}
