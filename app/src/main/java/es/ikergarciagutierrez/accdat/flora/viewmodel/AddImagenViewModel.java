package es.ikergarciagutierrez.accdat.flora.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import es.ikergarciagutierrez.accdat.flora.model.Repository;
import es.ikergarciagutierrez.accdat.flora.model.entity.Imagen;

/**
 * Clase que controla el ViewModel para añadir imagenes a los objetos Flora
 */
public class AddImagenViewModel extends AndroidViewModel {

    /**
     * Campos de la clase
     */
    private Repository repository;

    /**
     * Constructor de la clase
     *
     * @param application
     */
    public AddImagenViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    /**
     * Método que guarda el objeto Imagen en la base de datos
     *
     * @param intent Objeto Intent
     * @param imagen Objeto Imagen que queremos guardar
     */
    public void saveImagen(Intent intent, Imagen imagen) {
        repository.saveImagen(intent, imagen);
    }
}
