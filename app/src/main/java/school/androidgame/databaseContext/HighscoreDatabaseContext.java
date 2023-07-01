package school.androidgame.databaseContext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

import school.androidgame.Highscore;

public class HighscoreDatabaseContext extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "hightscores";
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + HighscoreDatabaseContext.TABLE_NAME
                    + "(id integer PRIMARY KEY AUTOINCREMENT not null,"
                    + " points integer not null, "
                    + " creation_date Timestamp not null, "
                    + " time_played integer not null)";
    private static final String SELECT_HIGHSCORE_LIST = "SELECT * FROM "
                    + HighscoreDatabaseContext.TABLE_NAME
                    + " ORDER BY points DESC LIMIT 5;";


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "corbal_db";

    public HighscoreDatabaseContext(@Nullable Context context) {
        super(context, HighscoreDatabaseContext.DATABASE_NAME, null, HighscoreDatabaseContext.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HighscoreDatabaseContext.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + HighscoreDatabaseContext.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public void insertHighscore(Highscore highScore) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("points", highScore.getScore());
        values.put("creation_date", highScore.getTimestamp().getTime());
        values.put("time_played", highScore.getRuntime());

        db.insert(HighscoreDatabaseContext.TABLE_NAME, null, values);
    }

    public ArrayList<Highscore> selectHighscores(int amount) {
        ArrayList<Highscore> highscores = new ArrayList<Highscore>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + HighscoreDatabaseContext.TABLE_NAME + " ORDER BY " +
                "points DESC LIMIT " + amount;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Highscore highscore = new Highscore(
                        cursor.getInt(cursor.getColumnIndex("points")),
                        cursor.getInt(cursor.getColumnIndex("time_played")),
                        new Date(cursor.getInt(cursor.getColumnIndex("creation_date")))
                );
                highscores.add(highscore);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return highscores;
    }
}
