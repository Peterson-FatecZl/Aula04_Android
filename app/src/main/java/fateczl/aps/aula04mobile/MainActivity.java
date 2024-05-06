package fateczl.aps.aula04mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.Period;

public class MainActivity extends AppCompatActivity {
    private EditText etDiaNascimento;

    private EditText etMesNascimento;

    private EditText etAnoNascimento;

    private TextView tvIdade;

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

        etDiaNascimento = findViewById(R.id.etDiaNascimento);
        etMesNascimento = findViewById(R.id.etMesNascimento);
        etAnoNascimento = findViewById(R.id.etAnoNascimento);

        tvIdade = findViewById(R.id.tvIdade);

        Button btnCalcularIdade = findViewById(R.id.btnCalcula);

        btnCalcularIdade.setOnClickListener(op -> calcularIdade());
    }

    private void calcularIdade() {

        int DiaAtual = Integer.parseInt(etDiaNascimento.getText().toString());
        int MesAtual = Integer.parseInt(etMesNascimento.getText().toString());
        int AnoAtual = Integer.parseInt(etAnoNascimento.getText().toString());

        LocalDate dataDeNascimento = LocalDate.of(AnoAtual, MesAtual, DiaAtual);
        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataDeNascimento, dataAtual);
        int anosDecorridos = periodo.getYears();
        int mesesDecorridos = periodo.getMonths();
        int diasDecorridos = periodo.getDays();

        String msgIdade = getString(R.string.tvIdade)  + String.valueOf(diasDecorridos) + " " +  String.valueOf(mesesDecorridos) + "  " +  String.valueOf(anosDecorridos);

        tvIdade.setText(String.format("%s %d Anos, %d Meses, %d dias", getString(R.string.tvIdade),
                anosDecorridos, mesesDecorridos, diasDecorridos));

        etDiaNascimento.setText("");
        etMesNascimento.setText("");
        etAnoNascimento.setText("");

    }
}