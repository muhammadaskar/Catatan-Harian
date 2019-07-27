package com.askar.catatanharian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.askar.catatanharian.DB.AppDbProvider;
import com.askar.catatanharian.DB.DAO.CatatanDao;
import com.askar.catatanharian.DB.Entity.Catatan;

import static com.askar.catatanharian.MainActivity.KEY;

public class DetailCatatan extends AppCompatActivity {

    private EditText editTextJudul,
            editTextBody;
    private Button btnUpdate,
            btnDelete;
    CatatanDao catatanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catatan);

        editTextJudul = findViewById(R.id.edt_nama_file_detail);
        editTextBody = findViewById(R.id.edt_catatan_detail);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        catatanDao = AppDbProvider.getInstance(this.getApplicationContext()).catatanDao();
        getDetailData();
    }

    private void getDetailData() {
        final Catatan catatan = (Catatan) getIntent().getParcelableExtra(KEY);
        if (catatan != null) {
            editTextJudul.setText(catatan.getNamaFile());
            editTextBody.setText(catatan.getTextBody());

            final Catatan finalCatatan = catatan;

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    catatan.setNamaFile(editTextJudul.getText().toString());
                    catatan.setTextBody(editTextBody.getText().toString());
                    updateCatatan(finalCatatan);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteDialog(view, "Hapus Catatan",
                            "Apakah Anda Yakin", finalCatatan);
                }
            });
        }


    }

    @SuppressLint("StaticFieldLeak")
    private void updateCatatan(final Catatan catatan) {
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                return catatanDao.update(catatan);
            }

            @Override
            protected void onPostExecute(Integer status) {
                Toast.makeText(DetailCatatan.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void deleteCatatan(final Catatan catatan) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return catatanDao.delete(catatan);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                Toast.makeText(DetailCatatan.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailCatatan.this, MainActivity.class));
            }

        }.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DetailCatatan.this, MainActivity.class));
        finish();
    }

    private void deleteDialog(View mView, String title,CharSequence message, final Catatan catatan){
        new AlertDialog.Builder(mView.getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteCatatan(catatan);
                    }
                })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).show();

    }


}
