package tomoaki.com.mynote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater

class NoteDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.note_details_menu, menu)
        return true

    }
}

