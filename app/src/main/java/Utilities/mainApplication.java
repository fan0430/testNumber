package Utilities;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by dai_li_fan on 2015/12/8.
 */
public class mainApplication extends Application implements android.app.Application.ActivityLifecycleCallbacks{
    private static String TAG = "DodoHouseApplication";
    private static mainApplication mApplication;
    private static SQLiteDatabase _db;
//    private static GoogleAnalytics ga_analytics;
//    private static Tracker ga_tracker;
    private Context mContext;
    private String _foreground_activity;
    private Activity _foreground_activity_instance;

    public enum TrackerName {
        DEBUG_TRACKER, ONLINE_TRACKER
    }

//    public GoogleAnalytics getGAnalytics() {return ga_analytics;}
//    public Tracker getGATracker() {return ga_tracker;}

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        mApplication = this;
        mContext = getApplicationContext();
//        Fabric.with(this, new Crashlytics()); //twitter


//        initGA();
//        initDB();
//        initImageLoader();
//        initCrashHandler();
//        initAnalytics();
    }

//    synchronized public Tracker getDefaultTracker() {
//        if (ga_tracker == null) {
//            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//            if (Config.DEBUG_GA) {
//                analytics.setDryRun(true);
//            }
//
//            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
//            ga_tracker = analytics.newTracker(R.xml.global_tracker);
//        }
//        return ga_tracker;
//    }
//
//    private void initAnalytics() {
//        AnalyticsController.init(this);
//    }

//    private void initCrashHandler() {
//        final UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
//
//            public void uncaughtException(Thread thread, Throwable exception) {
//                Log.w(TAG, "I'm custom crash handler");
//                Log.w(TAG, "exception = " + exception.toString());
//                Utility.save(mContext, SharePrefrenceKey.SHOULD_CLOSE_APP, String.valueOf(true));
//                Intent intent = new Intent(mContext, LandingActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//
////				走原來的crash流程
//                defaultHandler.uncaughtException(thread, exception);
//            }
//        };
//
//        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
//    }

    private void initDB() {
        db.mainDBHelper helper = new db.mainDBHelper(getApplicationContext());
        _db = helper.getWritableDatabase();
    }

//    private void initImageLoader() {
//        ImageLoaderConfiguration config;
//        try {
//            config = new ImageLoaderConfiguration.Builder(mContext)
//                    .threadPriority(Thread.NORM_PRIORITY - 2)
//                    .threadPoolSize(5)
//                    .memoryCacheExtraOptions(Utility.getDeviceWidthInPX(mContext), Utility.getDeviceHeightInPX(mContext)) // default = device screen dimensions
//                    .tasksProcessingOrder(QueueProcessingType.FIFO)
//                    .memoryCacheSize(10 * 1024 * 1024)
//                    .diskCache(new LruDiskCache(new File(Utility.getStorageImageCacheDir()), new Md5FileNameGenerator(), 200 * 1024 * 1024))
//                    .writeDebugLogs()
//                    .build();
//            ImageLoader.getInstance().init(config);
//            ImageLoader.getInstance().handleSlowNetwork(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    synchronized public static SQLiteDatabase getDBInstance() {
        return _db;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.i(TAG, activity.getClass().getSimpleName() + " onActivityCreated");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.i(TAG, activity.getClass().getSimpleName() + " onActivityDestroyed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i(TAG, activity.getClass().getSimpleName() + " onActivityPaused");
        _foreground_activity = null;
        _foreground_activity_instance = null;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i(TAG, activity.getClass().getSimpleName() + " onActivityResumed");
        _foreground_activity = activity.getClass().getSimpleName();
        _foreground_activity_instance = activity;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.i(TAG, activity.getClass().getSimpleName() + " onActivitySaveInstanceState");
    }


    @Override
    public void onActivityStarted(Activity activity) {
        Log.i(TAG, activity.getClass().getSimpleName() + " onActivityStarted");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i(TAG, activity.getClass().getSimpleName() + " onActivityStopped");
    }

    public String getActivityForeground() {
        return _foreground_activity;
    }

    public Activity getActivityIntanceForeground() {
        return _foreground_activity_instance;
    }

}
