package com.example.resto.EntityDTO;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Message extends AlertDialog.Builder {

    public Message(Context context, String title, String Message) {
        super(context);
        this.setMessage(Message);
        this.setCancelable(false);
        this.setTitle(title);

    }

    public AlertDialog onlyOption(){
        this.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        this.create();
        return this.show();
    }
}
