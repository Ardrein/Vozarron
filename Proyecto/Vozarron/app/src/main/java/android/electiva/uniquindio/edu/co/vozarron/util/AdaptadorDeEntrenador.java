package android.electiva.uniquindio.edu.co.vozarron.util;

import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeEntrenadoresFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Entrenador;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Clase de adaptador para almacenar la informacion de los elementos de la lista de entrenadores para posteriormente
 * ser visualizada en un control de seleccion.
 */

public class AdaptadorDeEntrenador extends RecyclerView.Adapter<AdaptadorDeEntrenador.EntrenadorViewHolder>{

    /**
     * Lista de los entrenadores.
     */
    private ArrayList<Entrenador> entrenadores;

    /**
     * Instancia de la interface OnClickAdaptadorDeEntrenador para transmitir información entre la clase y el fragmento de la lista
     * de entrenadores.
     */
    private static OnClickAdaptadorDeEntrenador listener;

    /**
     * Constructor de la clase.
     * @param entrenadores lista de entrenadores.
     * @param listaDeEntrenadoresFragment fragmento de la lista de entrenadores.
     */
    public AdaptadorDeEntrenador(ArrayList<Entrenador> entrenadores, ListaDeEntrenadoresFragment listaDeEntrenadoresFragment){
        this.entrenadores = entrenadores;
        listener = (OnClickAdaptadorDeEntrenador) listaDeEntrenadoresFragment;
    }

    /**
     * Interface utilizada para comunicar la clase con el fragmento de la lista de entrenadores.
     */
    public interface OnClickAdaptadorDeEntrenador{
        /**
         * Metodo que se ejecuta cuando se selecciona un elemento de la lista de entrenadores para mostrar los detalles
         * del entrenador.
         * @param pos posicion del entrenador seleccionado en la lista.
         */
        public void onClickPosition(int pos);
    }


    /**
     * Metodo ejecutado cuando  RecyclerView necesita un View de este tipo para representar un objeto, en este caso
     * un Entrenador.
     * @param parent  ViewGroup en el cual el nuevo View sera añadido despues de que sea asociado a una posicion de adapter
     * @param viewType tipo de view del nuevo view.
     * @return un nuevo ViewHolder que contiene el view para el entrenador.
     */
    @Override
    public EntrenadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_entrenadores,parent,false);

        EntrenadorViewHolder entrenadorVH = new EntrenadorViewHolder(itemView);
        return entrenadorVH;
    }

    /**
     * Meotodo para realizar el vinculo entre el viewHolder y los datos del entrenador.
     * @param holder ViewHolder al cual se le va a hacer el vinculo de los datos del entrenador.
     * @param position posicion del entrenador en la lista.
     */
    @Override
    public void onBindViewHolder(EntrenadorViewHolder holder, int position) {
        Entrenador entrenador = entrenadores.get(position);
        holder.bindEntrenador(entrenador);
    }

    /**
     * Metodo para obtener el tamaño de la lista de entrenadores.
     * @return entero con el tamaño del arrayList de entrenador.
     */
    @Override
    public int getItemCount() {
        return entrenadores.size();
    }


    /**
     * Clase EntrenadorViewHolder en donde se hace uso del patron ViewHolder para almacenar todos los controles que son cargados
     * por el metodo findViewById de cada uno de los items del control de seleccion.
     */
    public static class EntrenadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        /**
         * TextView que contendrá el nombre del entrenador.
         */
        private TextView txtNombreEntrenador;

        /**
         * ImageView que contendrá la imagen del entrenador.
         */
        private ImageView imgFotoEntrenador;


        /**
         * Constructor de la clase.
         * @param itemView vista que contiene los controles.
         */
        public EntrenadorViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txtNombreEntrenador = (TextView) itemView.findViewById(R.id.resumen_nombre_entrenador);
            imgFotoEntrenador = (ImageView) itemView.findViewById(R.id.resumen_foto_entrenador);

        }

        /**
         * Metodo para asociar los atributos de una instancia de Entrenador con los controles de una instancia de esta clase.
         * @param entrenador instancia de la clase Entrenador de la cual se obtienen los datos.
         */
        public void bindEntrenador(Entrenador entrenador){
            txtNombreEntrenador.setText(entrenador.getNombre());
            imgFotoEntrenador.setImageResource(entrenador.getFoto());


        }


        /**
         * Metodo que se ejecuta cuando se presiona sobre un elemento de la lista.
         */
        @Override
        public void onClick(View view) {
            listener.onClickPosition(getAdapterPosition());

        }
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
