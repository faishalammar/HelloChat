package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentListContactBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.FriendViewModel;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.MessageViewModel;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.UserViewModel;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListContactFragment extends Fragment {

    private FragmentListContactBinding binding;
    ArrayList arrayListContact = new ArrayList();

    public ListContactFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_contact, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] columns = new String[] {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID };

        int[] views = new int[] {R.id.contactName, R.id.contactID };

        arrayListContact = getAllContacts();

        ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.getContextOfApplication(),
                R.layout.chat_history, R.id.username, arrayListContact);

        ListView listViewContact = (ListView) binding.listContact;
        listViewContact.setAdapter(adapter);
    }

    private ArrayList getAllContacts() {
        ArrayList<String> nameList = new ArrayList<>();
        Context applicationContext = MainActivity.getContextOfApplication();
        Log.d("ListContact", String.valueOf(applicationContext==null));
        ContentResolver contentResolver = applicationContext.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor != null && cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.d("ListContact", name);
                nameList.add(name);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return nameList;
    }

}



