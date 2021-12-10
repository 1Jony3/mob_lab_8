# mob_lab_8
#  **Разработка **
## **Добавление элементов в activity_main.xml**
- [X] Вставить EditText тип textMultiLine
- [X] Добавить поле TextView
- [X] Добавить кнопки для получить данные и сохранить данные
##  **Добавление кода**

- [X] Добавить обработчики кнопок
```
        <Button
            android:id="@+id/get"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:layout_marginRight="10dp"
            android:onClick="Get"
            android:text="Get"/>

        <Button
            android:id="@+id/save"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:layout_marginRight="10dp"
            android:onClick="Save"
            android:text="Save"/>
```
- [X] Добавить функции onSaveInstanceState(Bundle outState) и onSaveInstanceState(Bundle outState).
```
        @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString(nameVariableKey, name);
        TextView nameView = (TextView) findViewById(R.id.save);
        outState.putString(textViewTexKey, nameView.getText().toString());
        super.onSaveInstanceState(outState);
    }
``` 
***Все работает***

[Информация по офрмлению README.md](https://github.com/GnuriaN/format-README.git)
