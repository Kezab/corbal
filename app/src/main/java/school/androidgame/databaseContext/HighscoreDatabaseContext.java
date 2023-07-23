package school.androidgame.databaseContext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class HighscoreDatabaseContext extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "hightscores";
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + HighscoreDatabaseContext.TABLE_NAME
                    + "(id integer PRIMARY KEY AUTOINCREMENT not null,"
                    + " points integer not null, "
                    + " creation_date Timestamp not null, "
                    + " time_played integer not null)";


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
}
