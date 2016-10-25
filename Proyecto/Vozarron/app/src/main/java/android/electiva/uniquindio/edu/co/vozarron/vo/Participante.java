package android.electiva.uniquindio.edu.co.vozarron.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Clase que representa un participante del vozarrón.
 * Implementa la interface Parcelable para que el objeto pueda ser enviado entre actividades.
 */

public class Participante implements Parcelable{

    /**
     * Atributo para representar el id del participante.
     */
    private String id;
    /**
     * Atributo para representar el nombre completo del participante.
     */
    private String nombre;
    /**
     * Atributo para representar la edad del participante.
     */
    private int edad;
    /**
     * Atriuto para representar si el representante se encuentra activo en el vozarrón(true) o si ha sido eliminado(false).
     */
    private boolean estado;

    /**
     * Atributo para representar la relación del participante con la universidad.
     * Puede tomar los valores de:
     * Estudiante.
     * Administrativo.
     * Docente.
     * Otro.
     */
    private String relacionUniversidad;

    /**
     * Atributo que representa el nombre de la imagen asociada al participante.
     */
    private String foto;

    /**
     * Atributo que representa el entrenador de este participante.
     */
    private String idEntrenador;

    /**
     * Atributo que representa la lista de rondas en las que ha participado el participante.
     */
    private ArrayList<ParticipantesRonda> participantesRondas;

    /**
     * Constructor de la clase Participante.
     * @param nombre nombre del participante.
     * @param edad edad del participante.
     * @param relacionUniversidad relación del participante con la universidad: Estudiante, Administrativo, Docente, Otro.
     * @param foto string que representa el nombre de la imagen asociada al participante.
     */
    public Participante(String nombre, int edad, String relacionUniversidad, String foto){
        this.nombre = nombre;
        this.edad = edad;
        this.relacionUniversidad = relacionUniversidad;
        this.foto = foto;
        this.estado = true;
        this.participantesRondas = new ArrayList<>();
    }

    /**
     * Metodo para leer los datos y construir la clase desde un parcel.
     * @param in parcel del que se va a leer.
     */
    protected Participante(Parcel in) {


        id = in.readString();
        nombre = in.readString();
        edad = in.readInt();
        estado = in.readByte() != 0;
        relacionUniversidad = in.readString();
        foto = in.readString();
        idEntrenador = in.readString();
        participantesRondas = in.createTypedArrayList(ParticipantesRonda.CREATOR);
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
        parcel.writeInt(edad);
        parcel.writeByte((byte) (estado ? 1 : 0));
        parcel.writeString(relacionUniversidad);
        parcel.writeString(foto);
        parcel.writeString(idEntrenador);
        parcel.writeTypedList(participantesRondas);
    }


    /**
     * Metodo encargado de construir el Participante con base al parcel recibido.
     * Tambien es necesario para enviar array para la lectura de arrays enviados por medio del parcel.
     */
    public static final Creator<Participante> CREATOR = new Creator<Participante>() {
        @Override
        public Participante createFromParcel(Parcel in) {
            return new Participante(in);
        }

        @Override
        public Participante[] newArray(int size) {
            return new Participante[size];
        }
    };

    /**
     * Metodo para obtener el nombre del estado del participante dependiendo si ha sido eliminado o no basado en el boolean estado.
     * @return String con el nombre del estado del participante.
     */
    public String getEstado(){
        return (this.estado)?"ACTIVO":"ELIMINADO";
    }

    /**
     * Getter del id.
     * @return String con el id del participante.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter del id.
     * @param id String con el id del participante.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter de nombre;
     * @return String con el nombre del participante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter de nombre;
     * @param nombre String con el nombre del participante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de edad.
     * @return entero con la edad del participante.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Setter de edad.
     * @param edad entero con la edad del participante.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Getter de estado.
     * @return boolean con el estado del participante.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Setter de estado.
     * @param estado boolean con el estado del participante.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Getter de relacionUniversidad.
     * @return String con la relacion con la universidad del participante.
     */
    public String getRelacionUniversidad() {
        return relacionUniversidad;
    }

    /**
     * Setter de relacionUniversidad.
     * @param relacionUniversidad String con la relacion con la universidad del participante.
     */
    public void setRelacionUniversidad(String relacionUniversidad) {
        this.relacionUniversidad = relacionUniversidad;
    }

    /**
     * Getter de foto.
     * @return nombre de la imagen asociada al participante.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Setter de foto.
     * @param foto  nombre de la imagen asociada al participante.
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Getter de participantesRondas.
     * @return ArrayList con la lista de rondas del participante.
     */
    public ArrayList<ParticipantesRonda> getParticipantesRondas() {
        return participantesRondas;
    }

    /**
     * Setter de participantesRondas.
     * @param participantesRondas ArrayList con la lista de rondas del participante.
     */
    public void setParticipantesRondas(ArrayList<ParticipantesRonda> participantesRondas) {
        this.participantesRondas = participantesRondas;
    }

    /**
     * Getter de idEntrenador.
     * @return idEntrenador del participante.
     */
    public String getIdEntrenador() {
        return idEntrenador;
    }

    /**
     * Setter de idEntrenador.
     * @param idEntrenador idEntrenador del participante.
     */
    public void setIdEntrenador(String idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    /**
     * Se utiliza cuando existen parcelables hijos.
     * @return retorna cero al no tener hijos.
     */
    @Override
    public int describeContents() {
        return 0;
    }



}
