package android.electiva.uniquindio.edu.co.vozarron.activity;

import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.DetalleDeParticipanteFragment;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeParticipantesRondaFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.electiva.uniquindio.edu.co.vozarron.vo.Ronda;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Actividad utilizada para mostrar los detalles de un Participante.
 */
public class DetalleParticipanteActivity extends AppCompatActivity implements ListaDeParticipantesRondaFragment.OnPartRSelecListener {

    /**
     * ArrayList con la lista de rondas.
     */
    private ArrayList<Ronda> listaRondas;

    /**
     * Metodo llamado en la creacion de la actividad, utilizado para realizar inicializaciones.
     * @param savedInstanceState Bundle para recibir informacion de estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_participante);
        listaRondas = (ArrayList<Ronda>) getIntent().getExtras().get(HomeActivity.LISTA_RONDAS);

        DetalleDeParticipanteFragment detalleParticipante =
                (DetalleDeParticipanteFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_participante);

        ListaDeParticipantesRondaFragment participantesRondaFragment =
                (ListaDeParticipantesRondaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_lista_participantes_ronda);

        Participante participante = (Participante) getIntent().getExtras().get(ParticipantesActivity.PARTICIPANTE_POS);
        Entrenador entrenador = (Entrenador) getIntent().getExtras().get(HomeActivity.ENTRENADOR_POS);



        detalleParticipante.mostrarParticipante(participante, entrenador);

        participantesRondaFragment.setListaParticipantesRonda(participante.getParticipantesRondas());


    }
    /**
     * Metodo para obtener una ronda a partir de su id.
     * @param id String con el id de la ronda.
     * @return Ronda a la que pertenece el id. Null en caso de que no haya coincidencia.
     */
    @Override
    public Ronda findRondaById(String id){
        for(Ronda ronda: listaRondas){
            if(ronda.getId().equals(id)){
                return ronda;
            }
        }

        return null;
    }

    /**
     * Getter de la lista de Rondas.
     * @return lista de Rondas.
     */
    public ArrayList<Ronda> getListaRondas() {
        return listaRondas;
    }

    /**
     * Setter de la lista de Rondas.
     * @param listaRondas lista de Rondas.
     */
    public void setListaRondas(ArrayList<Ronda> listaRondas) {
        this.listaRondas = listaRondas;
    }
}
