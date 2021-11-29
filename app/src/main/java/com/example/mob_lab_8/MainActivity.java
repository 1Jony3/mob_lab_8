package com.example.mob_lab_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button buttonAdd, buttonDelete, buttonClear, buttonRead,
            buttonUpdate;
    TextInputEditText etName, etAuthor, etDate, etID;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.add);
        buttonAdd.setOnClickListener(this);
        buttonRead = (Button) findViewById(R.id.read);
        buttonRead.setOnClickListener(this);
        /*buttonClear = (Button) findViewById(R.id.button3);
        buttonClear.setOnClickListener(this);*/
        buttonUpdate = (Button) findViewById(R.id.update);
        buttonUpdate.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.delete);
        buttonDelete.setOnClickListener(this);

        etID = findViewById(R.id.textID);
        etName = findViewById(R.id.textName);
        etAuthor = findViewById(R.id.textAuthor);
        etDate = findViewById(R.id.date);

        dbHelper = new DBHelper(this);
    }
    @Override
    public void onClick(View v) {
        String ID = etID.getText().toString();
        String name = etName.getText().toString();
        String author = etAuthor.getText().toString();
        String date = etDate.getText().toString();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // класс для добавления новых строк в таблицу
        switch (v.getId())
        {
            case R.id.add:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_AUTHOR, author);
                contentValues.put(DBHelper.KEY_Date, date);
                database.insert(DBHelper.TABLE_PERSONS, null,
                        contentValues);
                break;
            case R.id.read:
                Cursor cursor = database.query(DBHelper.TABLE_PERSONS, null,
                        null, null,
                        null, null, null); // все поля без сортировки и группировки
                if (cursor.moveToFirst())
                {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int authorIndex = cursor.getColumnIndex(DBHelper.KEY_AUTHOR);
                    int dateIndex = cursor.getColumnIndex(DBHelper.KEY_Date);
                    do {
                        Log.d("mLog", "ID =" + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", author = " + cursor.getString(authorIndex) +
                                ", author = " + cursor.getString(dateIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");
                cursor.close(); // освобождение памяти
                break;
            /*case R.id.button3:
                database.delete(DBHelper.TABLE_PERSONS, null, null);
                break;*/
            case R.id.delete:
                if (ID.equalsIgnoreCase(""))
                {
                    break;
                }
                int delCount = database.delete(DBHelper.TABLE_PERSONS,
                        DBHelper.KEY_ID + "= " + ID, null);
                Log.d("mLog", "Удалено строк = " + delCount);
            case R.id.update:
                if (ID.equalsIgnoreCase(""))
                {
                    break;
                }
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_AUTHOR, author);
                contentValues.put(DBHelper.KEY_Date, date);
                int updCount = database.update(DBHelper.TABLE_PERSONS,
                        contentValues, DBHelper.KEY_ID + "= ?", new String[] {ID});
                Log.d("mLog", "Обновлено строк = " + updCount);
        }
        dbHelper.close(); // закрываем соединение с БД
    }


}