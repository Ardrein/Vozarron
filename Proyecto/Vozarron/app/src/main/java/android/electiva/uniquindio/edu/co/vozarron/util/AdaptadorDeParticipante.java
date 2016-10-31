package android.electiva.uniquindio.edu.co.vozarron.util;

import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeParticipantesFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Clase de adaptador para almacenar la informacion de los elementos de la lista de participantes para posteriormente
 * ser visualizada en un control de seleccion.
 */

public class AdaptadorDeParticipante extends RecyclerView.Adapter<AdaptadorDeParticipante.ParticipanteViewHolder>{

    /**
     * Lista de los participantes.
     */
    private ArrayList<Participante> participantes;

    /**
     * Instancia de la interface OnClickAdaptadorDeParticipante para transmitir información entre la clase y el fragmento de la lista
     * de participantes.
     */
    private static OnClickAdaptadorDeParticipante listener;

    /**
     * Constructor de la clase.
     * @param participantes lista de participantes.
     * @param listaDeParticipantesFragment fragmento de la lista de participantes.
     */
    public AdaptadorDeParticipante(ArrayList<Participante> participantes, ListaDeParticipantesFragment listaDeParticipantesFragment){
        this.participantes = participantes;
        listener = (OnClickAdaptadorDeParticipante) listaDeParticipantesFragment;
    }

    /**
     * Interface utilizada para comunicar la clase con el fragmento de la lista de participantes.
     */
    public interface OnClickAdaptadorDeParticipante{

        /**
         * Metodo que se ejecuta cuando se selecciona un elemento de la lista de participantes para mostrar los detalles
         * del participante.
         * @param pos posicion del participante seleccionado en la lista.
         */
        public void onClickPosition(int pos);
    }


    /**
     * Metodo ejecutado cuando  RecyclerView necesita un View de este tipo para representar un objeto, en este caso
     * un Participante.
     * @param parent  ViewGroup en el cual el nuevo View sera añadido despues de que sea asociado a una posicion de adapter
     * @param viewType tipo de view del nuevo view.
     * @return un nuevo ViewHolder que contiene el view para el participante.
     */
    @Override
    public ParticipanteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_participantes,parent,false);

        ParticipanteViewHolder participanteVH = new ParticipanteViewHolder(itemView);

        return participanteVH;
    }

    /**
     * Meotodo para realizar el vinculo entre el viewHolder y los datos del participante.
     * @param holder ViewHolder al cual se le va a hacer el vinculo de los datos del participante.
     * @param position posicion del participante en la lista.
     */
    @Override
    public void onBindViewHolder(ParticipanteViewHolder holder, int position) {
        Participante participante = participantes.get(position);
        holder.bindParticipante(participante);
    }

    /**
     * Metodo para obtener el tamaño de la lista de participantes.
     * @return entero con el tamaño del arrayList de participante.
     */
    @Override
    public int getItemCount() {
        return participantes.size();
    }



    /**
     * Clase ParticipanteViewHolder en donde se hace uso del patron ViewHolder para almacenar todos los controles que son cargados
     * por el metodo findViewById de cada uno de los items del control de seleccion.
     */
    public static class ParticipanteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        /**
         *TextView que contendrá el nombre del participante.
         */
        private TextView txtNombreParticipante;

        /**
         * TextView que contendrá la relación con la universidad del participante.
         */
        private TextView txtRelacionUParticipante;

        /**
         * ImageView que contendrá la foto del participante.
         */
        private ImageView imgFotoParticipante;

        /**
         * Constructor de la clase.
         * @param itemView vista que contiene los controles.
         */
        public ParticipanteViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            txtNombreParticipante = (TextView) itemView.findViewById(R.id.resumen_nombre_participante);
            txtRelacionUParticipante = (TextView) itemView.findViewById(R.id.resumen_relacion_u_participante);
            imgFotoParticipante = (ImageView) itemView.findViewById(R.id.resumen_foto_participante);

        }

        /**
         * Metodo para asociar los atributos de una instancia de Participante con los controles de una instancia de esta clase.
         * @param participante instancia de la clase Participante de la cual se obtienen los datos.
         */
        public void bindParticipante(Participante participante){
            txtNombreParticipante.setText(participante.getNombre());
            txtRelacionUParticipante.setText(participante.getRelacionUniversidad());
            imgFotoParticipante.setImageResource(participante.getFoto());

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
     * Getter de participantes.
     * @return lista de participantes.
     */
    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    /**
     * Setter de participantes.
     * @param participantes lista de participantes.
     */
    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }
}
