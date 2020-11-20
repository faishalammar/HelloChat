package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Message.class}, version = 1, exportSchema = false)
public abstract class MessageDatabase extends RoomDatabase {

    private static MessageDatabase instance;
    public abstract MessageDao messageDao();

    public static synchronized MessageDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MessageDatabase.class, "user_database")
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
        private MessageDao messageDao;

        private PopulateDbAsyncTask(MessageDatabase db){
            messageDao = db.messageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            messageDao.insert(new Message("faishal-ammar", 8787812));
//            messageDao.insert(new Message("username-user", 8787812));
//            messageDao.insert(new Message("user-empat", 8787812));
            return null;
        }
    }

}
