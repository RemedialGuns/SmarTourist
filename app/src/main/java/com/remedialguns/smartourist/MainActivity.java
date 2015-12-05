package com.remedialguns.smartourist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //public String[] answers = {"", "", "", "", "", ""};
    public int[] CheckBoxAnswers = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public String gender = "";
    public int age = 0;
    public String country = "";
    Socket miCliente;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public void CheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.Random:
                if (checked) {
                    CheckBoxAnswers[0] = 1;
                } else {
                    CheckBoxAnswers[0] = 0;
                }
                break;
            case R.id.Museums:
                if (checked) {
                    CheckBoxAnswers[1] = 1;
                } else {
                    CheckBoxAnswers[1] = 0;
                }
                break;
            case R.id.Sports:
                if (checked) {
                    CheckBoxAnswers[2] = 1;
                } else {
                    CheckBoxAnswers[2] = 0;
                }
                break;
            case R.id.Concerts:
                if (checked) {
                    CheckBoxAnswers[3] = 1;
                } else {
                    CheckBoxAnswers[3] = 0;
                }
                break;
            case R.id.Disco:
                if (checked) {
                    CheckBoxAnswers[4] = 1;
                } else {
                    CheckBoxAnswers[4] = 0;
                }
                break;
            case R.id.Bars:
                if (checked) {
                    CheckBoxAnswers[5] = 1;
                } else {
                    CheckBoxAnswers[5] = 0;
                }
                break;
            case R.id.Movies:
                if (checked) {
                    CheckBoxAnswers[6] = 1;
                } else {
                    CheckBoxAnswers[6] = 0;
                }
                break;
            case R.id.Mall:
                if (checked) {
                    CheckBoxAnswers[7] = 1;
                } else {
                    CheckBoxAnswers[7] = 0;
                }
                break;
            case R.id.CulturalEvents:
                if (checked) {
                    CheckBoxAnswers[8] = 1;
                } else {
                    CheckBoxAnswers[8] = 0;
                }
                break;

            case R.id.Bowling:
                if (checked) {
                    CheckBoxAnswers[9] = 1;
                } else {
                    CheckBoxAnswers[9] = 0;
                }
                break;
            case R.id.VideoGames:
                if (checked) {
                    CheckBoxAnswers[10] = 1;
                } else {
                    CheckBoxAnswers[10] = 0;
                }
                break;
            case R.id.AmusementParks:
                if (checked) {
                    CheckBoxAnswers[11] = 1;
                } else {
                    CheckBoxAnswers[11] = 0;
                }
                break;
            case R.id.Libraries:
                if (checked) {
                    CheckBoxAnswers[12] = 1;
                } else {
                    CheckBoxAnswers[12] = 0;
                }
                break;
            case R.id.ReligiousPlaces:
                if (checked) {
                    CheckBoxAnswers[13] = 1;
                } else {
                    CheckBoxAnswers[13] = 0;
                }
                break;
            case R.id.RuralNatural:
                if (checked) {
                    CheckBoxAnswers[14] = 1;
                } else {
                    CheckBoxAnswers[14] = 0;
                }
                break;
            case R.id.Camping:
                if (checked) {
                    CheckBoxAnswers[15] = 1;
                } else {
                    CheckBoxAnswers[15] = 0;
                }
                break;
            case R.id.HistoricPlaces:
                if (checked) {
                    CheckBoxAnswers[16] = 1;
                } else {
                    CheckBoxAnswers[16] = 0;
                }
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Male:
                if (checked) {
                    gender = "Female";
                }
            case R.id.Female:
                if (checked) {
                    gender = "Male";
                }
        }
    }

    public void saveText() {
        EditText a1 = (EditText) findViewById(R.id.a1);//age
        EditText a3 = (EditText) findViewById(R.id.a3);
        age = Integer.parseInt(a1.getText().toString());
        country = a3.getText().toString();

    }

    public void next(View view) {
        Button aux = (Button) findViewById(R.id.NextButton);
        if (filled()) {
            aux.setClickable(true);
            enableStrictMode();
            Connect();
          //  Snd_txt_Msg("hola mundo");
            // Snd_Msg(new Message());
            //Snd_Msg(new Mensaje_data1(CheckBoxAnswers,gender,age,country));
            Intent real = new Intent(view.getContext(), RealMainActivity.class);
            startActivity(real);
        } else {

        }
    }

    public void enableStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public boolean filled() {
        saveText();
        int answersCounter = 0;
        for (int i = 0; i < CheckBoxAnswers.length; i++) {//check at least one place selected
            if (CheckBoxAnswers[i] == 1) {
                answersCounter++;
                break;
            }
        }
        if (gender != "") {//check gender is selected
            answersCounter++;
        }
        if (country != "") {//check country input
            answersCounter++;
        }
        if (age > 0) {//check age input
            answersCounter++;
        }

        if (answersCounter == 4) {
            return true;
        } else {
            return false;
        }

    }


    //Conectamos
    public boolean Connect() {
        //Obtengo datos ingresados en campos
        String IP = "192.168.0.13";
        int PORT = 5555;

        try {//creamos sockets con los valores anteriores
            miCliente = new Socket(IP, PORT);
            //Accedo a flujo de salida
            oos = new ObjectOutputStream(miCliente.getOutputStream());

            oos.writeObject(CheckBoxAnswers);
            oos.writeObject(gender);
            oos.writeObject(age);
            oos.writeObject(country);
             //Object resp = ois.readObject();

            Log.d("Mensaje recibido","");
            //si nos conectamos
            if (miCliente.isConnected() == true) {


                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //Si hubo algun error mostrmos error
            //txtstatus.setTextColor(Color.RED);
            //txtstatus.setText(" !!! ERROR  !!!");
            Log.e("Error connect()", "" + e);
            return false;
        }
    }

}



