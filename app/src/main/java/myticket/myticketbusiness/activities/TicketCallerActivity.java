package myticket.myticketbusiness.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import myticket.myticketbusiness.R;

public class TicketCallerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ticket_caller);

        Button viewer = (Button) this.findViewById(R.id.caller_view_ticket);
        Button config = (Button) this.findViewById(R.id.caller_config_ticket);

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

    }


}
