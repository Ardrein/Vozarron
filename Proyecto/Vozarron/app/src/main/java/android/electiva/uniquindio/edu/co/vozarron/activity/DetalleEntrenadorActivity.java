package android.electiva.uniquindio.edu.co.vozarron.activity;

import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.DetalleDeEntrenadorFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Actividad utilizada para mostrar los detalles de un Entrenador.
 */
public class DetalleEntrenadorActivity extends AppCompatActivity {

    /**
     * Metodo llamado en la creacion de la actividad, utilizado para realizar inicializaciones.
     * @param savedInstanceState Bundle para recibir informacion de estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrenador);


        DetalleDeEntrenadorFragment detalleEntrenador =
                (DetalleDeEntrenadorFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_entrenador);

        Entrenador entrenador = (Entrenador) getIntent().getExtras().get(HomeActivity.ENTRENADOR_POS);
        detalleEntrenador.mostrarEntrenador(entrenador);
    }
}
