package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.electiva.uniquindio.edu.co.vozarron.R;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragmento de dialogo para verificar si se procede con la votacion.
 */
public class VotarDialogFragment extends DialogFragment {

    /**
     * Instancia para el boton de si.
     */
    Button btnSi;

    /**
     * Instancia para boton de no.
     */
    Button btnNo;

    /**
     * Instancia de la interface para la comunicacion con la actividad de votar.
     */
    private static OnVotarListener listener;


    public VotarDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Interface para la comunicacion con la actividad de Votar.
     */
    public interface OnVotarListener{

        /**
         * Metodo que se ejecuta cuando se confirma la votacion por el participante.
         */
        void votarPor();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                listener = (OnVotarListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " debe implementar la interfaz OnVotarListener");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_votar_dialog, container, false);

        getDialog().setTitle(R.string.votar);


        btnSi = (Button)v.findViewById(R.id.btn_votar_si);
        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.votarPor();
                getDialog().dismiss();
            }
        });

        btnNo = (Button)v.findViewById(R.id.btn_votar_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();

            }
        });




        return v;
    }

}
