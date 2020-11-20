package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FriendDao {

    @Insert
    void insert(Friend friend);

    @Update
    void update(Friend friend);

    @Delete
    void delete(Friend friend);

    @Query("DELETE FROM friend_table")
    void  deleteAllFriend();

    @Query("SELECT * FROM friend_table")
    LiveData<List<Friend>> getAllFriend();
}
