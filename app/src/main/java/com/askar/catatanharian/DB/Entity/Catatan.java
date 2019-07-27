package com.askar.catatanharian.DB.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Catatan implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "nama_file")
    private String namaFile;

    @ColumnInfo(name = "text_body")
    private String textBody;

    public Catatan() {
    }

    public Catatan(String namaFile, String textBody) {
        this.namaFile = namaFile;
        this.textBody = textBody;
    }

    protected Catatan(Parcel in) {
        id = in.readInt();
        namaFile = in.readString();
        textBody = in.readString();
    }

    public static final Creator<Catatan> CREATOR = new Creator<Catatan>() {
        @Override
        public Catatan createFromParcel(Parcel in) {
            return new Catatan(in);
        }

        @Override
        public Catatan[] newArray(int size) {
            return new Catatan[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(namaFile);
        parcel.writeString(textBody);
    }
}
