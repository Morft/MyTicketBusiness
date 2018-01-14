package myticket.myticketbusiness.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import myticket.myticketbusiness.R;

public class TicketViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_viewer);

        Button back = (Button) this.findViewById(R.id.viewer_back);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicketCallerActivity.class);
                startActivity(intent);
            }
        });
    }
}
