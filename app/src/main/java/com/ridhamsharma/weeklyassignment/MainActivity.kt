package com.ridhamsharma.weeklyassignment

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ridhamsharma.weeklyassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var lvList = arrayListOf<String>()
    lateinit var adapter: ArrayAdapter<String>
   // lateinit var spinView: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // spinView=findViewById(R.id.spinView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lvList)
        binding.lvList.adapter = adapter
        //////// btn plus start here ///////////

        binding.fab.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.layout_dialog)
            dialog.getWindow()?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            );
            var name = dialog.findViewById<EditText>(R.id.etname)
            var add = dialog.findViewById<Button>(R.id.btnAdd)
            add.setOnClickListener {
                if (name.text.toString().isNullOrEmpty()) {
                    name.error = "Enter your name"
                } else {
                    lvList.add("${name.text.toString()}")
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        /////// plus btn ends here ////////

        /*****  alert dialog starts here  ******/
        binding.lvList.setOnItemClickListener { parent, view, position, id ->
            var name: EditText? = null
            name = findViewById(R.id.etname)
            AlertDialog.Builder(this)
                .setMessage("Do You Want To Delete Or Update")
                .setCancelable(true)

                .setNeutralButton("Cancel") { _, _ ->
                    Toast.makeText(this, "CLicked cancel", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Delete") { _, _ ->
                    Toast.makeText(this, "Clicked No", Toast.LENGTH_SHORT).show()
                    lvList.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
                .setPositiveButton("Update") { _, _ ->
                    Toast.makeText(this, "Clicked Update", Toast.LENGTH_SHORT).show()
                    /**** alert ends here ****/


                    /**** custom dialog  ****/
                    var dialog = Dialog(this)
                    dialog.setContentView(R.layout.cutomdialogalert)
                    dialog.getWindow()?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    var btnUpdate: Button? = null
                    var etNewItemName: EditText? = null
                    btnUpdate = dialog.findViewById(R.id.btnUpdate)
                    etNewItemName = dialog.findViewById(R.id.etNewItemName)
                    btnUpdate.setOnClickListener {
                        if (etNewItemName.text.toString().isNullOrEmpty()) {
                            etNewItemName.error = "Enter the name"
                        } else {
                            lvList.set(position, etNewItemName.text.toString())
                            dialog.dismiss()
                            adapter.notifyDataSetChanged()
                        }
                    }
                    dialog.show()

                }
                /**** Custom Dialog Ends Here ****/
                .show()
        }
    }
}