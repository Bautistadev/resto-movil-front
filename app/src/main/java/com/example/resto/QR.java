package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QR extends AppCompatActivity {

    public static String code;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        this.image = findViewById(R.id.qrCode);
        BarcodeEncoder codigo = new BarcodeEncoder();
        try {
            Bitmap bitmap = codigo.encodeBitmap(code, BarcodeFormat.QR_CODE,750,750);
            image.setImageBitmap(bitmap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}