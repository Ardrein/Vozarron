package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.electiva.uniquindio.edu.co.vozarron.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Fragmento de dialogo para agregar un participante a la lista de participantes.
 * Solo es utilizado en el menu en la ParticipantesActivity.
 */
public class AgregarParticipanteFragment extends DialogFragment {

    /**
     * Elemento Spinner para mostrar la lista de los entrenadores.
     */
    private Spinner spinnerEntrenadores;

    /**
     * Elemento Spinner para mostrar la lista de las relaciones con la universidad.
     */
    private Spinner spinnerRelU;

    public AgregarParticipanteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agregar_participante,container,false);


        spinnerEntrenadores = (Spinner) v.findViewById(R.id.spinner_entrenadores);
        spinnerRelU = (Spinner) v.findViewById(R.id.spinner_relacion_u);

        ArrayAdapter<CharSequence> adaptador =
                ArrayAdapter.createFromResource(getActivity()
                        , R.array.entrenadores,android.R.layout.simple_spinner_item);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntrenadores.setAdapter(adaptador);

        ArrayAdapter<CharSequence> adaptador2 =
                ArrayAdapter.createFromResource(getActivity()
                        , R.array.relaciones_universidad,android.R.layout.simple_spinner_item);

        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRelU.setAdapter(adaptador2);





        getDialog().setTitle(getResources().getString(R.string.agregar_participante));

        return v;
    }

}
