package es.ikergarciagutierrez.accdat.flora.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Flora implements Parcelable {

    private long id;
    private String correo, nombre, familia, identificacion, altitud, habitat, fitosociologia, biotipo,
            biologia_reproductiva, floracion, fructificacion, expresion_sexual, polinizacion,
            dispersion, numero_cromosomatico, reproduccion_asexual, distribucion, biologia,
            demografia, amenazas, medidas_propuestas;

    public Flora() {
    }

    protected Flora(Parcel in) {
        id = in.readLong();
        correo = in.readString();
        nombre = in.readString();
        familia = in.readString();
        identificacion = in.readString();
        altitud = in.readString();
        habitat = in.readString();
        fitosociologia = in.readString();
        biotipo = in.readString();
        biologia_reproductiva = in.readString();
        floracion = in.readString();
        fructificacion = in.readString();
        expresion_sexual = in.readString();
        polinizacion = in.readString();
        dispersion = in.readString();
        numero_cromosomatico = in.readString();
        reproduccion_asexual = in.readString();
        distribucion = in.readString();
        biologia = in.readString();
        demografia = in.readString();
        amenazas = in.readString();
        medidas_propuestas = in.readString();
    }

    public static final Creator<Flora> CREATOR = new Creator<Flora>() {
        @Override
        public Flora createFromParcel(Parcel in) {
            return new Flora(in);
        }

        @Override
        public Flora[] newArray(int size) {
            return new Flora[size];
        }
    };

    @Override
    public String toString() {
        return "Flora{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", familia='" + familia + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", altitud='" + altitud + '\'' +
                ", habitat='" + habitat + '\'' +
                ", fitosociologia='" + fitosociologia + '\'' +
                ", biotopo='" + biotipo + '\'' +
                ", biologia_reproductiva='" + biologia_reproductiva + '\'' +
                ", floracion='" + floracion + '\'' +
                ", fructificacion='" + fructificacion + '\'' +
                ", expresion_sexual='" + expresion_sexual + '\'' +
                ", polinizacion='" + polinizacion + '\'' +
                ", dispersion='" + dispersion + '\'' +
                ", numero_cromosomico='" + numero_cromosomatico + '\'' +
                ", reproduccion_asexual='" + reproduccion_asexual + '\'' +
                ", distribucion='" + distribucion + '\'' +
                ", biologia='" + biologia + '\'' +
                ", demografia='" + demografia + '\'' +
                ", amenazas='" + amenazas + '\'' +
                ", medidas_propuestas='" + medidas_propuestas + '\'' +
                '}';
    }

    public Flora(long id, String correo, String nombre, String familia, String identificacion, String altitud,
                 String habitat, String fitosociologia, String biotopo, String biologia_reproductiva,
                 String floracion, String fructificacion, String expresion_sexual, String polinizacion,
                 String dispersion, String numero_cromosomico, String reproduccion_asexual, String distribucion,
                 String biologia, String demografia, String amenazas, String medidas_propuestas) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.familia = familia;
        this.identificacion = identificacion;
        this.altitud = altitud;
        this.habitat = habitat;
        this.fitosociologia = fitosociologia;
        this.biotipo = biotopo;
        this.biologia_reproductiva = biologia_reproductiva;
        this.floracion = floracion;
        this.fructificacion = fructificacion;
        this.expresion_sexual = expresion_sexual;
        this.polinizacion = polinizacion;
        this.dispersion = dispersion;
        this.numero_cromosomatico = numero_cromosomico;
        this.reproduccion_asexual = reproduccion_asexual;
        this.distribucion = distribucion;
        this.biologia = biologia;
        this.demografia = demografia;
        this.amenazas = amenazas;
        this.medidas_propuestas = medidas_propuestas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getFitosociologia() {
        return fitosociologia;
    }

    public void setFitosociologia(String fitosociologia) {
        this.fitosociologia = fitosociologia;
    }

    public String getBiotipo() {
        return biotipo;
    }

    public void setBiotipo(String biotipo) {
        this.biotipo = biotipo;
    }

    public String getBiologia_reproductiva() {
        return biologia_reproductiva;
    }

    public void setBiologia_reproductiva(String biologia_reproductiva) {
        this.biologia_reproductiva = biologia_reproductiva;
    }

    public String getFloracion() {
        return floracion;
    }

    public void setFloracion(String floracion) {
        this.floracion = floracion;
    }

    public String getFructificacion() {
        return fructificacion;
    }

    public void setFructificacion(String fructificacion) {
        this.fructificacion = fructificacion;
    }

    public String getExpresion_sexual() {
        return expresion_sexual;
    }

    public void setExpresion_sexual(String expresion_sexual) {
        this.expresion_sexual = expresion_sexual;
    }

    public String getPolinizacion() {
        return polinizacion;
    }

    public void setPolinizacion(String polinizacion) {
        this.polinizacion = polinizacion;
    }

    public String getDispersion() {
        return dispersion;
    }

    public void setDispersion(String dispersion) {
        this.dispersion = dispersion;
    }

    public String getNumero_cromosomatico() {
        return numero_cromosomatico;
    }

    public void setNumero_cromosomatico(String numero_cromosomatico) {
        this.numero_cromosomatico = numero_cromosomatico;
    }

    public String getReproduccion_asexual() {
        return reproduccion_asexual;
    }

    public void setReproduccion_asexual(String reproduccion_asexual) {
        this.reproduccion_asexual = reproduccion_asexual;
    }

    public String getDistribucion() {
        return distribucion;
    }

    public void setDistribucion(String distribucion) {
        this.distribucion = distribucion;
    }

    public String getBiologia() {
        return biologia;
    }

    public void setBiologia(String biologia) {
        this.biologia = biologia;
    }

    public String getDemografia() {
        return demografia;
    }

    public void setDemografia(String demografia) {
        this.demografia = demografia;
    }

    public String getAmenazas() {
        return amenazas;
    }

    public void setAmenazas(String amenazas) {
        this.amenazas = amenazas;
    }

    public String getMedidas_propuestas() {
        return medidas_propuestas;
    }

    public void setMedidas_propuestas(String medidas_propuestas) {
        this.medidas_propuestas = medidas_propuestas;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(correo);
        parcel.writeString(nombre);
        parcel.writeString(familia);
        parcel.writeString(identificacion);
        parcel.writeString(altitud);
        parcel.writeString(habitat);
        parcel.writeString(fitosociologia);
        parcel.writeString(biotipo);
        parcel.writeString(biologia_reproductiva);
        parcel.writeString(floracion);
        parcel.writeString(fructificacion);
        parcel.writeString(expresion_sexual);
        parcel.writeString(polinizacion);
        parcel.writeString(dispersion);
        parcel.writeString(numero_cromosomatico);
        parcel.writeString(reproduccion_asexual);
        parcel.writeString(distribucion);
        parcel.writeString(biologia);
        parcel.writeString(demografia);
        parcel.writeString(amenazas);
        parcel.writeString(medidas_propuestas);
    }
}
