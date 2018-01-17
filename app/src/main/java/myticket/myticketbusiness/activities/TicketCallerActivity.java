package myticket.myticketbusiness.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import myticket.myticketbusiness.R;

public class TicketCallerActivity extends AppCompatActivity {

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;

    private String providerName;
    private String providerId;
    private ArrayList<Service> services = new ArrayList<>();
    private long actualTicket;
    private long avgTime;
    private List<String> usersInLine;

    // RecycleView
    private RecyclerView recyclerView;
    private List<Service> result;
    private ServicesAdapter adapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ticket_caller);

        Button viewer = (Button) this.findViewById(R.id.caller_view_ticket);
        Button config = (Button) this.findViewById(R.id.caller_config_ticket);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        viewer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicketViewerActivity.class);
                startActivity(intent);
            }
        });

        config.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicketConfigActivity.class);
                startActivity(intent);
            }
        });


        mProgressBar = findViewById(R.id.indeterminateBar);
        mProgressBar.setVisibility(View.VISIBLE);

        // RecyclerView
        result = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        adapter = new ServicesAdapter(result);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        passTicket(position);
                        Log.i("Teste", "onClick " + position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        passTicket(position);
                        Log.i("Teste", "onLongClick " + position);
                    }
                })
        );


        providerId = "123456789";

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("Providers/" + providerId);
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                providerName = (String) dataSnapshot.child("name").getValue();

                for (DataSnapshot serviceSnapShot: dataSnapshot.child("services").getChildren()) {

                    String serviceName = serviceSnapShot.getKey();
                    avgTime = (long) serviceSnapShot.child("avg_time").getValue();
                    actualTicket = (long) serviceSnapShot.child("actual_ticket").getValue();

                    usersInLine = new ArrayList<>();
                    for (DataSnapshot userSnapshot: serviceSnapShot.child("tickets").getChildren())
                        usersInLine.add((String) userSnapshot.getValue());

                    Log.e("Queue", "ProviderName: " + providerName + ", AVGTime: " + avgTime + ", ActualTicket: " + actualTicket + ", UsersInLine: " + usersInLine);
                    Service newService = new Service(providerId, providerName, serviceName, actualTicket, avgTime, usersInLine);

                    boolean verifyIfExist = false;
                    for (int i = 0; i < result.size(); i++) {

                        if (result.get(i).getService().equals(newService.getService())) {
                            verifyIfExist = true;
                            result.set(i, newService);
                        }
                    }

                    if (!verifyIfExist)
                        result.add(newService);
                }
                mProgressBar.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    public void passTicket(int position) {
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("Providers/" + result.get(position).getProviderId() + "/services/" + result.get(position).getService() + "/actual_ticket");
        mFirebaseDatabaseReference.setValue(result.get(position).getActualTicket() + 1);
    }

}
