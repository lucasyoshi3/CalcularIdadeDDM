package com.example.calcularidadeddm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText dia;
    private EditText mes;
    private EditText ano;
    private Button btnCalc;
    private TextView resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dia=findViewById(R.id.etDia);
        mes=findViewById(R.id.etMes);
        ano=findViewById(R.id.etAno);
        resp=findViewById(R.id.tvResp);
        btnCalc=findViewById(R.id.btnCalc);
    }

    public void calc(){
        int diaNasc=Integer.parseInt(dia.getText().toString());
        int mesNasc=Integer.parseInt(mes.getText().toString());
        int anoNasc=Integer.parseInt(ano.getText().toString());

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        int anoAtual = cal.get(Calendar.YEAR);
        int mesAtual = cal.get(Calendar.MONTH) + 1;
        int diaAtual = cal.get(Calendar.DAY_OF_MONTH);

        int idadeAnos = anoAtual - anoNasc;
        int idadeMeses = mesAtual - mesNasc;
        int idadeDias = diaAtual - diaNasc;

        if (idadeMeses < 0 || (idadeMeses == 0 && idadeDias < 0)) {
            idadeAnos--;
            idadeMeses += 12;
        }

        boolean bissexto = ((anoNasc % 4 == 0) && (anoNasc % 100 != 0)) || (anoNasc % 400 == 0);

        if (bissexto && mesAtual > 2) {
            idadeDias++;
        }

        String res=("Idade: " + idadeAnos + " anos, " + idadeMeses + " meses e " + idadeDias + " dias.");
        resp.setText(res);
    }
}