package com.aceplus.e_commerce.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ppa on 12/15/16.
 */

public class MyDatabase extends SQLiteOpenHelper {

    private static final String MYDATABASE = "mydatabase";
    public static String DB_PATH;
    public static final String DB_NAME = "e_commerce.sqlite";
    public static MyDatabase instance = null;
    //public static final String DB_NAME = "myanmar-padauk.db";//after testing, notice to comment
    private SQLiteDatabase paramiDatabase;
    private Context context;

    //constant field for table and key colunm name
    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new MyDatabase(context);
        }
        return instance;
    }

    public MyDatabase(Context context) {

        super(context, DB_NAME, null, 14);

        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {

            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        this.context = context;
    }


    private void createDatabase() throws IOException {

        if (!(new File(DB_PATH + DB_NAME)).exists()) {

            this.getReadableDatabase();
            this.close();
            try {

                // Copy database
                InputStream inputStream = context.getAssets().open(DB_NAME);
                String outFileName = DB_PATH + DB_NAME;
                OutputStream outputStream = new FileOutputStream(outFileName);
                byte[] mBuffer = new byte[1024];
                int mLength;
                while ((mLength = inputStream.read(mBuffer)) > 0) {
                    outputStream.write(mBuffer, 0, mLength);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public SQLiteDatabase getDataBase() {

        try {

            createDatabase();
            if (paramiDatabase == null) {
                paramiDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return paramiDatabase;
    }

    @Override
    public synchronized void close() {

        if (paramiDatabase != null) {

            paramiDatabase.close();
        }

        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        if (newVersion > oldVersion) {
            try {
                createDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
