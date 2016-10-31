package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.util.AdaptadorDeVotacion;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Fragmento que contiene la lista de participantes validos en la votacion.
 */
public class VotacionesFragment extends Fragment implements AdaptadorDeVotacion.OnClickAdaptadorDeVotacion{

    /**
     * Instancia del adaptador de participante.
     */
    private AdaptadorDeVotacion adaptador;

    /**
     * Instancia del RecyclerView.
     */
    private RecyclerView listadoDeParticipantes;

    /**
     * ArrayList con la lista de participantes.
     */
    private ArrayList<Participante> participantes;

    /**
     * Instancia utilizada para comunicarse con la actividad de Participantes.
     */
    private OnVotacionSeleccionadoListener listener;


    /**
     * Constructor vacio.
     */
    public VotacionesFragment() {
        // Required empty public constructor
    }

    /**
     * Interface a ser utilizada para la comunicación con la actividad de Votaciones.
     */
    public interface OnVotacionSeleccionadoListener{
        /**
         * Metodo de la interface para proceder a votar sobre un participante seleccionado.
         * @param pos posición en el array del participante seleccionado.
         */
        void onParticipanteSeleccionado(int pos);

        /**
         * Getter para la lista de participantes.
         * @return ArrayList de Participante.
         */
        ArrayList<Participante> getListaParticipantes();

    }

    /**
     * Metodo que se ejecuta cuando se selecciona un elemento de la lista de participantes para proceder a votar.
     * @param pos posicion del participante seleccionado en la lista.
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onParticipanteSeleccionado(pos);
    }

    /**
     * Metodo llamado en la creacion del fragmento.
     * @param savedInstanceState Bundle para recibir informacion de estado del fragmento.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                listener = (OnVotacionSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " debe implementar la interfaz OnVotacionSeleccionadoListener");
            }
        }
    }


    /**
     * Metodo llamado cuando el metodo onCreate de la actividad finaliza su ejecucion.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setParticipantes(listener.getListaParticipantes());

        listadoDeParticipantes = (RecyclerView) getView().findViewById(R.id.lista_participantes_votacion);
        adaptador =  new AdaptadorDeVotacion(participantes,this);
        listadoDeParticipantes.setAdapter(adaptador);
        listadoDeParticipantes.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_votaciones, container, false);
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
