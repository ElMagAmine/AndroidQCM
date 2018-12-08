package com.magamine.qcm;

import android.app.Application;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Random;

public class MyAppSingleton extends Application {

    public static final String TAG = MyAppSingleton.class.getSimpleName();
    private RequestQueue mRequestQueue = null;
    public static MyAppSingleton mInstance;

    public static int playerScore;
    static String uLogin = "admin";
    static String uPass = "admin";

    private static ArrayList<Integer> takenQuests = new ArrayList<Integer>();
    private static QuestionForm[] QuestionBase;
    public static synchronized MyAppSingleton getInstance() {
        return mInstance;
    }

    public static void setQuestions(QuestionForm[] q){QuestionBase = q;}
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    public static class QuestionForm{
        public QuestionForm(String qquestion, String[] qanswers, int correct, int imag) {
            Qquestion = qquestion;
            Qanswers = qanswers;
            Correct = correct;
            Imag = imag;
        }

        public String Qquestion;
        public String[] Qanswers;
        public int Correct;
        public int Imag;
    }

    /*private static QuestionForm[] QuestionBase = new QuestionForm[]{
            new QuestionForm("ABCD est un carré de centre O. La distance de C à la droite (DB) est :",new String[]{"CD","CO","CB","CA"},1, R.drawable.images),
            new QuestionForm("Dans un triangle, le centre du cercle inscrit est :",new String[]{"Le point d'intersection des médianes.","Le point d'intersection des médiatrices.","Le point d'intersection des bissectrices.","Le point d'intersection des hauteurs."},2,R.drawable.triangle),
            new QuestionForm("(IN)//(HM) et TI = 5 TH = 8 TN = 4",new String[]{"TM = 7","TM = 6.4","TM = 7.5","TM = 2.5"},1,R.drawable.thales),
            new QuestionForm("Le triangle BOF est tels que OB = 13 m, OF = 12,2 m et BF = 4,5 m alors",new String[]{"Le triangle BOF est rectangle en O.","Le triangle BOF est rectangle en B.","Le triangle BOF est rectangle en F.","BOF n'est pas un rectangle."},3,R.drawable.triangle2),
            new QuestionForm("Qui est le president Francais actuel ?",new String[]{"Trump","L7aj","Macron","Nachet"},0,R.drawable.macron),
            new QuestionForm("Quelle est la moitié de 4 plus 4 ?",new String[]{"16","8","6","4"},2,R.drawable.numbers),
            new QuestionForm("Un éléphant rose va dans la mer rouge.\n" +
                    "Quand il sort, comment est il ?",new String[]{"rouge","rose","moitié-moitié","ils ont appris a nager ?"},2,R.drawable.elephants)

    };*/
    private static Random rand = new Random();
    public static QuestionForm getRandomQuestion(){
        if(QuestionBase == null || QuestionBase.length == 0) return null;
        int id = -1;
        while(id == -1 || takenQuests.contains(new Integer(id)))
            id = rand.nextInt(QuestionBase.length);
        takenQuests.add(id);
        if(takenQuests.size() == 7)takenQuests.clear();
        return QuestionBase[id];
    }


    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        playerScore = 0;
        // Required initialization logic here!
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
