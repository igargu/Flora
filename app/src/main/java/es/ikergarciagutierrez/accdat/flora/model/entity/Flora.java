package es.ikergarciagutierrez.accdat.flora.model.entity;

public class Flora {

    private long id;
    private String nombre, familia, identificacion, altitud, habitat, fitosociologia, biotopo,
            biologia_reproductiva, floracion, fructificacion, expresion_sexual, polinizacion,
            dispersion, numero_cromosomico, reproduccion_asexual, distribucion, biologia,
            demografia, amenazas, medidas_propuestas;

    public Flora() {
    }

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
                ", biotopo='" + biotopo + '\'' +
                ", biologia_reproductiva='" + biologia_reproductiva + '\'' +
                ", floracion='" + floracion + '\'' +
                ", fructificacion='" + fructificacion + '\'' +
                ", expresion_sexual='" + expresion_sexual + '\'' +
                ", polinizacion='" + polinizacion + '\'' +
                ", dispersion='" + dispersion + '\'' +
                ", numero_cromosomico='" + numero_cromosomico + '\'' +
                ", reproduccion_asexual='" + reproduccion_asexual + '\'' +
                ", distribucion='" + distribucion + '\'' +
                ", biologia='" + biologia + '\'' +
                ", demografia='" + demografia + '\'' +
                ", amenazas='" + amenazas + '\'' +
                ", medidas_propuestas='" + medidas_propuestas + '\'' +
                '}';
    }

    public Flora(long id, String nombre, String familia, String identificacion, String altitud,
                 String habitat, String fitosociologia, String biotopo, String biologia_reproductiva,
                 String floracion, String fructificacion, String expresion_sexual, String polinizacion,
                 String dispersion, String numero_cromosomico, String reproduccion_asexual, String distribucion,
                 String biologia, String demografia, String amenazas, String medidas_propuestas) {
        this.id = id;
        this.nombre = nombre;
        this.familia = familia;
        this.identificacion = identificacion;
        this.altitud = altitud;
        this.habitat = habitat;
        this.fitosociologia = fitosociologia;
        this.biotopo = biotopo;
        this.biologia_reproductiva = biologia_reproductiva;
        this.floracion = floracion;
        this.fructificacion = fructificacion;
        this.expresion_sexual = expresion_sexual;
        this.polinizacion = polinizacion;
        this.dispersion = dispersion;
        this.numero_cromosomico = numero_cromosomico;
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

    public String getBiotopo() {
        return biotopo;
    }

    public void setBiotopo(String biotopo) {
        this.biotopo = biotopo;
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

    public String getNumero_cromosomico() {
        return numero_cromosomico;
    }

    public void setNumero_cromosomico(String numero_cromosomico) {
        this.numero_cromosomico = numero_cromosomico;
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
}
