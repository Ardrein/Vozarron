package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.electiva.uniquindio.edu.co.vozarron.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleParticipanteFragment extends Fragment {


    public DetalleParticipanteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_participante, container, false);
    }

}
