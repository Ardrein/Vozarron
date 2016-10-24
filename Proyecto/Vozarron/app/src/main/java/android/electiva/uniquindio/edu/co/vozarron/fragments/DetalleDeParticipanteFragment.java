package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Fragmento para mostrar los detalles de un participante.
 */
public class DetalleDeParticipanteFragment extends Fragment {

    /**
     * TextView con el nombre del participante.
     */
    private TextView txtNombre;

    /**
     * TextView con el estado del participante.
     */
    private TextView txtEstado;

    /**
     * TextView con la relacion con la Universided del participante.
     */
    private TextView txtRelacionU;

    /**
     * TextView con la edad del participante.
     */
    private TextView txtEdad;

    /**
     * TextView con el nombre del entrenador del participante.
     */
    private TextView txtNombreEntrenador;

    /**
     * ImageView con la foto del participante.
     */
    private ImageView imgFoto;

    /**
     * Participante del que se obtienen los datos.
     */
    private Participante participante;


    /**
     * Constructor vacio.
     */
    public DetalleDeParticipanteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_de_participante, container, false);
    }

}
