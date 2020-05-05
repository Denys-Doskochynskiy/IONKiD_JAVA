package com.example.aba;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinerTest extends Activity {




        String[] data = {"Select gender", "Male", "Female"};

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_spiner_test);

            // адаптер
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setAdapter(adapter);
            // заголовок
            spinner.setPrompt("Title");
            // выделяем элемент
            spinner.setSelection(0);
            // устанавливаем обработчик нажатия
            spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    // показываем позиция нажатого элемента
                    if(position==1){
                        Toast.makeText(getBaseContext(), "Position = Male", Toast.LENGTH_SHORT).show();
                    }
                    else if(position==2){
                        Toast.makeText(getBaseContext(), "Position = Female", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getBaseContext(), "Position didn't selected " , Toast.LENGTH_SHORT).show();

                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
        }
    }
