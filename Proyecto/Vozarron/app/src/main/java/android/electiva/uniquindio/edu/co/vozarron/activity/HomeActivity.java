package android.electiva.uniquindio.edu.co.vozarron.activity;

import android.content.Intent;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeEntrenadoresFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Ronda;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

/**
 * Actividad principal del Vozarrón.
 * En esta actividad se muestra la lista de entrenadores y las opciones para navegar hacia las votaciones o hacia la lista
 * de todos los participantes.
 */
public class HomeActivity extends AppCompatActivity implements ListaDeEntrenadoresFragment.OnEntrenadorSeleccionadoListener{

    /**
     * ArrayList con la lista de entrenadores.
     */
    private ArrayList<Entrenador> listaEntrenadores;

    /**
     * ArrayList con la lista de rondas.
     */
    private ArrayList<Ronda> rondas;

    /**
     * Constante para identificar el objeto que es pasado a traves del intent a una nueva actividad.
     */
    final static String RONDA_POS = "ronda";

    /**
     * Constante para identificar el objeto que es pasado a traves del intent a una nueva actividad.
     */
    final static String ENTRENADOR_POS = "entrenador";

    /**
     * Constante apra identificar la lista pasada a traves del intent.
     */
    final static String LISTA_ENTRENADORES = "listEntrenadores";

    /**
     * Constante apra identificar la lista pasada a traves del intent.
     */
    final static String LISTA_RONDAS = "listRondas";


    /**
     * Metodo llamado en la creacion de la actividad, utilizado para realizar inicializaciones.
     * @param savedInstanceState Bundle para recibir informacion de estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    /**
     * Override del metodo de la interface para ejecutar el evento que permite mostrar los detalles de un entrenador.
     * Los detalles del entrenador seran mostrados en una nueva actividad.
     * @param pos posición en el array del entrenador seleccionado.
     */
    @Override
    public void onEntrenadorSeleccionado(int pos) {
        Intent intent = new Intent(this,DetalleEntrenadorActivity.class);
        intent.putExtra(ENTRENADOR_POS,listaEntrenadores.get(pos));
        startActivity(intent);
    }

    /**
     * Setter de listaEntrenadores desde la interface.
     * @param listaEntrenadores ArrayList con la lista de los Entrenadores.
     */
    @Override
    public void setEntrenadores(ArrayList<Entrenador> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }

    /**
     * Setter de la lista de rondas de la interface.
     * @param rondas lista de rondas.
     */
    @Override
    public void setRondas(ArrayList<Ronda> rondas) {
        this.rondas = rondas;
    }

    /**
     * Metodo para iniciar la actividad de Participantes.
     */
    public void irAListaParticipantes(View view){
        Intent intent = new Intent(this,ParticipantesActivity.class);
        intent.putExtra(LISTA_ENTRENADORES,listaEntrenadores);
        intent.putExtra(LISTA_RONDAS,rondas);
        startActivity(intent);
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
