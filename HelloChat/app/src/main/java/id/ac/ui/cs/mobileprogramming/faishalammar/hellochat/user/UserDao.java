package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void  deleteAllUser();

    @Query("SELECT * FROM user_table ORDER BY username DESC")
    LiveData<List<User>> getAllUser();

    @Query("SELECT * FROM message_table WHERE userReceiverId= :id" )
    LiveData<List<Message>> getAllMessageWithReceiverId(int id);

    @Query("SELECT * FROM message_table WHERE userSenderId= :id" )
    LiveData<List<Message>> getAllMessageWithSenderId(int id);

}
