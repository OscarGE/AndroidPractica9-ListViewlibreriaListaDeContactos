package com.example.practica9_listviewlibrerialistadecontactos

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment

class AnadirDialog: DialogFragment() {
    private lateinit var listener:AnadirDialogListener
    interface AnadirDialogListener {
        fun applyTexts(editNombre: String,editTel: String,editCorreo: String, imgFoto: Uri?)
    }

    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val binding = inflater.inflate(R.layout.layout_dialog, null)
            var theUri: Uri?=null
            val getContent = registerForActivityResult(ActivityResultContracts.GetContent())  { uri: Uri? ->
                binding.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.imgFoto).setImageBitmap(null)
                binding.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.imgFoto).setImageURI(null)
                binding.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.imgFoto).setImageURI(uri)
                if (uri != null) {
                    theUri= uri
                }
            }
            binding.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.imgFoto).setOnClickListener{
                getContent.launch("image/*")
            }
            builder.setView(binding)
                .setTitle("Nuevo Contacto")
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        val editNombre= binding.findViewById<EditText>(R.id.editNombre).text.toString()
                        val editTel=binding.findViewById<EditText>(R.id.editTel).text.toString()
                        val editCorreo= binding.findViewById<EditText>(R.id.editCorreo).text.toString()

                        listener.applyTexts(editNombre,editTel,editCorreo,theUri)

                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as AnadirDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implementAnadirDialogListener"))
        }
    }



}