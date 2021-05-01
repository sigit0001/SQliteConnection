package com.example.sqliteconnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqliteconnection.adapter.TemanAdapter;
import com.example.sqliteconnection.database.DBController;
import com.example.sqliteconnection.database.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    //    untuk menampung data teman
    private ArrayList<Teman> temanArrayList;
    DBController controller = new DBController(this);
    String id,nm,tlp;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        bacaData();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TemanBaru.class);
                startActivity(intent);
            }
        });

    }

    public void bacaData(){
        ArrayList<HashMap<String,String>> daftarTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();
//        memindahkan dari hasil query kedalam teman
        for (int i = 0; i<daftarTeman.size();i++){
            Teman teman = new Teman();

            teman.setId(daftarTeman.get(i).get("id").toString());
            teman.setId(daftarTeman.get(i).get("nama").toString());
            teman.setId(daftarTeman.get(i).get("telpon").toString());

//          pindahkan dari teman ke dalam Arraylist teman di adapter
            temanArrayList.add(teman);

        }

    }

}