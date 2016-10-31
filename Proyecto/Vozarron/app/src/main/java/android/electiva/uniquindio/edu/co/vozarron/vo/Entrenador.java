package android.electiva.uniquindio.edu.co.vozarron.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Clase que representa un entrenador del vozarrón.
 * Implementa la interface Parcelable para que el objeto pueda ser enviado entre actividades.
 */

public class Entrenador implements Parcelable{

    /**
     * Atributo para representar el id del entrenador.
     */
    private String id;

    /**
     * Atributo que representa el nombre del entrenador.
     */
    private String nombre;

    /**
     * Atributo que representa una breve reseña del entrenador.
     */
    private String historia;

    /**
     * Atributo que representa el genero del entrenador. F o M.
     */
    private String genero;

    /**
     * Atributo que representa la imagen asociada al entrenador.
     */
    private int foto;

    /**
     * Atributo de ArrayList con la lista de participantes bajo este entrenador
     */

    private ArrayList<Participante> listaParticipantes;


    /**
     * Construtor de la clase.
     * @param nombre nombre del entrenador.
     * @param historia reseña corta del entrenador.
     * @param genero genero del entrenador Femenino o Masculino.
     * @param foto imagen asociada al entrenador.
     */
    public Entrenador(String nombre, String historia, String genero, int foto) {
        this.nombre = nombre;
        this.historia = historia;
        this.genero = genero;
        this.foto = foto;
        this.listaParticipantes = new ArrayList<>();
    }

    /**
     * Metodo para leer los datos y construir la clase desde un parcel.
     * @param in parcel del que se va a leer.
     */
    protected Entrenador(Parcel in) {
        id= in.readString();
        nombre = in.readString();
        historia = in.readString();
        foto = in.readInt();
        genero = in.readString();
        listaParticipantes = in.createTypedArrayList(Participante.CREATOR);

    }

    /**
     * Permite escribir un parcel. El orden en que se escribe es importante.
     * @param parcel Parcel donde se va a escribir.
     * @param i indica como deberia ser escrito el parcel.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nombre);
        parcel.writeString(historia);
        parcel.writeInt(foto);
        parcel.writeString(genero);
        parcel.writeTypedList(listaParticipantes);
    }

    /**
     * Metodo encargado de construir el Entrenador con base al parcel recibido.
     * Tambien es necesario para enviar array para la lectura de arrays enviados por medio del parcel.
     */
    public static final Creator<Entrenador> CREATOR = new Creator<Entrenador>() {
        @Override
        public Entrenador createFromParcel(Parcel in) {
            return new Entrenador(in);
        }

        @Override
        public Entrenador[] newArray(int size) {
            return new Entrenador[size];
        }
    };


    /**
     * Getter del id.
     * @return String con el id del entrenador.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter del id.
     * @param id String con el id del entrenador.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter de nombre.
     * @return nombre del entrenador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter de nombre.
     * @param nombre nombre del entrenador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de historia.
     * @return breve historia del entrenador.
     */
    public String getHistoria() {
        return historia;
    }

    /**
     * Setter de historia.
     * @param historia  breve historia del entrenador.
     */
    public void setHistoria(String historia) {
        this.historia = historia;
    }

    /**
     * Getter de genero.
     * @return cadena Femenino o Masculino que representa el genero del entrenador.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Setter de genero.
     * @param genero cadena Femenino o Masculino que representa el genero del entrenador.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Getter de foto.
     * @return imagen asociada al entrenador.
     */
    public int getFoto() {
        return foto;
    }

    /**
     * Setter de foto.
     * @param foto imagen asociada al entrenador.
     */
    public void setFoto(int foto) {
        this.foto = foto;
    }

    /**
     * Getter de listaParticipante.
     * @return ArrayList con la lista de participantes bajo este entrenador.
     */
    public ArrayList<Participante> getListaParticipantes() {
        return listaParticipantes;
    }

    /**
     * Setter de listaParticipantes.
     * @param listaParticipantes ArrayList con la lista de participantes bajo este entrenador.
     */
    public void setListaParticipantes(ArrayList<Participante> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    /**
     * Se utiliza cuando existen parcelables hijos.
     * @return retorna cero al no tener hijos.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Metodo toString para utilizar la clase dentro de un spinner.
     * @return String con el nNombre del entrenador.
     */
    public String toString(){
        return nombre;
    }


}
