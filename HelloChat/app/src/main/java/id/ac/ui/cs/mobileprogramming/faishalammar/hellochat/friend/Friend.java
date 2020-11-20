package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend;

import java.util.List;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.User;

@Entity(tableName = "friend_table")
public class Friend {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Long phoneNumber;
    private String username;

    @Ignore
    private List<Message> listMessage = null;

    @ForeignKey
            (entity = User.class,
                    parentColumns = "id",
                    childColumns = "userId")
    private Integer userId;

    @ForeignKey
            (entity = Message.class,
                    parentColumns = "id",
                    childColumns = "messageId")
    private Integer messageId;

    public Friend(Long phoneNumber, String username) {
        super();
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
