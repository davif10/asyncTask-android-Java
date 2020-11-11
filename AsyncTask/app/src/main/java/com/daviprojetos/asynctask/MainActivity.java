package com.daviprojetos.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar2);
    }

    public void iniciarAsyncTask(View view){
        //Exemplo: executar("Davi","Francisco","Maria","José");
        MyAsyncTask task = new MyAsyncTask();
        task.execute(10);


    }
    /*
    1 - parâmetro a ser passado para a classe/Void
    2- Tipo de valor que será utilizado para o progresso da tarefa
    3- Retorno após a tarefa finalizada
     */

    /*public void executar(String...strings){
        String nome = strings[0];
        System.out.println("EXECUTAR: "+nome);

    }*/
    class MyAsyncTask extends AsyncTask<Integer, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int numero = integers[0];
            for(int i= 0 ; i<numero;i++){
                publishProgress(i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }
}