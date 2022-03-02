package es.ikergarciagutierrez.accdat.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import es.ikergarciagutierrez.accdat.flora.model.Repository;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.model.entity.RowsResponse;

/**
 * Clase que controla el ViewModel para editar y borra objetos Flora
 */
public class EditFloraViewModel extends AndroidViewModel {

    /**
     * Campos de la clase
     */
    private Repository repository;

    /**
     * Constructor de la clase
     *
     * @param application
     */
    public EditFloraViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    /**
     * Método que borra el objeto Flora con el id que le pasemos como parámetro
     *
     * @param id ID del objeto Flora que queremos borrar
     */
    public void deleteFlora(long id) {
        repository.deleteFlora(id);
    }

    /**
     * Método que edita el objeto Flora con el id que le pasemos como parámetro con el objeto Flora
     * que le pasemos como segundo parámetro
     *
     * @param id    ID del objeto Flora que queremos editar
     * @param flora Objeto Flora por el queremos sustituirlo
     */
    public void editFlora(long id, Flora flora) {
        repository.editFlora(id, flora);
    }
}
