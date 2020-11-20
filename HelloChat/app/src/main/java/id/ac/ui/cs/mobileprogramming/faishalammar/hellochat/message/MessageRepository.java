package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MessageRepository {
    private MessageDao messageDao;
    private LiveData<List<Message>> allMessages;

    public MessageRepository(Application application) {
        MessageDatabase database = MessageDatabase.getInstance(application);
        messageDao = database.messageDao();
        allMessages = messageDao.getAllMessage();
    }

    public void insert(Message message){
        new InsertMessageAsyncTask(messageDao).execute(message);
    }
    public void delete(Message message){
        new DeleteMessageAsyncTask(messageDao).execute(message);
    }
    public void update(Message message){
        new UpdateMessageAsyncTask(messageDao).execute(message);
    }

    public void deleteAllMessages(){
        new DeleteAllMessageAsyncTask(messageDao).execute();
    }

    public LiveData<List<Message>> getAllMessages(){
        return allMessages ;
    }

    // room didn't allow running on bg
    private static class InsertMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private MessageDao messageDao;
        private InsertMessageAsyncTask(MessageDao messageDao){
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Message... messages){
            messageDao.insert(messages[0]);
            return null;
        }

    }
    private static class UpdateMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private MessageDao messageDao;
        private UpdateMessageAsyncTask(MessageDao messageDao){
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Message... messages){
            messageDao.update(messages[0]);
            return null;
        }

    }
    private static class DeleteMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private MessageDao messageDao;
        private DeleteMessageAsyncTask(MessageDao messageDao){
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Message... messages){
            messageDao.delete(messages[0]);
            return null;
        }

    }
    private static class DeleteAllMessageAsyncTask extends AsyncTask<Void, Void, Void> {
        private MessageDao messageDao;
        private DeleteAllMessageAsyncTask(MessageDao messageDao){
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Void... voids){
            messageDao.deleteAllMessage();
            return null;
        }

    }


}
