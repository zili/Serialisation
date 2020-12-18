package com.example.serialisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serialisation.model.Article;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static String path    = "MyList";
    final static String myTag   = "toto";
    List<Article> lst;

    EditText etCode;
    EditText etPU;
    EditText etLibelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCode = findViewById(R.id.etCode);
        etLibelle  = findViewById(R.id.etLibelle);
        etPU = findViewById(R.id.etPU);

        lst = new ArrayList<>();

        //Saisir(lst);

      /*  try {
            //Serialiser(lst);
            //Deserialiser();
        } catch (Exception e) {
            Toast.makeText(this, "Oops, Serializing Failed !", Toast.LENGTH_SHORT).show();
            Log.i(myTag, e.getMessage());
            e.printStackTrace();
        }*/


    }

    private void Deserialiser() throws IOException, ClassNotFoundException {
        List<Article> lst;
        FileInputStream fis = this.openFileInput(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        lst = (List<Article>) ois.readObject();
        ois.close();
        fis.close();
        for (Article art : lst){
            Log.i(myTag, art.toString());
        }
        Toast.makeText(this, "Ok, it's working", Toast.LENGTH_SHORT).show();
    }

    private void Serialiser(List<Article> lst) throws IOException {
        FileOutputStream fos    = this.openFileOutput(path, Context.MODE_PRIVATE);    //new FileOutputStream(path);
        ObjectOutputStream oos  = new ObjectOutputStream(fos);
        oos.writeObject(lst);
        oos.close();
        fos.close();
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        Log.i(myTag, "Ok, it's working !");
    }
    private void Saisir(List<Article> lst) {
        lst.add(new Article(1, "pain", (double) 2));
        lst.add(new Article(2, "thé", (double) 15));
        lst.add(new Article(3, "lait", (double) 8));
    }

    public void ajouter(View view) {

        Integer code = Integer.parseInt(etCode.getText().toString());
        String  libelle = etLibelle.getText().toString();
        Double  pu = Double.parseDouble(etPU.getText().toString());

        lst.add(new Article(code, libelle, pu));
        Log.i(myTag, libelle +" est bien ajouté !");


    }

    public void bnSerialiser(View view) {
        try {
            Serialiser(lst);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void BtnDeserialiser(View view) {
        try {
            Deserialiser();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}