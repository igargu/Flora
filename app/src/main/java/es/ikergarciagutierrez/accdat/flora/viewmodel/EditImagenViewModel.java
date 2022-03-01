package es.ikergarciagutierrez.accdat.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import es.ikergarciagutierrez.accdat.flora.model.Repository;

public class EditImagenViewModel extends AndroidViewModel {

    private Repository repository;

    public EditImagenViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }
}
