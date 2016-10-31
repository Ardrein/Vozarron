package android.electiva.uniquindio.edu.co.vozarron.fragments;


import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Fragmento para mostrar los detalles de un entrenador.
 */
public class DetalleDeEntrenadorFragment extends Fragment {


    /**
     * TextView con el nombre del entrenador.
     */
    private TextView txtNombre;

    /**
     * TextView con el historial del entrenador.
     */
    private TextView txtHistorial;

    /**
     * TextView con el genero del entrenador.
     */
    private TextView txtGenero;

    /**
     * ImageView con la foto del entrenador.
     */
    private ImageView imgFoto;

    /**
     * Instancia de entrenador para obtener los datos de este.
     */
    private Entrenador entrenador;




    /**
     * Constructor vacio.
     */
    public DetalleDeEntrenadorFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo para inicializar los TextView con la información del entrenador.
     * @param entrenador Instancia de entrenador del que se obtienen los detalles.
     */
    public void mostrarEntrenador(Entrenador entrenador){
        this.entrenador = entrenador;

        txtNombre = (TextView) getView().findViewById(R.id.detalle_entrenador_nombre);
        txtNombre.setText(entrenador.getNombre());

        txtGenero = (TextView) getView().findViewById(R.id.detalle_entrenador_genero);
        txtGenero.setText(entrenador.getGenero());

        txtHistorial = (TextView) getView().findViewById(R.id.detalle_entrenador_historial);
        txtHistorial.setText(entrenador.getHistoria());

        imgFoto = (ImageView) getView().findViewById(R.id.detalle_entrenador_foto);
        imgFoto.setImageResource(entrenador.getFoto());

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_de_entrenador, container, false);
    }

}
