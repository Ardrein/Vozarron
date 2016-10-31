package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.util.AdaptadorDeEntrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.electiva.uniquindio.edu.co.vozarron.vo.ParticipantesRonda;
import android.electiva.uniquindio.edu.co.vozarron.vo.Ronda;
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
 * Fragmento que contiene la lista de entrenadores.
 */
public class ListaDeEntrenadoresFragment extends Fragment implements AdaptadorDeEntrenador.OnClickAdaptadorDeEntrenador{


    /**
     * Instancia del adaptador de entrenador.
     */
    private AdaptadorDeEntrenador adaptador;

    /**
     * Instancia del RecyclerView.
     */
    private RecyclerView listadoDeEntrenadores;

    /**
     * Instancia utilizada para comunicarse con la actividad home.
     */
    private OnEntrenadorSeleccionadoListener listener;

    /**
     * ArrayList con la lista de entrenadores.
     */
    private ArrayList<Entrenador> entrenadores;

    /**
     * ArrayList con la lista de rondas.
     */
    private ArrayList<Ronda> rondas;


    /**
     * Constructor vacío.
     */
    public ListaDeEntrenadoresFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo que se ejecuta cuando se selecciona un elemento de la lista de entrenadores para mostrar los detalles
     * del entrenador.
     * @param pos posicion del entrenador seleccionado en la lista.
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onEntrenadorSeleccionado(pos);
    }


    /**
     * Interface a ser utilizada para la comunicación con la actividad Home.
     */
    public interface OnEntrenadorSeleccionadoListener{

        /**
         * Metodo de la interface para visualizar los detalles de un entrenador seleccionado.
         * @param pos posición en el array del entrenador seleccionado.
         */
        void onEntrenadorSeleccionado(int pos);

        /**
         * Setter de la lista de entrenadores.
         * @param listaEntrenadores lista de entrenadores.
         */
        void setEntrenadores(ArrayList<Entrenador> listaEntrenadores);

        /**
         * Setter de la lista de rondas.
         * @param rondas lista de rondas.
         */
        void setRondas(ArrayList<Ronda> rondas);
    }


    /**
     * Metodo llamado en la creacion del fragmento.
     * @param savedInstanceState Bundle para recibir informacion de estado del fragmento.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entrenadores = new ArrayList<>();
        rondas = new ArrayList<>();


        Entrenador entrenador1 = new Entrenador("Rihanna",getResources().getString(R.string.relleno),"Femenino",R.drawable.rihanna);
        entrenador1.setId("1");
        entrenador1.setListaParticipantes(new ArrayList<Participante>());

        Entrenador entrenador2 = new Entrenador("Adele",getResources().getString(R.string.relleno),"Femenino",R.drawable.adele);
        entrenador2.setId("2");
        entrenador2.setListaParticipantes(new ArrayList<Participante>());

        Entrenador entrenador3 = new Entrenador("Jhonny Rivera",getResources().getString(R.string.relleno),"Masculino",R.drawable.jhonny);
        entrenador3.setId("3");
        entrenador3.setListaParticipantes(new ArrayList<Participante>());

        Ronda ronda1 = new Ronda("Ronda 1");
        ronda1.setId("1");

        Ronda ronda2 = new Ronda("Ronda 2");
        ronda2.setId("2");

        rondas.add(ronda1);
        rondas.add(ronda2);


        ArrayList<ParticipantesRonda> participantesRonda1 = new ArrayList<>();
        ArrayList<ParticipantesRonda> participantesRonda2 = new ArrayList<>();

        Participante participante1 =  new Participante("Alejandro",24,"Estudiante",R.drawable.cat);
        participante1.setId("1");
        participante1.setIdEntrenador(entrenador1.getId());

        Participante participante2 =  new Participante("David",24,"Estudiante",R.drawable.user);
        participante2.setId("2");
        participante2.setIdEntrenador(entrenador2.getId());

        Participante participante3 =  new Participante("Jhon",24,"Administrativo",R.drawable.cat);
        participante3.setId("3");
        participante3.setIdEntrenador(entrenador3.getId());
        participante3.setEstado(false);

        participantesRonda1.add(new ParticipantesRonda("http://www.youtube.com",participante1.getId(),ronda1.getId()));
        participantesRonda1.add(new ParticipantesRonda("http://www.google.com",participante1.getId(),ronda2.getId()));
        participante1.setParticipantesRondas(participantesRonda1);

        participantesRonda2.add(new ParticipantesRonda("http://www.youtube.com",participante2.getId(),ronda1.getId()));
        participantesRonda2.add(new ParticipantesRonda("http://www.google.com",participante2.getId(),ronda2.getId()));
        participante2.setParticipantesRondas(participantesRonda2);

        participante3.setParticipantesRondas(new ArrayList<ParticipantesRonda>());



        entrenador1.getListaParticipantes().add(participante1);
        entrenador2.getListaParticipantes().add(participante2);
        entrenador3.getListaParticipantes().add(participante3);




        entrenadores.add(entrenador1);
        entrenadores.add(entrenador2);
        entrenadores.add(entrenador3);

        listener.setEntrenadores(entrenadores);
        listener.setRondas(rondas);


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
                listener = (OnEntrenadorSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " debe implementar la interfaz OnEntrenadorSeleccionadoListener");
            }
        }

    }


    /**
     * Metodo llamado cuando el metodo onCreate de la actividad finaliza su ejecucion.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoDeEntrenadores = (RecyclerView) getView().findViewById(R.id.lista_entrenadores);
        adaptador = new AdaptadorDeEntrenador(entrenadores,this);
        listadoDeEntrenadores.setAdapter(adaptador);
        listadoDeEntrenadores.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_de_entrenadores, container, false);
    }


    /**
     * Getter de la lista de entrenadores.
     * @return ArrayList de Entrenador con la lista de los entrenadores.
     */
    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    /**
     * Setter de la lista de entrenadores.
     * @param entrenadores ArrayList de Entrenador con la lista de los entrenadores.
     */
    public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    /**
     * Getter de la lista de rondas.
     * @return ArrayList de Ronda con la lista de las rondas.
     */
    public ArrayList<Ronda> getRondas() {
        return rondas;
    }

    /**
     * Setter de la lista de rondas.
     * @param rondas ArrayList de Ronda con la lista de las rondas.
     */
    public void setRondas(ArrayList<Ronda> rondas) {
        this.rondas = rondas;
    }
}
