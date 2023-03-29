package pereira.otavio.evelyn.email;

// Importação das classes necessárias
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Definição da classe MainActivity que herda de AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // Método onCreate que é chamado quando a atividade é criada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Busca o botão com id btnEnviar no layout activity_main
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        //Definição da ação do click do botão
        btnEnviar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick (View v) {
                //Obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                // Obtém o assunto digitado pelo usuário
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                // Obtém o texto digitado pelo usuário
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                // Cria uma nova intenção para enviar um e-mail
                Intent i = new Intent(Intent.ACTION_SENDTO);
                // Define o protocolo URI para enviar um e-mail
                i.setData(Uri.parse("mailto:"));
                // Define o endereço de e-mail para o qual o e-mail será enviado
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL,emails);
                // Define o assunto do e-mail
                i.putExtra(Intent.EXTRA_SUBJECT,assunto);
                // Define o corpo do e-mail
                i.putExtra(Intent.EXTRA_TEXT,texto);
                // Tenta iniciar uma atividade para enviar o e-mail
                try {
                    startActivity(Intent.createChooser(i,"Escolha o APP"));
                }
                catch(ActivityNotFoundException e) {
                    // Se não houver nenhum aplicativo de e-mail instalado, exibe uma mensagem de erro
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}