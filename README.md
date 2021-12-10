# mob_lab_8
#  **Работа с базой данных SQLite "Каталог книг"**
## **Добавление элементов в activity_main.xml**
- [X] Вставить TextInputEditText для названия книги, автора и даты издания
- [X] Добавить кнопки 
```        
        <Button
            android:id="@+id/add"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="2dp"
            android:layout_marginStart="2dp"
            android:text="add"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/textDate" />

        <Button
            android:id="@+id/update"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="2dp"
            android:layout_marginStart="2dp"
            android:text="update"
            app:layout_constraintLeft_toRightOf="@+id/add"
            app:layout_constraintTop_toBottomOf="@+id/datePicker" />

        <Button
            android:id="@+id/delete"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="2dp"
            android:layout_marginStart="2dp"
            android:text="delete"
            app:layout_constraintLeft_toRightOf="@+id/save"
            app:layout_constraintTop_toBottomOf="@+id/datePicker" />

        <Button
            android:id="@+id/read"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginStart="2dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="2dp"
            android:text=">"
            app:layout_constraintLeft_toRightOf="@+id/save"
            app:layout_constraintTop_toBottomOf="@+id/delete" />
```
## **Пример интерфейса**
![Снимок экрана от 2021-12-10 11-48-16](https://user-images.githubusercontent.com/90905407/145573457-d3c881be-e6f7-40c9-9811-7937ecc699f2.png)
##  **Добавление кода**
- [X] Добавить DBHelper
```
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myBase";
    public static final String TABLE_Books = "books";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_Date = "date";

    public DBHelper(@Nullable Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_Books + "(" + KEY_ID + " integer primary key," +
                KEY_NAME + " text," + KEY_AUTHOR + " text," + KEY_Date + " text" + ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_Books);
        onCreate(db);
    }
    
``` 
- [X] Добавить обработчик кнопок
```
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
                database.insert(DBHelper.TABLE_Books, null, contentValues);
                break;
            case R.id.read:
                Cursor cursor = database.query(DBHelper.TABLE_Books, null,
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
                                ", date = " + cursor.getString(dateIndex));
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
                int delCount = database.delete(DBHelper.TABLE_Books,
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
                int updCount = database.update(DBHelper.TABLE_Books,
                        contentValues, DBHelper.KEY_ID + "= ?", new String[] {ID});
                Log.d("mLog", "Обновлено строк = " + updCount);
        }
        dbHelper.close(); // закрываем соединение с БД
    }
    
``` 
##  **База данных**
![Снимок экрана от 2021-12-10 11-40-45](https://user-images.githubusercontent.com/90905407/145573547-1cdc0135-8ea1-4188-93b5-0ef13507bbd8.png)

##  **Собрать APK-файл и залить его на гитхаб в корень проекта**
![Снимок экрана 2021-12-10 192953](https://user-images.githubusercontent.com/90905407/145574478-49061a45-da1c-4def-a955-4da714bb9963.png)


[Информация по офрмлению README.md](https://github.com/GnuriaN/format-README.git)
