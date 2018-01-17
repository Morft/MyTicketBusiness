package Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import myticket.myticketbusiness.R;
import myticket.myticketbusiness.activities.TicketConfigActivity;

public class RemoveQueueFragment extends DialogFragment {

    private String queueName;

    // Firebase
    private DatabaseReference mFirebaseDatabaseReference;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        queueName = getArguments().getString("Queue");

        builder.setView(inflater.inflate(R.layout.fragment_remove_queue, null))
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog

                        Log.e("Teste", queueName);
                        String providerId = "123456789";
                        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("Providers/" + providerId + "/services/" + queueName);
                        mFirebaseDatabaseReference.removeValue();

                        Intent intent = new Intent(getActivity(), TicketConfigActivity.class);
                        startActivity(intent);


                    }
                });

        return builder.create();
    }

}
