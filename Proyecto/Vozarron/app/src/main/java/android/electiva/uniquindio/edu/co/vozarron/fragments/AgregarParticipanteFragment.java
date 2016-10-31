package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Fragmento de dialogo para agregar un participante a la lista de participantes.
 * Solo es utilizado en el menu en la ParticipantesActivity.
 */
public class AgregarParticipanteFragment extends DialogFragment implements View.OnClickListener{

    /**
     * Elemento Spinner para mostrar la lista de los entrenadores.
     */
    private Spinner spinnerEntrenadores;

    /**
     * Lista entrenadores.
     */
    private ArrayList<Entrenador> entrenadores;

    /**
     * Instancia de la interface para comunicarse con la actividad.
     */
    private static OnAgregarListener listener;

    /**
     * Elemento Spinner para mostrar la lista de las relaciones con la universidad.
     */
    private Spinner spinnerRelU;

    /**
     * Instancia del boton agregar.
     */
    private Button btnAgregar;

    public AgregarParticipanteFragment() {
        // Required empty public constructor
    }

    /**
     * Interface para comunicarse con la actividad.
     */
    public interface OnAgregarListener{
        /**
         * Metodo para obtener la lista de entrenadores de la actividad.
         * @return ArrayList con la lista de los entrenadores.
         */
        ArrayList<Entrenador> getEntrenadores();

        /**
         * Metodo para agregar un participante a la lista de participantes.
         * @param participante a agregar.
         */
        void agregarParticipante(Participante participante);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                listener = (OnAgregarListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " debe implementar la interfaz OnAgregarListener");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agregar_participante,container,false);


        this.entrenadores = listener.getEntrenadores();
        spinnerEntrenadores = (Spinner) v.findViewById(R.id.spinner_entrenadores);
        spinnerRelU = (Spinner) v.findViewById(R.id.spinner_relacion_u);


        ArrayAdapter<Entrenador> adaptador =
                new ArrayAdapter<Entrenador>(getActivity(),android.R.layout.simple_spinner_item,entrenadores);



        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntrenadores.setAdapter(adaptador);

        ArrayAdapter<CharSequence> adaptador2 =
                ArrayAdapter.createFromResource(getActivity()
                        , R.array.relaciones_universidad,android.R.layout.simple_spinner_item);

        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRelU.setAdapter(adaptador2);

        btnAgregar = (Button) v.findViewById(R.id.btn_agregar);
        btnAgregar.setOnClickListener(this);

        spinnerRelU.setSelected(true);




        getDialog().setTitle(getResources().getString(R.string.agregar_participante));

        return v;
    }

    @Override
    public void onClick(View view) {

        String nombre = ((EditText) getDialog().findViewById(R.id.txt_agregar_nombre)).getText().toString();
        int edad = Integer.parseInt(((EditText) getDialog().findViewById(R.id.txt_agregar_edad)).getText().toString());
        String relacionU = ((Spinner) view.getRootView().findViewById(R.id.spinner_relacion_u)).getSelectedItem().toString();
        int posEntrenador = ((Spinner) view.getRootView().findViewById(R.id.spinner_entrenadores)).getSelectedItemPosition();
        Entrenador entrenador = entrenadores.get(posEntrenador);

        Participante participante = new Participante(nombre,edad,relacionU,R.drawable.cat);
        participante.setIdEntrenador(entrenador.getId());
        listener.agregarParticipante(participante);

        getDialog().dismiss();

    }
}
