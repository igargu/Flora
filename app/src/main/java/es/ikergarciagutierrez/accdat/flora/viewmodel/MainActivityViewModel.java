package es.ikergarciagutierrez.accdat.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import es.ikergarciagutierrez.accdat.flora.model.Repository;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;

/**
 * Clase que controla el ViewModel principal
 */
public class MainActivityViewModel extends AndroidViewModel {

    /**
     * Campos de la clase
     */
    private Repository repository;

    /**
     * Constructor de la clase
     *
     * @param application
     */
    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }

    /**
     * Método que devuele la lista de los objetos Flora
     *
     * @return Lista de los objetos Flora
     */
    public MutableLiveData<ArrayList<Flora>> getFloraLiveData() {
        return repository.getFloraLiveData();
    }

    /**
     * Método que obtiene los objetos Flora
     */
    public void getFlora() {
        repository.getFlora();
    }

}
