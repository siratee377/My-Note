package tomoaki.com.mynote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DB
        var objToCreateDB = MyNoteSQLiteOpenHelper(this)
        var db = objToCreateDB.readableDatabase

        var cursor = db.query("notes", arrayOf("_id","title"),
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
}
