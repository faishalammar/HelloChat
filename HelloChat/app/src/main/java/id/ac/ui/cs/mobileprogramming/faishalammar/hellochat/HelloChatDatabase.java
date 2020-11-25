package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.Friend;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.FriendDao;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.MessageDao;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.User;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.UserDao;

@Database(entities = {Friend.class, Message.class, User.class}, version = 4)
public abstract class HelloChatDatabase extends RoomDatabase {

    private static HelloChatDatabase instance;
    public abstract FriendDao friendDao();
    public abstract MessageDao messageDao();
    public abstract UserDao userDao();

    public static synchronized HelloChatDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HelloChatDatabase.class, "hellochatdatabase")
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
        private MessageDao messageDao;
        private UserDao userDao;

        private PopulateDbAsyncTask(HelloChatDatabase db){
            friendDao = db.friendDao();
            messageDao = db.messageDao();
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            friendDao.insert(new Friend(Long.valueOf(8786), "faishalammar"));
            friendDao.insert(new Friend(Long.valueOf(8486), "username123"));
            friendDao.insert(new Friend(Long.valueOf(5786), "new user"));

            userDao.insert(new User("faishalammar", 1294));
            userDao.insert(new User("username123", 1294));
            userDao.insert(new User("new user", 1294));

            userDao.insert(new User("main_user", 1294));

            messageDao.insert(new Message("Hi, faishalammar How are you ?", 4, 1));
            messageDao.insert(new Message("Hello main_user, I'm great", 1, 4));
            messageDao.insert(new Message("is there something i can help ?", 1, 4));
            messageDao.insert(new Message("Nah, I'm okay", 4, 1));

            messageDao.insert(new Message("Can you help me for today homework ?", 2, 4));
            messageDao.insert(new Message("Yes, how about we met at new cafe in detos at 4pm?", 4, 2));
            messageDao.insert(new Message("But treat me okay hehe", 4, 2));
            messageDao.insert(new Message("How about 11am, i have some appointment after ashar ?", 2, 4));

            messageDao.insert(new Message("Hello main_user, are you free today ?", 3, 4));

            return null;
        }
    }

}


