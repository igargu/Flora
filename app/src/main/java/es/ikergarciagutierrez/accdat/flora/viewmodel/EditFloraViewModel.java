package es.ikergarciagutierrez.accdat.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import es.ikergarciagutierrez.accdat.flora.model.Repository;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;

public class EditFloraViewModel extends AndroidViewModel {

    private Repository repository;

    public EditFloraViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Flora> getFloraLiveDataId() {
        return repository.getFloraLiveDataId();
    }

    public void deleteFlora(long id) {
        repository.deleteFlora(id);
    }

    public void editFlora(long id, Flora flora) {
        repository.editFlora(id, flora);
    }
}
