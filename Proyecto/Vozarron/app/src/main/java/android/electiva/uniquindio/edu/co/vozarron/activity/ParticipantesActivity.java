package android.electiva.uniquindio.edu.co.vozarron.activity;

import android.content.Intent;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeParticipantesFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Actividad que contiene la lista de los participantes del Vozarrón.
 */
public class ParticipantesActivity extends AppCompatActivity implements ListaDeParticipantesFragment.OnParticipanteSeleccionadoListener{

    /**
     * Lista de los participantes.
     */
    private ArrayList<Participante> participantes;

    /**
     * Constante para identificar el objeto que es pasado a traves del intent a una nueva actividad.
     */
    final static String PARTICIPANTE_POS = "participante";

    /**
     * Metodo llamado en la creacion de la actividad, utilizado para realizar inicializaciones.
     * @param savedInstanceState Bundle para recibir informacion de estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);
    }


    /**
     * Override del metodo de la interface para ejecutar el evento que permite mostrar los detalles de un participante.
     * Los detalles del participante seran mostrados en una nueva actividad.
     * @param pos posición en el array del participante seleccionado.
     */
    @Override
    public void onParticipanteSeleccionado(int pos) {
        Intent intent = new Intent(this,ParticipantesActivity.class);
        intent.putExtra(PARTICIPANTE_POS,participantes.get(pos));
        startActivity(intent);

    }

    /**
     * Setter de la lista de participantes desde la interface.
     * @param participantes ArrayList con la lista de los Participantes.
     */
    @Override
    public void setListaParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;

    }

    /**
     * Getter de participantes.
     * @return lista de participantes.
     */
    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    /**
     * Setter de participantes.
     * @param participantes lista de participantes.
     */
    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }


}
