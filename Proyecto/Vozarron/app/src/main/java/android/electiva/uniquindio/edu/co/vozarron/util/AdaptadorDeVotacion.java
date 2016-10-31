package android.electiva.uniquindio.edu.co.vozarron.util;

import android.electiva.uniquindio.edu.co.vozarron.R;
import android.electiva.uniquindio.edu.co.vozarron.fragments.VotacionesFragment;
import android.electiva.uniquindio.edu.co.vozarron.vo.Participante;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
        * Clase de adaptador para almacenar la informacion de los elementos de la lista de participantes activos para posteriormente
        * ser visualizada en un control de seleccion.
        */

public class AdaptadorDeVotacion extends RecyclerView.Adapter<AdaptadorDeVotacion.VotacionesViewHolder>{

    /**
     * Lista de los participantes.
     */
    private ArrayList<Participante> participantes;

    /**
     * Instancia de la interface OnClickAdaptadorDeVotacion para transmitir información entre la clase y el fragmento de
     * votaciones.
     */
    private static OnClickAdaptadorDeVotacion listener;

    /**
     * View del elemento de la lista seleccionado.
     */
    private static View itemSeleccionado;

    /**
     * Entero para mantener seguimiento a la posicion seleccionada en la lista.
     */
    private static int posSeleccionado  = -1;






    /**
     * Constructor de la clase.
     * @param participantes lista de participantes.
     * @param votacionesFragment fragmento de votaciones.
     */
    public AdaptadorDeVotacion(ArrayList<Participante> participantes, VotacionesFragment votacionesFragment) {
        this.participantes = participantes;
        listener = (OnClickAdaptadorDeVotacion) votacionesFragment;
    }

    /**
     * Interface utilizada para comunicar la clase con el fragmento de votaciones.
     */
    public interface OnClickAdaptadorDeVotacion{

        /**
         * Metodo que se ejecuta cuando se selecciona un elemento de la lista de participantes para mostrar
         * la opcion de votacion.
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
    public VotacionesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_votacion,parent,false);

        VotacionesViewHolder votacionesVH = new VotacionesViewHolder(itemView);

        return votacionesVH;
    }

    /**
     * Meotodo para realizar el vinculo entre el viewHolder y los datos del participante.
     * @param holder ViewHolder al cual se le va a hacer el vinculo de los datos del participante.
     * @param position posicion del participante en la lista.
     */
    @Override
    public void onBindViewHolder(VotacionesViewHolder holder, int position) {
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
     * Clase VotacionesViewHolder en donde se hace uso del patron ViewHolder para almacenar todos los controles que son cargados
     * por el metodo findViewById de cada uno de los items del control de seleccion.
     */
    public static class VotacionesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        /**
         *TextView que contendrá el nombre del participante.
         */
        private TextView txtNombreParticipante;

        /**
         *TextView que contendrá la cantidad de votos del participante.
         */
        private TextView txtVotosParticipante;

        /**
         * ImageView que contendrá la foto del participante.
         */
        private ImageView imgFotoParticipante;


        /**
         * Constructor de la clase.
         * @param itemView vista que contiene los controles.
         */
        public VotacionesViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txtNombreParticipante = (TextView) itemView.findViewById(R.id.votacion_nombre_participante);
            txtVotosParticipante = (TextView) itemView.findViewById(R.id.votacion_votos_participante);
            imgFotoParticipante = (ImageView) itemView.findViewById(R.id.votacion_foto_participante);

        }


        /**
         * Metodo para asociar los atributos de una instancia de Participante con los controles de una instancia de esta clase.
         * @param participante instancia de la clase Participante de la cual se obtienen los datos.
         */
        public void bindParticipante(Participante participante){
            txtNombreParticipante.setText(participante.getNombre());
            txtVotosParticipante.setText(String.format("%d",participante.getVotos()));
            imgFotoParticipante.setImageResource(participante.getFoto());

        }

        /**
         * Metodo que se ejecuta cuando se presiona sobre un elemento de la lista.
         */
        @Override
        public void onClick(View view) {
            if(!view.isSelected()){
                if(itemSeleccionado!=null){
                    //deseleccion del anterior
                    itemSeleccionado.setSelected(false);
                }
                itemSeleccionado = view;
                posSeleccionado = getAdapterPosition();
            }else{
                //deseleccion del actual
                itemSeleccionado = null;
                posSeleccionado = -1;
            }

            //cambio de estado de seleccion
            view.setSelected(!view.isSelected());
            listener.onClickPosition(posSeleccionado);


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
