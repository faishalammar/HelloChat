package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.HelloChatDatabase;

public class FriendRepository {
    private FriendDao friendDao;
    private LiveData<List<Friend>> allFriends;

    public FriendRepository(Application application) {
        HelloChatDatabase database = HelloChatDatabase.getInstance(application);
        friendDao = database.friendDao();
        allFriends = friendDao.getAllFriend();
    }

    public void insert(Friend friend){
        new InsertFriendAsyncTask(friendDao).execute(friend);
    }
    public void delete(Friend friend){
        new DeleteFriendAsyncTask(friendDao).execute(friend);
    }
    public void update(Friend friend){
        new UpdateFriendAsyncTask(friendDao).execute(friend);
    }

    public void deleteAllFriends(){
        new DeleteAllFriendAsyncTask(friendDao).execute();
    }

    public LiveData<List<Friend>> getAllFriends(){
        return allFriends ;
    }

    // room didn't allow running on bg
    private static class InsertFriendAsyncTask extends AsyncTask<Friend, Void, Void> {
        private FriendDao friendDao;
        private InsertFriendAsyncTask(FriendDao friendDao){
            this.friendDao = friendDao;
        }
        @Override
        protected Void doInBackground(Friend... friends){
            friendDao.insert(friends[0]);
            return null;
        }

    }
    private static class UpdateFriendAsyncTask extends AsyncTask<Friend, Void, Void> {
        private FriendDao friendDao;
        private UpdateFriendAsyncTask(FriendDao friendDao){
            this.friendDao = friendDao;
        }
        @Override
        protected Void doInBackground(Friend... friends){
            friendDao.update(friends[0]);
            return null;
        }

    }
    private static class DeleteFriendAsyncTask extends AsyncTask<Friend, Void, Void> {
        private FriendDao friendDao;
        private DeleteFriendAsyncTask(FriendDao friendDao){
            this.friendDao = friendDao;
        }
        @Override
        protected Void doInBackground(Friend... friends){
            friendDao.delete(friends[0]);
            return null;
        }

    }
    private static class DeleteAllFriendAsyncTask extends AsyncTask<Void, Void, Void> {
        private FriendDao friendDao;
        private DeleteAllFriendAsyncTask(FriendDao friendDao){
            this.friendDao = friendDao;
        }
        @Override
        protected Void doInBackground(Void... voids){
            friendDao.deleteAllFriend();
            return null;
        }

    }


}
