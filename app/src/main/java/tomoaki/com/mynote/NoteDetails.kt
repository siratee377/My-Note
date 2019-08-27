package tomoaki.com.mynote

import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetails : AppCompatActivity() {


    var db:SQLiteDatabase? = null
    var noteId :Int = 0
    var cursor :Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        val myNoteDatabaseHelper = MyNoteSQLiteOpenHelper(this)
        db = myNoteDatabaseHelper.writableDatabase

        noteId = intent.extras.get("NOTE_ID").toString().toInt()

        if (noteId!=0) {
            // filter data for query
            cursor = db!!.query("notes", arrayOf("title","description"),
                "_id=?", arrayOf(noteId.toString()),null,null,null)

            if (cursor!!.moveToFirst()) {

                cursor!!.moveToFirst()
                editTextTitle.setText(cursor!!.getString(0))
                editTextDescription.setText(cursor!!.getString(1))
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item!!.itemId == R.id.save_note) {

            val newNoteValues = ContentValues()

            if (editTextTitle.text.isEmpty()) {
                newNoteValues.put("title", "Untitled")
            } else {
                newNoteValues.put("title", editTextTitle.text.toString())
            }
            newNoteValues.put("description", editTextDescription.text.toString())


            //choose between insert or update
            if (noteId==0) {
                insertNote(newNoteValues)

            }else {
                updateNote(newNoteValues)

            }

        }else if (item!!.itemId == R.id.delete_note) {

            deleteNote()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteNote() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("You Want To Delete This Note?")
        builder.setTitle("Delete Message")

            .setPositiveButton("Yes") { dialog, id ->
                db!!.delete("notes","_id=?", arrayOf(noteId.toString()))
                Toast.makeText(this,"The Note Deleted",Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                // User cancelled the dialog
            }

        var dialog:AlertDialog = builder.create()
        dialog.show()

    }

    private fun updateNote(newNoteValues :ContentValues) {

        db!!.update("notes",newNoteValues,"_id=?", arrayOf(noteId.toString()))
        finish()
        Toast.makeText(this,"Note Updated",Toast.LENGTH_SHORT).show()
    }

    private fun insertNote(newNoteValues :ContentValues) {
        // put text in content value and insert to DB

        db!!.insert("notes", null, newNoteValues)
        finish()
        Toast.makeText(this, "The Note Has Been Saved", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        val inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.note_details_menu, menu)
        return true

    }

    override fun onDestroy() {
        super.onDestroy()

        db!!.close()
    }
}

