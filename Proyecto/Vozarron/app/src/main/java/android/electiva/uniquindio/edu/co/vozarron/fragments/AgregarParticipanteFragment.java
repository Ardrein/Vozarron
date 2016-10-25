package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.electiva.uniquindio.edu.co.vozarron.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarParticipanteFragment extends DialogFragment {

    private Spinner spinner;

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
        View v = inflater.inflate(R.layout.fragment_agregar_participante,container,false);

        getDialog().setTitle(getResources().getString(R.string.agregar_participante));




        spinner = (Spinner) v.findViewById(R.id.spinner_entrenadores);
        ArrayAdapter<CharSequence> adaptador =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.entrenadores,android.R.layout.simple_spinner_item);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_participante, container, false);
    }

}
