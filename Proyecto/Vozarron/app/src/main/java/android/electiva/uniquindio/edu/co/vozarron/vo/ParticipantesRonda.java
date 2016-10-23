package android.electiva.uniquindio.edu.co.vozarron.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * En esta clase se encuentra representada la relación muchos a muchos entre Participantes y Ronda.
 */

public class ParticipantesRonda implements Parcelable{

    /**
     * Atributo que representa la dirección url del video con el que un participante partipó en una ronda.
     */
    private String urlVideo;
    /**
     * Atributo que representa el número de votos que tiene un participante en la ronda.
     */
    private int numVotos;

    /**
     * Atributo que representa el participante de la ronda.
     */
    private Participante participante;

    /**
     * Atributo que representa la ronda en donde participa un participante.
     */
    private Ronda ronda;

    /**
     * Metodo constructor para añadir un participante a una ronda.
     * @param urlVideo Url del video con el que el participante participa en la ronda.
     * @param participante participante de la ronda.
     * @param ronda ronda en donde participa un participante.
     */
    public ParticipantesRonda(String urlVideo, Participante participante, Ronda ronda){
        this.urlVideo = urlVideo;
        this.numVotos = 0;
        this.participante = participante;
        this.ronda = ronda;
    }

    /**
     * Metodo para leer los datos y construir la clase desde un parcel.
     * @param in parcel del que se va a leer.
     */
    protected ParticipantesRonda(Parcel in) {
        urlVideo = in.readString();
        numVotos = in.readInt();
        participante = in.readParcelable(Participante.class.getClassLoader());
        ronda = in.readParcelable(Ronda.class.getClassLoader());
    }

    /**
     * Metodo encargado de construir ParticipantesRonda con base al parcel recibido.
     * Tambien es necesario para enviar array para la lectura de arrays enviados por medio del parcel.
     */
    public static final Creator<ParticipantesRonda> CREATOR = new Creator<ParticipantesRonda>() {
        @Override
        public ParticipantesRonda createFromParcel(Parcel in) {
            return new ParticipantesRonda(in);
        }

        @Override
        public ParticipantesRonda[] newArray(int size) {
            return new ParticipantesRonda[size];
        }
    };

    /**
     * Getter de urlVideo.
     * @return url del video con el que el participante participa en la ronda.
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Setter de urlVideo.
     * @param urlVideo url del video con el que el participante participa en la ronda.
     */
    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    /**
     * Getter de numVotos.
     * @return entero con el numero de votos del participante en la ronda.
     */
    public int getNumVotos() {
        return numVotos;
    }

    /**
     * Setter de numVotos.
     * @param numVotos entero con el numero de votos del participante en la ronda.
     */
    public void setNumVotos(int numVotos) {
        this.numVotos = numVotos;
    }

    /**
     * Getter de Participante.
     * @return Participante de una ronda.
     */
    public Participante getParticipante() {
        return participante;
    }

    /**
     * Setter de Participante.
     * @param participante Participante de una ronda.
     */
    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    /**
     * Getter de Ronda.
     * @return ronda de la relación.
     */
    public Ronda getRonda() {
        return ronda;
    }

    /**
     * Setter de Ronda.
     * @param ronda Ronda de la relación.
     */
    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
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
     * Permite escribir un parcel. El orden en que se escribe es importante.
     * @param parcel Parcel donde se va a escribir.
     * @param i indica como deberia ser escrito el parcel.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(urlVideo);
        parcel.writeInt(numVotos);
        parcel.writeParcelable(participante, i);
        parcel.writeParcelable(ronda, i);
    }
}
