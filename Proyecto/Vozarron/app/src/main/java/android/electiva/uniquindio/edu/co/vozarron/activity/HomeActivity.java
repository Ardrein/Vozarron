package android.electiva.uniquindio.edu.co.vozarron.activity;

import android.content.Intent;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.DetalleDeEntrenadorFragment;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeEntrenadoresFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
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

        boolean esFragmento = getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_entrenador)!= null;
        if(esFragmento && !listaEntrenadores.isEmpty()){
            Entrenador entrenador = listaEntrenadores.get(0);

            mostrarEntrenador(entrenador);
        }


    }

    /**
     * Metodo ejecutado cuando se inicia una actividad desde esta actividad y se espera obtener un resultado.
     * @param requestCode codigo de peticion cuando se inicio otra actividad desde esta.
     * @param resultCode codigo de estado de la transaccion.
     * @param data datos obtenidos en la transaccion.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2){
            if(resultCode == RESULT_OK){

                Participante participante = (Participante) data.getParcelableExtra(VotacionesActivity.VOTACION_POS);
                actualizarParticipante(participante);

            }
        }
    }

    /**
     * Override del metodo de la interface para ejecutar el evento que permite mostrar los detalles de un entrenador.
     * Los detalles del entrenador seran mostrados en una nueva actividad.
     * @param pos posición en el array del entrenador seleccionado.
     */
    @Override
    public void onEntrenadorSeleccionado(int pos) {
        boolean esFragmento = getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_entrenador)!= null;
        if(esFragmento){
            mostrarEntrenador(listaEntrenadores.get(pos));
        }else{
            Intent intent = new Intent(this,DetalleEntrenadorActivity.class);
            intent.putExtra(ENTRENADOR_POS,listaEntrenadores.get(pos));
            startActivity(intent);
        }

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
     * Metodo para iniciar la actividad de Votaciones.
     */
    public void irAVotaciones(View view){
        Intent intent = new Intent(this,VotacionesActivity.class);
        intent.putExtra(LISTA_ENTRENADORES,listaEntrenadores);
        startActivityForResult(intent,2);
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

    /**
     * Metodo para actualizar un participante de la lista de participantes.
     * @param participante
     */
    public void actualizarParticipante(Participante participante){
        String idPart = participante.getId();
        String idEntrenador = participante.getIdEntrenador();
        for(Entrenador entrenador: listaEntrenadores){
            if(entrenador.getId().equals(idEntrenador)){

                for(Participante partic: entrenador.getListaParticipantes()){
                    if(partic.getId().equals(idPart)){

                        partic.setVotos();
                        break;
                    }
                }

                break;
            }
        }
    }

    /**
     * Metodo para enviar un entrenador del cual se quieren ver sus detalles al fragmento de detalle de entrenador.
     * @param entrenador entrenador a mostrar los detalles.
     */
    public void mostrarEntrenador(Entrenador entrenador){
        ((DetalleDeEntrenadorFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_entrenador))
                .mostrarEntrenador(entrenador);
    }

}
