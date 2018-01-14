package myticket.myticketbusiness.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import myticket.myticketbusiness.R;

public class TicketConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_config);

        Button back = (Button) this.findViewById(R.id.config_back);
        Button add = (Button) this.findViewById(R.id.config_add);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicketCallerActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // Quando se clicar no botao add, faz alguma coisa.
            }
        });
    }
}
