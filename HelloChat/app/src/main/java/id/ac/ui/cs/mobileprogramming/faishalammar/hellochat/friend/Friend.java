package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.User;

@Entity(tableName = "friend_table")
public class Friend {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int phoneNumber;
    private String username;

    @ForeignKey
            (entity = User.class,
                    parentColumns = "id",
                    childColumns = "userId")
    private Integer userId;

    public Friend(String username, int phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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
}
