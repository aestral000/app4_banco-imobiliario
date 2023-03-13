package br.edu.ifsp.dmo5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmo5.model.CreditCard;
import br.edu.ifsp.dmo5.model.StarBank;
import br.edu.ifsp.dmo5.model.exception.DebitOperationException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input_text;
    private Button button_M;
    private Button button_C;
    private Button button_K;
    private Button button_D;
    private Button button_T;
    private Button button_player_1;
    private Button button_player_2;
    private Button button_player_3;
    private Button button_player_4;
    private Button button_player_5;
    private Button button_player_6;
    private TextView text_out;

    private StarBank starBank;

    private CreditCard actionCard = null;
    private CreditCard actionCardTransfer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starBank = StarBank.getInstance();
        starBank.starCreditCards();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        input_text = findViewById(R.id.input_text);

        button_M = findViewById(R.id.button_M);
        button_C = findViewById(R.id.button_C);
        button_D = findViewById(R.id.button_D);
        button_K = findViewById(R.id.button_K);
        button_T = findViewById(R.id.button_T);

        button_player_1 = findViewById(R.id.player_1);
        button_player_2 = findViewById(R.id.player_2);
        button_player_3 = findViewById(R.id.player_3);
        button_player_4 = findViewById(R.id.player_4);
        button_player_5 = findViewById(R.id.player_5);
        button_player_6 = findViewById(R.id.player_6);

        button_M.setOnClickListener(this);
        button_C.setOnClickListener(this);
        button_D.setOnClickListener(this);
        button_K.setOnClickListener(this);
        button_T.setOnClickListener(this);

        button_player_1.setOnClickListener(this);
        button_player_2.setOnClickListener(this);
        button_player_3.setOnClickListener(this);
        button_player_4.setOnClickListener(this);
        button_player_5.setOnClickListener(this);
        button_player_6.setOnClickListener(this);

        text_out = findViewById(R.id.text_out);

    }

    @Override
    public void onClick(View view) {

        if (view == button_M) {
            input_text.setText(String.format("%.2f", Double.parseDouble(String.valueOf(input_text.getText())) * 1000));
        }
        if (view == button_K) {
            input_text.setText(String.format("%.2f", Double.parseDouble(String.valueOf(input_text.getText())) * 100));
        }

        if (view == button_player_1) {

            try{
                if(actionCard == null) {
                    actionCard = starBank.getCreditCardArray().get(0);
                    text_out.setText(String.format("R$%.2f", actionCard.getBalance()));
                }
                actionCardTransfer = starBank.getCreditCardArray().get(0);
            }catch (NullPointerException ex){
                Toast.makeText(this, "Jogador 1 Inexistente", Toast.LENGTH_SHORT).show();
            }
        }

        if (view == button_player_2) {
            try{
                if(actionCard == null) {
                    actionCard = starBank.getCreditCardArray().get(1);
                    text_out.setText(String.format("R$%.2f", actionCard.getBalance()));
                }
                actionCardTransfer = starBank.getCreditCardArray().get(1);
            }catch (NullPointerException ex){
                Toast.makeText(this, "Jogador 2 Inexistente", Toast.LENGTH_SHORT).show();
            }
        }

        if (view == button_player_3) {
            try{
                if(actionCard == null) {
                    actionCard = starBank.getCreditCardArray().get(2);
                    text_out.setText(String.format("R$%.2f", actionCard.getBalance()));
                }
                actionCardTransfer = starBank.getCreditCardArray().get(2);
            }catch (NullPointerException ex){
                Toast.makeText(this, "Jogador 3 Inexistente", Toast.LENGTH_SHORT).show();
            }
        }

        if (view == button_player_4) {
            try{
                if(actionCard == null){
                    actionCard = starBank.getCreditCardArray().get(3);
                    text_out.setText(String.format("R$%.2f", actionCard.getBalance()));
                }
                actionCardTransfer = starBank.getCreditCardArray().get(3);
            }catch (NullPointerException ex){
                Toast.makeText(this, "Jogador 4 Inexistente", Toast.LENGTH_SHORT).show();
            }
        }

        if (view == button_player_5) {
            try{
                if(actionCard == null) {
                    actionCard = starBank.getCreditCardArray().get(4);
                    text_out.setText(String.format("R$%.2f", actionCard.getBalance()));
                }
                actionCardTransfer = starBank.getCreditCardArray().get(4);
            }catch (NullPointerException ex){
                Toast.makeText(this, "Jogador 5 Inexistente", Toast.LENGTH_SHORT).show();
            }
        }

        if (view == button_player_6) {
            try{
                if(actionCard == null) {
                    actionCard = starBank.getCreditCardArray().get(5);
                    text_out.setText(String.format("R$%.2f", actionCard.getBalance()));
                }
                actionCardTransfer = starBank.getCreditCardArray().get(5);
            }catch (NullPointerException ex){
                Toast.makeText(this, "Jogador 6 Inexistente", Toast.LENGTH_SHORT).show();
            }
        }

        if (actionCard != null) {
            if (view == button_D) {
                try {
                    starBank.pay(actionCard, Double.parseDouble(String.valueOf(input_text.getText())));
                    Toast.makeText(this, "Pagamento realizado com sucesso", Toast.LENGTH_SHORT).show();
                } catch (DebitOperationException ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }finally {
                    actionCard = null;
                    actionCardTransfer = null;
                    text_out.setText("");
                    input_text.setText("");
                }
            }

            if (view == button_C) {
                starBank.receive(actionCard, Double.parseDouble(String.valueOf(input_text.getText())));
                Toast.makeText(this, "Recebimento realizado com sucesso", Toast.LENGTH_SHORT).show();
                actionCard = null;
                actionCardTransfer = null;
                text_out.setText("");
                input_text.setText("");
            }

            if (actionCardTransfer != null) {

                if (view == button_T) {
                    try {
                        starBank.transfer(actionCard, actionCardTransfer, Double.parseDouble(String.valueOf(input_text.getText())));
                        Toast.makeText(this, "Transferência realizado com sucesso", Toast.LENGTH_SHORT).show();
                    } catch (DebitOperationException ex) {
                        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }finally {
                        actionCard = null;
                        actionCardTransfer = null;
                        text_out.setText("");
                        input_text.setText("");
                    }
                }

            }
        }
    }
}