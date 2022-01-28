package es.ikergarciagutierrez.accdat.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import es.ikergarciagutierrez.accdat.flora.model.Repository;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;

public class MainActivityViewModel extends AndroidViewModel {

    private Repository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }

    public MutableLiveData<ArrayList<Flora>> getFloraLiveData() {
        return repository.getFloraLiveData();
    }

    public void deleteFlora(long id) {
        repository.deleteFlora(id);
    }

    public void getFlora(long id) {
        repository.getFlora(id);
    }

    public void getFlora() {
        repository.getFlora();
    }

    public void createFlora(Flora flora) {
        repository.createFlora(flora);
    }

    public void editFlora(long id, Flora flora) {
        repository.editFlora(id, flora);
    }
}
