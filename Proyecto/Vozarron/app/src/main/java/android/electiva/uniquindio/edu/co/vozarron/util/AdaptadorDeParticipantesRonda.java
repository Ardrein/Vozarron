package android.electiva.uniquindio.edu.co.vozarron.util;

import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.ListaDeParticipantesRondaFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.ParticipantesRonda;
import android.electiva.uniquindio.edu.co.vozarron.vo.Ronda;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Clase de adaptador para almacenar la informacion de los elementos de la lista de participantesRonda para posteriormente
 * ser visualizada en un control de seleccion.
 */

public class AdaptadorDeParticipantesRonda extends RecyclerView.Adapter<AdaptadorDeParticipantesRonda.ParticipantesRondaViewHolder>{

    /**
     * Lista de los detalles de las rondas del participante.
     */
    private ArrayList<ParticipantesRonda> listaParticipantesRonda;

    /**
     * Instancia de la interface OnClickAdaptadorDeParticipantesRonda para transmitir información entre la clase y el
     * fragmento de la lista de participantesRonda.
     */
    private static OnClickAdaptadorDeParticipantesRonda listener;

    /**
     * Constructor de la clase.
     * @param listaParticipantesRonda lista de participantesRonda.
     * @param listaDeParticipantesRondaFragment fragmento de la lista de participantesRonda.
     */
    public AdaptadorDeParticipantesRonda(ArrayList<ParticipantesRonda> listaParticipantesRonda,
                                         ListaDeParticipantesRondaFragment listaDeParticipantesRondaFragment){
        this.listaParticipantesRonda = listaParticipantesRonda;
        listener = (OnClickAdaptadorDeParticipantesRonda) listaDeParticipantesRondaFragment;

    }




    /**
     * Interface utilizada para comunicar la clase con el fragmento de la lista de participantesRonda.
     */
    public interface OnClickAdaptadorDeParticipantesRonda{

        /**
         * Metodo que se ejecuta cuando se selecciona un boton de un elemento de la lista de participantesRonda
         * para redireccionar hacia la direccion del mismo.
         * @param pos posicion del participantesRonda seleccionado en la lista.
         */
        void onClickPosition(int pos);

        /**
         * Metodo para obtener una instancia de onClickListener. En este caso se busca el fragmento
         * para asociar al botonUrl.
         * @return instancia de onClickListener para asociar al botonUrl.
         */
        View.OnClickListener getOnClickListener();

        /**
         * Metodo para obtener una ronda segun su id.
         * @param id String con el id de la ronda.
         * @return Ronda a la que pertenece el id.
         */
        Ronda findRondaById(String id);

        /**
         * Metodo para enviar el participantesRonda asociado a el viewholder hacia el fragmento.
         * @param participantesRonda
         */
        void setParticipantesRonda(ParticipantesRonda participantesRonda);


    }



    /**
     * Metodo ejecutado cuando  RecyclerView necesita un View de este tipo para representar un objeto, en este caso
     * un ParticipantesRonda.
     * @param parent  ViewGroup en el cual el nuevo View sera añadido despues de que sea asociado a una posicion de adapter.
     * @param viewType tipo de view del nuevo view.
     * @return un nuevo ViewHolder que contiene el view para el participantesRonda.
     */
    @Override
    public ParticipantesRondaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_participantes_ronda,parent,false);

        ParticipantesRondaViewHolder pRondaVH = new ParticipantesRondaViewHolder(itemView);
        return pRondaVH;
    }

    /**
     * Meotodo para realizar el vinculo entre el viewHolder y los datos del participantesRonda.
     * @param holder ViewHolder al cual se le va a hacer el vinculo de los datos del participantesRonda.
     * @param position posicion del participantesRonda en la lista.
     */
    @Override
    public void onBindViewHolder(ParticipantesRondaViewHolder holder, int position) {
        ParticipantesRonda participantesRonda = listaParticipantesRonda.get(position);
        holder.bindParticipantesRonda(participantesRonda);
    }

    /**
     * Metodo para obtener el tamaño de la lista de participantesRonda.
     * @return entero con el tamaño del arrayList de participantesRonda.
     */
    @Override
    public int getItemCount() {
        return listaParticipantesRonda.size();
    }




    public static class ParticipantesRondaViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        /**
         *TextView que contendrá el nombre de la ronda.
         */
        private TextView txtNombreRonda;

        /**
         * Boton que contendrá la url del video de la ronda del participante y que permitirá navegar hacia dicha url.
         */
        private Button btnUrlVideo;

        /**
         * Constructor de la clase.
         * @param itemView vista que contiene los controles.
         */
        public ParticipantesRondaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombreRonda = (TextView) itemView.findViewById(R.id.resumen_nombre_ronda);
            btnUrlVideo = (Button) itemView.findViewById(R.id.btn_resumen_url);

        }

        /**
         * Metodo para asociar los atributos de una instancia de ParticipantesRonda con los controles de una instancia de esta clase.
         * @param participantesRonda instancia de la clase ParticipantesRonda de la cual se obtienen los datos.
         */
        public void bindParticipantesRonda(ParticipantesRonda participantesRonda){

            txtNombreRonda.setText(listener.findRondaById(participantesRonda.getIdRonda()).getNombre());
            btnUrlVideo.setOnClickListener(listener.getOnClickListener());
            listener.setParticipantesRonda(participantesRonda);


        }

        /**
         * Metodo que se ejecuta cuando se presiona sobre un elemento de la lista .
         */
        @Override
        public void onClick(View view) {
            listener.onClickPosition(getAdapterPosition());

        }
    }


    /**
     * Getter de la lista de ParticipantesRonda.
     * @return lista de ParticipantesRonda.
     */
    public ArrayList<ParticipantesRonda> getListaParticipantesRonda() {
        return listaParticipantesRonda;
    }

    /**
     * Setter de la lista de ParticipantesRonda.
     * @param listaParticipantesRonda lista de ParticipantesRonda.
     */
    public void setListaParticipantesRonda(ArrayList<ParticipantesRonda> listaParticipantesRonda) {
        this.listaParticipantesRonda = listaParticipantesRonda;
    }
}
