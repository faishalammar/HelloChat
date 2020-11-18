package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Message> selected = new MutableLiveData<>();

    public void setSelected(Message message){
        selected.setValue(message);
    }
    public MutableLiveData<Message> getSelected() {
        return selected;
    }
}
