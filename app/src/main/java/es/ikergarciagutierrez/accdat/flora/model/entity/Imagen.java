package es.ikergarciagutierrez.accdat.flora.model.entity;

public class Imagen {

    public long id, idflora;
    public String nombre, descripcion;

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", idflora=" + idflora +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
