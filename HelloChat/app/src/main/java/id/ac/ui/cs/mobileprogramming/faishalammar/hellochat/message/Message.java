package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message;

import java.sql.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.Friend;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.User;

@Entity(tableName = "message_table")
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String message;

    @ForeignKey
            (entity = User.class,
                    parentColumns = "id",
                    childColumns = "userSenderId")
    private Integer userSenderId;

    @ForeignKey
            (entity = Friend.class,
                    parentColumns = "id",
                    childColumns = "userReceiverId")
    private Integer userReceiverId;

    public Message(String message, Integer userSenderId, Integer userReceiverId) {
        this.message = message;
        this.userSenderId = userSenderId;
        this.userReceiverId = userReceiverId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserSenderId() {
        return userSenderId;
    }

    public void setUserSenderId(Integer userSenderId) {
        this.userSenderId = userSenderId;
    }

    public Integer getUserReceiverId() {
        return userReceiverId;
    }

    public void setUserReceiverId(Integer userReceiverId) {
        this.userReceiverId = userReceiverId;
    }
}
