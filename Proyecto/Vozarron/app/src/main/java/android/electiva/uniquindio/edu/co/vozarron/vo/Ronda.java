package android.electiva.uniquindio.edu.co.vozarron.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Clase que representa una ronda en el vozarrón.
 *
 */

public class Ronda implements Parcelable{

    /**
     * Atributo para representar el id de la ronda.
     */
    private String id;

    /**
     * Atributo que representa el número de la ronda, si es la semifinal o la final.
     */
    private String nombre;

    /**
     * Atributo que representa la lista de participantes que hay en la ronda.
     */
    private ArrayList<ParticipantesRonda> participantesRondas;

    /**
     * Constructor de la clase Ronda.
     * @param nombre Atributo que representa el número de la ronda, si es la semifinal o la final.
     */
    public Ronda(String nombre){
        this.nombre = nombre;
        this.participantesRondas = new ArrayList<>();
    }

    /**
     * Metodo para leer los datos y construir la clase desde un parcel.
     * @param in parcel del que se va a leer.
     */
    protected Ronda(Parcel in) {
        id = in.readString();
        nombre = in.readString();
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

    }

    /**
     * Metodo encargado de construir la Ronda con base al parcel recibido.
     * Tambien es necesario para enviar array para la lectura de arrays enviados por medio del parcel.
     */
    public static final Creator<Ronda> CREATOR = new Creator<Ronda>() {
        @Override
        public Ronda createFromParcel(Parcel in) {
            return new Ronda(in);
        }

        @Override
        public Ronda[] newArray(int size) {
            return new Ronda[size];
        }
    };

    /**
     * Getter del id.
     * @return String con el id de la ronda.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter del id.
     * @param id String con el id de la ronda.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter del nombre de la ronda.
     * @return String con el nombre de la ronda.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre de la ronda.
     * @param nombre nombre a dar a la ronda.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de ParticipantesRonda.
     * @return ArrayList con la lista de los participantes de la ronda.
     */
    public ArrayList<ParticipantesRonda> getParticipantesRondas() {
        return participantesRondas;
    }

    /**
     * Setter de ParticipantesRonda.
     * @param participantesRondas ArrayList con la lista de los participantes de la ronda.
     */
    public void setParticipantesRondas(ArrayList<ParticipantesRonda> participantesRondas) {
        this.participantesRondas = participantesRondas;
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
