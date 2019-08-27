package tomoaki.com.mynote

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var cursor: Cursor? = null
    var db: SQLiteDatabase? = null

    override fun onStart() {
        super.onStart()

        // DB
        var objToCreateDB = MyNoteSQLiteOpenHelper(this)
        db = objToCreateDB.readableDatabase

        cursor = db!!.query("notes", arrayOf("_id","title"),
            null, null, null, null, null)

        //adapter
        var listAdapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_1,
            cursor,
            arrayOf("title"),
            intArrayOf(android.R.id.text1),
            0
        )

        //set adapter
        listViewNote.adapter = listAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAddNote.setOnClickListener {

            openNoteDetailActivity(0)
        }
        listViewNote.setOnItemClickListener { parent, view, position, id ->

            openNoteDetailActivity(id)
        }

    }

    fun openNoteDetailActivity( id :Long) {

        val intent = Intent(this,NoteDetails::class.java)
        intent.putExtra("NOTE_ID",id)
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()

        cursor!!.close()
        db!!.close()
    }
    

}
