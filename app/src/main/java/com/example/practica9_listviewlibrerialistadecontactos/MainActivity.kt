package com.example.practica9_listviewlibrerialistadecontactos


import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica9_listviewlibrerialistadecontactos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AnadirDialog.AnadirDialogListener{
    private val pkN=BuildConfig.APPLICATION_ID
    private val listContactos= mutableListOf(
        Contacto("Iván Castillo","449 233 7476", "ivan23@gmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact1}")),
        Contacto("Francisco Medina","449 439 3134", "frank85@hotmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact2}")),
        Contacto("Ian López","449 412 3271", "ian54@gmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact3}")),
        Contacto("Orlando Montañez","449 338 2695", "orlando23@hotmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact4}")),
        Contacto("Yessica Medina","449 417 0477", "yessica33@gmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact5}")),
        Contacto("Alondra Macías","449 495 3679", "alondra11@hotmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact6}")),
        Contacto("Yamile Gutiérrez","449 201 7177", "yami03@gmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact7}")),
        Contacto("Andrés Pérez","449 396 5900", "andres3@hotmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact8}")),
        Contacto("Percival de la Torre","449 544 7040", "percy@gmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact9}")),
        Contacto("Maafs Atilano","475 108 0851", "maps66@hotmail.com",Uri.parse("android.resource://$pkN/${R.drawable.contact10}"))
    )

    private val adaptador = AdaptadorContactos(listContactos){
        val items: RecyclerView=findViewById(R.id.recView)
        if( items[it].background==null){
            items[it].setBackgroundResource(R.drawable.seleccionado)
        }
        else{
            items[it].setBackground(null)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btmAnadir.setOnClickListener{
            val anadirDialog = AnadirDialog()
            anadirDialog.show(supportFragmentManager, "anadir dialog")
        }

        binding.recView.setHasFixedSize(false)
        binding.recView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        binding.recView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recView.adapter = adaptador

    }

    override fun applyTexts(editNombre: String, editTel: String, editCorreo: String, imgFoto: Uri?) {
        if(imgFoto!=null)
            listContactos.add(0, Contacto(editNombre,editTel, editCorreo, imgFoto))
        else
            listContactos.add(0, Contacto(editNombre,editTel, editCorreo, Uri.parse("android.resource://$pkN/${R.drawable.user}")))
        adaptador.notifyDataSetChanged()
    }
}