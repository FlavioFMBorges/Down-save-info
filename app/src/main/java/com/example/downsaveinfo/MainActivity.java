package com.example.downsaveinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    // 1 - variável EditText no Java para captura do conteúdo inserido pelo usuário (associada ao componente EditText "editTextXML" no Layout)
    private EditText mensagemInserida;

    // 2 - String contendo a mensagem a ser enviada.
    private String mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3 - Associando a variável mensagemInserida do Java com o componente EditText do arquivoXML
        mensagemInserida = findViewById(R.id.editTextXML);

    }

    // 4 - Criando o método para envio da mensagem para a próxima tela
    public void disparoNovaTela(View v)  {

        // 4.1 - atribuição do valor ditado pelo usuário ao campo do EditTextXML para a variável mensagem
        mensagem = mensagemInserida.getText().toString();

        // 4.2 - Criação do Intent para chamada da segunda tela com envio da mensagem
        Intent myIntent = new Intent(this, Tela2.class);

        // 4.3 - Uso do método putExtra para envio da mensagem.
        myIntent.putExtra("mensagemEnviada", mensagem);

        startActivity(myIntent);

    }

    // 5 - Método para download do conteudo XML do site, utilizando a classe httURLCnnection
    private String (String theUrl){
        try{
            //cria uma instancia da classe URL com a url que será utilizada na conexão.
            URL myUrl = new URL(theUrl);

            //abre a conexão
            HttpURLConnection myconnection = (HttpURLConnection) myUrl.openConnection();

            //verifica se foi bem sucedida a conexão e exibe no layout; se sim código no 200
            int response = myconnection.getResponseCode();
            Log.d("Download", "the response code is" + response);

            //Cria a variável "data" para receber a stream de bytes.
            InputStream data = myconnection.getInputStream();

            //utiliza a classe InputStreamReader para converter bytes em chars.
            InputStreamReader caracteres = new InputStreamReader(data);

            //criação de um Array de char para leitura de 500 em 500 caracteres
            char[] InputBuffer = new char[500];

            //Criação de uma instância StringBuilder para formar a String final de interesse
            StringBuilder tempBuffer = new StringBuilder();

            //variável para contagem de número de caracteres lidos dentro de um laço while para formação da String
            int charRead;

            //laço de formação e leitura da string
            while (true){
                //lê os caracteres com tamanho máximo de InputBuffer(500) e informa o número lido
                charRead = caracteres.read(InputBuffer);

                // se -1, não há caracteres lidos e sai do laço.
                if (charRead <= 0) {
                    break;
                }
                tempBuffer.append(String.copyValueOf(InputBuffer,0, charRead));
            }
            return tempBuffer.toString;
        }
        // com os métodos open.Connection(), getResponsecode() e getInputStream() podem lançar exceções de 10
        // pois interagem com recursos externos do aplicativo, precisamos de um bloco catch para pegar eventuais exceções.
        catch (IOException e);
        {
            Log.d("Download", "Io Expection durante a conexão" + e.getmessage());
        }
        //nesse caso o método retorna null.
        return null;
    }

    //Precisamos dar permissão de acesso a internet ao arquivo manifest
    // <uses-permission android:name="android.pemission.INTERNET"></uses.permission>



}