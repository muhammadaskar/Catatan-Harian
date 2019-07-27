package com.askar.catatanharian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.askar.catatanharian.Adapters.CatatanAdapter;
import com.askar.catatanharian.DB.AppDbProvider;
import com.askar.catatanharian.DB.CatatanDatabase;
import com.askar.catatanharian.DB.DAO.CatatanDao;
import com.askar.catatanharian.DB.Entity.Catatan;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatatanAdapter.ClickListener {

    RecyclerView recyclerView;
    CatatanAdapter catatanAdapter;
    List<Catatan> catatanList = new ArrayList<>();
    CatatanDao catatanDao;
    Catatan catatan;
    public final static String KEY = "kunci";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_layout);
        catatanDao = AppDbProvider.getInstance(this.getApplicationContext()).catatanDao();
        initsComponent();
    }

    @SuppressLint("WrongConstant")
    private void initsComponent() {
        try {
            catatanList = catatanDao.getAll();

            catatanAdapter = new CatatanAdapter(catatanList);
            catatanAdapter.setListener(this);
            recyclerView.setAdapter(catatanAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (RuntimeException re) {
            Toast.makeText(this, "Terjadi Kesalahan Pada " + re, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view, int position) {
        catatan = catatanDao.selectDetailCatatan(catatanList.get(position).getId());
        startActivity(new Intent(MainActivity.this, DetailCatatan.class).putExtra(KEY, this.catatan));
    }

    public void clickTambah(View view) {
        Intent i = new Intent(MainActivity.this, tambahData.class);
        startActivity(i);
    }
}
