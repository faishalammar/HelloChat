package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MessageDao {

    @Insert
    void insert(Message message);

    @Update
    void update(Message message);

    @Delete
    void delete(Message message);

    @Query("DELETE FROM message_table")
    void  deleteAllMessage();

    @Query("SELECT * FROM message_table")
    LiveData<List<Message>> getAllMessage();
}
