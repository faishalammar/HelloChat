package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository repository;
    private LiveData<List<Message>> allMessages;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        repository = new MessageRepository(application);
        allMessages = repository.getAllMessages();
    }

    public void insert(Message message){
        repository.insert(message);
    }

    public void update(Message message){
        repository.update(message);
    }

    public void delete(Message message){
        repository.delete(message);
    }

    public void deleteAll(){
        repository.deleteAllMessages();
    }

    public LiveData<List<Message>> getAllMessages(){
        return allMessages;
    }


}
