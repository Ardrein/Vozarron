package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.activity.ParticipantesActivity;
import android.electiva.uniquindio.edu.co.vozarron.util.AdaptadorDeParticipante;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Fragmento que contiene la lista de participantes.
 */
public class ListaDeParticipantesFragment extends Fragment implements AdaptadorDeParticipante.OnClickAdaptadorDeParticipante{


    /**
     * Instancia del adaptador de participante.
     */
    private AdaptadorDeParticipante adaptador;

    /**
     * Instancia del RecyclerView.
     */
    private RecyclerView listadoDeParticipantes;

    /**
     * Instancia utilizada para comunicarse con la actividad de Participantes.
     */
    private OnParticipanteSeleccionadoListener listener;

    /**
     * Constructor vacio.
     */
    public ListaDeParticipantesFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo que se ejecuta cuando se selecciona un elemento de la lista de participantes para mostrar los detalles
     * del participante.
     * @param pos posicion del participante seleccionado en la lista.
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onParticipanteSeleccionado(pos);
    }

    /**
     * Interface a ser utilizada para la comunicación con la actividad de Participantes.
     */
    public interface OnParticipanteSeleccionadoListener{

        /**
         * Metodo de la interface para visualizar los detalles de un participante seleccionado.
         * @param pos posición en el array del participante seleccionado.
         */
        void onParticipanteSeleccionado(int pos);

        /**
         * Metodo para agregar un participante a la lista del listener.
         * @param participante participante a agregar.
         */
        void addParticipante(Participante participante);

        /**
         * Getter para la lista de participantes.
         * @return ArrayList de Participante.
         */
        ArrayList<Participante> getListaParticipantes();

        /**
         *  Getter para la lista de participantes.
         * @param participantes ArrayList de Participante.
         */
        void setListaParticipantes(ArrayList<Participante> participantes);
    }


    /**
     * Metodo llamado en la creacion del fragmento.
     * @param savedInstanceState Bundle para recibir informacion de estado del fragmento.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }

    /**
     * Metodo ejecutado cuando el fragmento es asociado a una actividad.
     * @param context contexto en el cual es asociado.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                listener = (OnParticipanteSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " debe implementar la interfaz OnParticipanteSeleccionadoListener");
            }
        }
    }


    /**
     * Metodo llamado cuando el metodo onCreate de la actividad finaliza su ejecucion.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        listadoDeParticipantes = (RecyclerView) getView().findViewById(R.id.lista_participantes);
        adaptador =  new AdaptadorDeParticipante(listener.getListaParticipantes(),this);
        listadoDeParticipantes.setAdapter(adaptador);
        listadoDeParticipantes.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_de_participantes, container, false);
    }



    /**
     * Getter de participantes.
     * @return lista de participantes.
     */
    public ArrayList<Participante> getParticipantes() {
        return listener.getListaParticipantes();
    }

    /**
     * Setter de participantes.
     * @param participantes lista de participantes.
     */
    public void setParticipantes(ArrayList<Participante> participantes) {
        listener.setListaParticipantes(participantes);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_agregar){
            ((ParticipantesActivity) getActivity()).mostrarDialogoAgregarParticipante(ListaDeParticipantesFragment.class
            .getName());
        }

            return super.onOptionsItemSelected(item);
    }


    /**
     * Metodo para agregar un participante a la lista de participantes.
     * @param participante a agregar.
     */
    public void agregarParticipante(Participante participante) {
        listener.addParticipante(participante);
        adaptador.notifyItemInserted(listener.getListaParticipantes().size()-1);
        Toast.makeText(getContext(),R.string.agregar_exitoso,Toast.LENGTH_SHORT).show();
    }
}
