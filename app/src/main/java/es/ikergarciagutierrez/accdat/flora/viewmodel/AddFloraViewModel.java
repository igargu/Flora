package es.ikergarciagutierrez.accdat.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import es.ikergarciagutierrez.accdat.flora.model.Repository;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;

/**
 * Clase que controla el ViewModel para añadir objetos Flora
 */
public class AddFloraViewModel extends AndroidViewModel {

    /**
     * Campos de la clase
     */
    private Repository repository;

    /**
     * Constructor de la clase
     *
     * @param application
     */
    public AddFloraViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    /**
     * Método que devuelve la lista para añadir objetos Flora
     *
     * @return Lista para añadir objetos Flora
     */
    public MutableLiveData<Long> getAddFloraLiveData() {
        return repository.getAddFloraLiveData();
    }

    /**
     * Método que añade el objeto Flora que le pasemos como parámetro
     *
     * @param flora Objeto Flora que queremos añadir
     */
    public void createFlora(Flora flora) {
        repository.createFlora(flora);
    }
}