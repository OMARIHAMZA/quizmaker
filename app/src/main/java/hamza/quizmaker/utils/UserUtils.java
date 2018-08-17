package hamza.quizmaker.utils;

import android.content.Context;
import android.content.SharedPreferences;

import hamza.quizmaker.models.User;

public class UserUtils {

    private static final String SHARED_PREFERENCES_TAG = "SHARED_PREFERENCES";
    private static final String USER_ID_TAG = "USER_ID";
    private static final String USERNAME_TAG = "USERNAME";
    private static final String SCHOOL_TAG = "SCHOOL";
    private static final String AGE_TAG = "AGE";

    public static void loginUser(User user, Context context){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_ID_TAG, user.getId());
        editor.putString(USERNAME_TAG, user.getUsername());
        editor.putString(SCHOOL_TAG, user.getSchool());
        editor.putString(AGE_TAG, user.getAge());
        editor.apply();
    }

    public static boolean isUserLoggedIn(Context context){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        return mSharedPreferences.getString(USERNAME_TAG, "null").equals("null");
    }

    public static String getLoggedUserId(Context context){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        return mSharedPreferences.getString(USER_ID_TAG, "-1");
    }



}
