package es.ikergarciagutierrez.accdat.flora.model.entity;

public class Imagen {

    public long id, idflora;
    public String nombre, descripcion;

    public Imagen() {
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", idflora=" + idflora +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public Imagen(long id, long idflora, String nombre, String descripcion) {
        this.id = id;
        this.idflora = idflora;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdflora() {
        return idflora;
    }

    public void setIdflora(long idflora) {
        this.idflora = idflora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
