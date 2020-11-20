package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FriendViewModel extends AndroidViewModel {
    private FriendRepository repository;
    private LiveData<List<Friend>> allFriends;

    public FriendViewModel(@NonNull Application application) {
        super(application);
        repository = new FriendRepository(application);
        allFriends = repository.getAllFriends();
    }

    public void insert(Friend friend){
        repository.insert(friend);
    }

    public void update(Friend friend){
        repository.update(friend);
    }

    public void delete(Friend friend){
        repository.delete(friend);
    }

    public void deleteAll(){
        repository.deleteAllFriends();
    }

    public LiveData<List<Friend>> getAllFriends(){
        return allFriends;
    }


}
