package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.util.AdaptadorDeParticipantesRonda;
import android.electiva.uniquindio.edu.co.vozarron.vo.ParticipantesRonda;
import android.electiva.uniquindio.edu.co.vozarron.vo.Ronda;
import android.net.Uri;
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
 * Fragmento que contiene la lista de ParticipantesRonda.
 */
public class ListaDeParticipantesRondaFragment extends Fragment implements AdaptadorDeParticipantesRonda.OnClickAdaptadorDeParticipantesRonda, View.OnClickListener{




    /**
     * Instancia del adaptador de participante.
     */
    private AdaptadorDeParticipantesRonda adaptador;

    /**
     * Instancia del RecyclerView.
     */
    private RecyclerView listadoDeParticipantesRonda;

    /**
     * ArrayList con la lista de participantesRonda.
     */
    private ArrayList<ParticipantesRonda> listaParticipantesRonda;

    /**
     * Instancia de participantesRonda para obtener sus datos.
     */
    private ParticipantesRonda participantesRonda;

    /**
     * Instancia utilizada para comunicarse con la actividad de detalle participante.
     */
    private OnPartRSelecListener listener;

    /**
     * Constructor vacío.
     */
    public ListaDeParticipantesRondaFragment() {
        // Required empty public constructor
    }

    /**
     * Interface a ser utilizada para la comunicación con la actividad de detalle participante.
     */
    public interface OnPartRSelecListener {

        /**
         * Metodo para obtener una ronda segun su id.
         * @param id String con el id de la ronda.
         * @return Ronda a la que pertenece el id.
         */
        Ronda findRondaById(String id);
    }


    /**
     * Metodo para obtener una instancia de onClickListener. En este caso se busca el fragmento
     * para asociar al botonUrl.
     * @return instancia de onClickListener para asociar al botonUrl.
     */
    @Override
    public View.OnClickListener getOnClickListener() {
        return this;
    }

    /**
     * Metodo que se ejecuta cuando se selecciona un boton de un elemento de la lista de participantesRonda
     * para asociar el participante en la ronda a la instancia del mismo.
     * @param pos posicion del participantesRonda seleccionado en la lista.
     */
    @Override
    public void onClickPosition(int pos) {
        this.participantesRonda = listaParticipantesRonda.get(pos);
    }

    /**
     * Metodo para obtener una ronda segun su id.
     * @param id String con el id de la ronda.
     * @return Ronda a la que pertenece el id.
     */
    @Override
    public Ronda findRondaById(String id) {


        return listener.findRondaById(id);
    }

    /**
     * Metodo llamado en la creacion del fragmento.
     * @param savedInstanceState Bundle para recibir informacion de estado del fragmento.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaParticipantesRonda =  new ArrayList<>();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity){
            activity = (Activity) context;

            try {
                listener = (OnPartRSelecListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " debe implementar la interfaz OnPartRSelecListener");
            }
        }
    }

    /**
     * Metodo llamado cuando el metodo onCreate de la actividad finaliza su ejecucion.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoDeParticipantesRonda = (RecyclerView) getView().findViewById(R.id.lista_participantes_ronda);
        adaptador = new AdaptadorDeParticipantesRonda(listaParticipantesRonda,this);
        listadoDeParticipantesRonda.setAdapter(adaptador);
        listadoDeParticipantesRonda.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_de_participantes_ronda, container, false);
    }



    /**
     * Getter de la lista de ParticipantesRonda.
     * @return lista de ParticipantesRonda.
     */
    public ArrayList<ParticipantesRonda> getListaParticipantesRonda() {
        return listaParticipantesRonda;
    }

    /**
     * Setter de la lista de ParticipantesRonda.
     * @param listaParticipantesRonda lista de ParticipantesRonda.
     */
    public void setListaParticipantesRonda(ArrayList<ParticipantesRonda> listaParticipantesRonda) {
        this.listaParticipantesRonda = listaParticipantesRonda;
    }

    /**
     * Metodo ejecuta para abrir el video del participante en la ronda cuando se presiona sobre el boton.
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.participantesRonda.getUrlVideo()));
        startActivity(intent);
    }

    /**
     * Metodo para obtener el participantesRonda del viewholder.
     * @param participantesRonda objeto participantesRonda del viewholder.
     */
    @Override
    public void setParticipantesRonda(ParticipantesRonda participantesRonda) {
        this.participantesRonda = participantesRonda;
    }


    public void notificarCambioLista(){
        adaptador.setListaParticipantesRonda(listaParticipantesRonda);
        adaptador.notifyDataSetChanged();
    }
}
