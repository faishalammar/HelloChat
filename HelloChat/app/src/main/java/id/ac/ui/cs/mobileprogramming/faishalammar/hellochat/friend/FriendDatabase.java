package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Friend.class}, version = 1)
public abstract class FriendDatabase extends RoomDatabase {

    private static FriendDatabase instance;
    public abstract FriendDao friendDao();

    public static synchronized FriendDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FriendDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private FriendDao friendDao;

        private PopulateDbAsyncTask(FriendDatabase db){
            friendDao = db.friendDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            friendDao.insert(new Friend("faishal-ammar", 8787812));
            friendDao.insert(new Friend("username-user", 8787812));
            friendDao.insert(new Friend("user-empat", 8787812));
            return null;
        }
    }

}