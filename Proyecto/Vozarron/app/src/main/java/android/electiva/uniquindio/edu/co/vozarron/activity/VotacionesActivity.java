package android.electiva.uniquindio.edu.co.vozarron.activity;

import android.content.Intent;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.VotacionesFragment;
import android.electiva.uniquindio.edu.co.vozarron.fragments.VotarDialogFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Actividad que contiene la lista de los participantes para votar del Vozarrón.
 */
public class VotacionesActivity extends AppCompatActivity implements VotacionesFragment.OnVotacionSeleccionadoListener, VotarDialogFragment.OnVotarListener {

    /**
     * Lista de los participantes.
     */
    private ArrayList<Participante> participantes;

    /**
     * Posicion del elemento seleccionado en la lista.
     */
    private int posSeleccion;

    /**
     * Lista de los entrenadores.
     */
    private ArrayList<Entrenador> listaEntrenadores;

    /**
     * Constante para identificar el objeto que es pasado a traves del intent a una nueva actividad.
     */
    final static String VOTACION_POS = "votacion";


    /**
     * Metodo llamado en la creacion de la actividad, utilizado para realizar inicializaciones.
     * @param savedInstanceState Bundle para recibir informacion de estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);
        posSeleccion = -1;


        participantes = new ArrayList<>();
        listaEntrenadores = (ArrayList<Entrenador>) getIntent().getExtras().get(HomeActivity.LISTA_ENTRENADORES);

        for (Entrenador entrenador: listaEntrenadores) {
            for(Participante participante: entrenador.getListaParticipantes()){
                if(participante.isEstado()) {
                    this.participantes.add(participante);
                }
            }
        }
    }

    /**
     * Override del metodo de la interface para ejecutar el evento que permite votar.
     * @param pos posición en el array del participante seleccionado.
     */
    @Override
    public void onParticipanteSeleccionado(int pos) {
        posSeleccion = pos;
        if(posSeleccion!= -1){
            findViewById(R.id.votar_button).setEnabled(true);
        }else{
            findViewById(R.id.votar_button).setEnabled(false);
        }
    }

    /**
     * Metodo ejecutado cuando se acepta el mensaje de votacion.
     */
    @Override
    public void votarPor() {
        Participante participante = participantes.get(posSeleccion);
        Toast.makeText(getBaseContext(),R.string.votacion_ok,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra(VOTACION_POS,participante);
        setResult(RESULT_OK,intent);
        finish();

    }

    /**
     * Metodo ejecutado cuando se presiona sobre el boton votar.
     */
    public void votar(View view){
        VotarDialogFragment dialogVotar = new VotarDialogFragment();
        dialogVotar.setStyle(dialogVotar.STYLE_NORMAL,R.style.DialogoTitulo);
        dialogVotar.show(getSupportFragmentManager(),VOTACION_POS);
    }





    /**
     * Getter para la lista de participantes.
     * @return ArrayList de Participante.
     */
    @Override
    public ArrayList<Participante> getListaParticipantes() {
        return participantes;
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

    /**
     * Getter de listaEntrenadores.
     * @return ArrayList con la lista de los Entrenadores.
     */
    public ArrayList<Entrenador> getListaEntrenadores() {
        return listaEntrenadores;
    }

    /**
     * Setter de listaEntrenadores.
     * @param listaEntrenadores ArrayList con la lista de los Entrenadores.
     */
    public void setListaEntrenadores(ArrayList<Entrenador> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }
}
