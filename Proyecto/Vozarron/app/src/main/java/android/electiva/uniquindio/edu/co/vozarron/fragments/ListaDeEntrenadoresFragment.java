package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.util.AdaptadorDeEntrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
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
    }


    /**
     * Metodo llamado en la creacion del fragmento.
     * @param savedInstanceState Bundle para recibir informacion de estado del fragmento.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entrenadores = new ArrayList<>();

        entrenadores.add(new Entrenador("Einer","profesor de la clase de dispositivos moviles","Masculino","gato"));
        entrenadores.add(new Entrenador("David","estudiante de la clase de dispositivos moviles","Masculino","gato"));
        entrenadores.add(new Entrenador("Alejandro","estudiante de la clase de dispositivos moviles","Masculino","gato"));

        listener.setEntrenadores(entrenadores);


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

        listadoDeEntrenadores = (RecyclerView) getView().findViewById(R.id.listaEntrenadores);
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
}
