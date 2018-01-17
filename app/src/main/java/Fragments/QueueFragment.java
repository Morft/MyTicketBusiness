package Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import myticket.myticketbusiness.R;


public class QueueFragment extends DialogFragment {

    private String queue;
    private String time;
    private EditText editText;


    // Firebase
    private DatabaseReference mFirebaseDatabaseReference;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_queue, null))
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        })
        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog

                editText = getDialog().findViewById(R.id.queue_name);
                queue = editText.getText().toString();
                time = getDialog().findViewById(R.id.time).toString();

                String providerId = "123456789";
                mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("Providers/" + providerId + "/services").child(queue);

                mFirebaseDatabaseReference.child("actual_ticket").setValue(0);
                mFirebaseDatabaseReference.child("avg_time").setValue(0);
                mFirebaseDatabaseReference.child("tickets").push().setValue("Admin");
            }
        });

        return builder.create();
    }


}
