package com.example.practica9_listviewlibrerialistadecontactos

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Contacto(val nombre: String,val tel: String,val correo: String, val imagen: Uri)

class AdaptadorContactos (private val listaContactos: MutableList<Contacto>,
                          private val clickListener: (Int) -> Unit):

    RecyclerView.Adapter<AdaptadorContactos.ContactoViewHolder>() {
    class ContactoViewHolder(item: View): RecyclerView.ViewHolder(item){
        val txt_nombre = item.findViewById(R.id.txt_nombre) as TextView
        val txt_tel = item.findViewById(R.id.txt_tel) as TextView
        val txt_correo = item.findViewById(R.id.txt_correo) as TextView
        val imagen = item.findViewById(R.id.imagen) as de.hdodenhof.circleimageview.CircleImageView
        fun bindContacto(contacto: Contacto){
            txt_nombre.text=contacto.nombre
            txt_tel.text=contacto.tel
            txt_correo.text=contacto.correo
            imagen.setImageURI(contacto.imagen)
        }
    }
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contactos, parent, false) as androidx.constraintlayout.widget.ConstraintLayout
        return ContactoViewHolder(item)
    }
    override fun onBindViewHolder (holder: ContactoViewHolder, position: Int) {
        val contacto=listaContactos[position]
        holder.bindContacto(contacto)
        holder.itemView.setOnClickListener{clickListener(position)}
    }

    override fun getItemCount() = listaContactos.size

}