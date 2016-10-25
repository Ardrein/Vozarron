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
    private String idParticipante;

    /**
     * Atributo que representa la ronda en donde participa un participante.
     */
    private String idRonda;

    /**
     * Metodo constructor para añadir un participante a una ronda.
     * @param urlVideo Url del video con el que el participante participa en la ronda.
     * @param idParticipante idParticipante de la ronda.
     * @param idRonda idRonda en donde participa un participante.
     */
    public ParticipantesRonda(String urlVideo, String idParticipante, String idRonda){
        this.urlVideo = urlVideo;
        this.numVotos = 0;
        this.idParticipante = idParticipante;
        this.idRonda = idRonda;
    }

    /**
     * Metodo para leer los datos y construir la clase desde un parcel.
     * @param in parcel del que se va a leer.
     */
    protected ParticipantesRonda(Parcel in) {
        urlVideo = in.readString();
        numVotos = in.readInt();
        idParticipante = in.readString();
        idRonda = in.readString();
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
        parcel.writeString(idParticipante);
        parcel.writeString(idRonda);
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
     * Getter de idParticipante.
     * @return idParticipante de una ronda.
     */
    public String getIdParticipante() {
        return idParticipante;
    }

    /**
     * Setter de idParticipante.
     * @param idParticipante Participante de una ronda.
     */
    public void setIdParticipante(String idParticipante) {
        this.idParticipante = idParticipante;
    }

    /**
     * Getter de idRonda.
     * @return idRonda de la relación.
     */
    public String getIdRonda() {
        return idRonda;
    }

    /**
     * Setter de idRonda.
     * @param idRonda idRonda de la relación.
     */
    public void setIdRonda(String idRonda) {
        this.idRonda = idRonda;
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
