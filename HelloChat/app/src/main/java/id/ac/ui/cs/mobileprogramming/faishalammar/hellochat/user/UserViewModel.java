package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user){
        repository.insert(user);
    }

    public void update(User user){
        repository.update(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public void deleteAll(){
        repository.deleteAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public LiveData<List<Message>> getAllMessageWithReceiverId(int id){ return repository.getAllMessageReceived(id);}
    public LiveData<List<Message>> getAllMessageWithSenderId(int id){ return repository.getAllMessageSent(id);}


}
