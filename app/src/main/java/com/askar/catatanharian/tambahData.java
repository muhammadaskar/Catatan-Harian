package com.askar.catatanharian;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.askar.catatanharian.DB.AppDbProvider;
import com.askar.catatanharian.DB.DAO.CatatanDao;
import com.askar.catatanharian.DB.Entity.Catatan;

public class tambahData extends AppCompatActivity {


    private EditText editTextNamaFile;
    private EditText bodyText;
    private CatatanDao catatanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        editTextNamaFile = findViewById(R.id.edt_nama_file);
        bodyText = findViewById(R.id.edt_catatan);

        catatanDao = AppDbProvider.getInstance(getApplicationContext()).catatanDao();

    }

    public void clickSimpanTambah(View view) {
        Catatan catatan;
        String judul = editTextNamaFile.getText().toString();
        String body = bodyText.getText().toString();
        catatan = new Catatan(judul, body);
        insertData(catatan);

        Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(tambahData.this, MainActivity.class));
        }

    @SuppressLint("StaticFileLeak")
    private void insertData(final Catatan catatan1){
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return catatanDao.insertAll(catatan1);
            }

            @Override
            protected void onPostExecute(Long status){
                Toast.makeText(tambahData.this, "Status Row" +status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    }

