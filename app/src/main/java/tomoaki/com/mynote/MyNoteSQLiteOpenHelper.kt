package tomoaki.com.mynote

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyNoteSQLiteOpenHelper(context :Context) :SQLiteOpenHelper(context , "MynoteDB",null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE notes ("
                        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "title TEXT,"
                        +"description TEXT)"

        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}