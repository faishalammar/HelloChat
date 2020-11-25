package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.HelloChatDatabase;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Message>> allMessageSent;
    private LiveData<List<Message>> allMessageReceived;

    public UserRepository(Application application) {
        HelloChatDatabase database = HelloChatDatabase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUser();
    }

    public void setAllMessageSent(LiveData<List<Message>> allMessageSent) {
        this.allMessageSent = allMessageSent;
    }

    public void setAllMessageReceived(LiveData<List<Message>> allMessageReceived) {
        this.allMessageReceived = allMessageReceived;
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }
    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }
    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }
    public void deleteAllUsers(){
        new DeleteAllUserAsyncTask(userDao).execute();
    }
    public LiveData<List<User>> getAllUsers(){
        return allUsers ;
    }
    public LiveData<List<Message>> getAllMessageSent(int userId) {
        GetMessageBySenderIdAsyncTask asyncTask = new GetMessageBySenderIdAsyncTask(userDao);
        asyncTask.execute(userId);
        return asyncTask.getListMessage();
    }
    public LiveData<List<Message>> getAllMessageReceived(int userId) {
        GetMessageByReceiverIdAsyncTask asyncTask = new GetMessageByReceiverIdAsyncTask(userDao);
        asyncTask.execute(userId);
        return asyncTask.getListMessage();
    }

    // room didn't allow running on bg
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users){
            userDao.insert(users[0]);
            return null;
        }

    }
    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private UpdateUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users){
            userDao.update(users[0]);
            return null;
        }

    }
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private DeleteUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users){
            userDao.delete(users[0]);
            return null;
        }

    }
    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private DeleteAllUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(Void... voids){
            userDao.deleteAllUser();
            return null;
        }

    }
    private static class GetMessageByReceiverIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        private UserDao userDao;
        private LiveData<List<Message>> listMessage;

        private GetMessageByReceiverIdAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        public LiveData<List<Message>> getListMessage() {
            return listMessage;
        }


        @Override
        protected Void doInBackground(Integer... userId){
            listMessage = userDao.getAllMessageWithReceiverId(userId[0]);
            return null;
        }

    }
    private static class GetMessageBySenderIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        private UserDao userDao;
        private LiveData<List<Message>> listMessage;

        private GetMessageBySenderIdAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        public LiveData<List<Message>> getListMessage() {
            return listMessage;
        }

        @Override
        protected Void doInBackground(Integer... userId){
            listMessage = userDao.getAllMessageWithSenderId(userId[0]);
            return null;
        }

    }


}
