package com.example.to_do_list;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> itens;
    private ArrayAdapter<String> itensAdapter;
    private ListView listView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        itens = new ArrayList<>();

        itensAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itens);
        listView.setAdapter(itensAdapter);
        setUpListViewListener();


    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Iten Removido", Toast.LENGTH_LONG).show();

                itens.remove(i);
                itensAdapter.notifyDataSetChanged();

                return true;
            }
        });
    }

    private void addItem(View view) {

        EditText input = findViewById(R.id.editTextText);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))) {
            itensAdapter.add(itemText);
            input.setText("");

        } else {
            Toast.makeText(getApplicationContext(), "Please enter text...", Toast.LENGTH_LONG).show();
        }

    }
}