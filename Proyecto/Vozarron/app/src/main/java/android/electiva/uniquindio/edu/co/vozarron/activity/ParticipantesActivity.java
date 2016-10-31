package android.electiva.uniquindio.edu.co.vozarron.activity;

import android.content.Intent;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.AgregarParticipanteFragment;
import android.electiva.uniquindio.edu.co.vozarron.fragments.DetalleDeParticipanteFragment;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeParticipantesFragment;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeParticipantesRondaFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.electiva.uniquindio.edu.co.vozarron.vo.Ronda;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Actividad que contiene la lista de los participantes del Vozarrón.
 */
public class ParticipantesActivity extends AppCompatActivity implements ListaDeParticipantesFragment.OnParticipanteSeleccionadoListener,
        AgregarParticipanteFragment.OnAgregarListener, ListaDeParticipantesRondaFragment.OnPartRSelecListener {

    /**
     * Lista de los participantes.
     */
    private ArrayList<Participante> participantes;

    /**
     * Lista de los entrenadores.
     */
    private ArrayList<Entrenador> listaEntrenadores;

    /**
     * Lista de las rondas.
     */
    private ArrayList<Ronda> listaRondas;

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

        participantes = new ArrayList<>();

        listaRondas = (ArrayList<Ronda>) getIntent().getExtras().get(HomeActivity.LISTA_RONDAS);
        listaEntrenadores = (ArrayList<Entrenador>) getIntent().getExtras().get(HomeActivity.LISTA_ENTRENADORES);

        for (Entrenador entrenador: listaEntrenadores) {
            for(Participante participante: entrenador.getListaParticipantes()){
                this.participantes.add(participante);
            }
        }


        //verificamos si se hace uso del fragmento de detalle de participante
        //en caso de ser asi, implica que es un dispositivo grande y mostraremos entonces
        // en el fragmento los detalles del primer participante de la lista.
        boolean esFragmento = getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_participante)!= null;
        if(esFragmento && !participantes.isEmpty()){
            Participante participante = participantes.get(0);
            Entrenador entrenador = findEntrenadorById(participante.getIdEntrenador());

            mostrarParticipante(participante,entrenador);
        }



    }


    /**
     * Override del metodo de la interface para ejecutar el evento que permite mostrar los detalles de un participante.
     * Los detalles del participante seran mostrados en una nueva actividad.
     * @param pos posición en el array del participante seleccionado.
     */
    @Override
    public void onParticipanteSeleccionado(int pos) {
        boolean esFragmento = getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_participante)!= null;
        Participante participante = participantes.get(pos);
        Entrenador entrenador = findEntrenadorById(participante.getIdEntrenador());
        if(esFragmento){

            mostrarParticipante(participante,entrenador);
            ListaDeParticipantesRondaFragment participantesRondaFragment =
                    (ListaDeParticipantesRondaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_lista_participantes_ronda);


            participantesRondaFragment.notificarCambioLista();
        }else{
            Intent intent = new Intent(this,DetalleParticipanteActivity.class);

            intent.putExtra(PARTICIPANTE_POS,participante);
            intent.putExtra(HomeActivity.ENTRENADOR_POS,entrenador);
            intent.putExtra(HomeActivity.LISTA_RONDAS,listaRondas);
            startActivity(intent);
        }


    }



    /**
     * Getter para la lista de participantes desde la interface.
     * @return ArrayList de Participante.
     */
    @Override
    public ArrayList<Participante> getListaParticipantes() {
        return participantes;
    }


    /**
     * Setter de participantes desde la interface.
     * @param participantes lista de participantes.
     */
    @Override
    public void setListaParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

    /**
     * Metodo para agregar un participante a la lista de participantes.
     * @param participante a agregar.
     */
    @Override
    public void addParticipante(Participante participante) {
        participantes.add(participante);
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

    /**
     * Metodo para obtener un entrenador a partir de su id.
     * @param id String con el id del entrenador.
     * @return Entrenador al que pertenece el id. Null en caso de que no haya coincidencia.
     */
    public Entrenador findEntrenadorById(String id){
        for(Entrenador entrenador: listaEntrenadores){
            if(entrenador.getId().equals(id)){
                return entrenador;
            }
        }

        return null;
    }


    /**
     * Metodo utilizado para mostrar el fragmento de dialogo.
     */
    public void mostrarDialogoAgregarParticipante(String nombreClase){
        AgregarParticipanteFragment dialogoAgregarParticipante = new AgregarParticipanteFragment();
        dialogoAgregarParticipante.setStyle(dialogoAgregarParticipante.STYLE_NORMAL,R.style.DialogoTitulo);
        dialogoAgregarParticipante.show(getSupportFragmentManager(),nombreClase);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    /**
     * Metodo para obtener la lista de entrenadores de la actividad.
     * @return ArrayList con la lista de los entrenadores.
     */
    @Override
    public ArrayList<Entrenador> getEntrenadores() {
        return listaEntrenadores;
    }


    /**
     * Metodo para agregar un participante a la lista de participantes.
     * @param participante a agregar.
     */
    @Override
    public void agregarParticipante(Participante participante) {
        ((ListaDeParticipantesFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmento_lista_participantes)).agregarParticipante(participante);
    }


    /**
     * Metodo para enviar un participante del cual se quieren ver sus detalles al fragmento de detalle de participante.
     * @param participante participante a detallar.
     * @param entrenador entrenador del participante.
     */
    public void mostrarParticipante(Participante participante, Entrenador entrenador){
        ((DetalleDeParticipanteFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_participante))
                .mostrarParticipante(participante, entrenador);

        ListaDeParticipantesRondaFragment participantesRondaFragment =
                (ListaDeParticipantesRondaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_lista_participantes_ronda);

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
}
