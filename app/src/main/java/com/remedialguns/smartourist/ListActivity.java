package com.remedialguns.smartourist;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.remedialguns.smartourist.ConnectionService.LocalBinder;

import com.google.android.gms.location.places.Places;

public class ListActivity extends AppCompatActivity {

    ConnectionService tcpService;
    boolean isBound=false;
    //Place[] PlacesToShow=tcpService.getPlaces();
    Place[] PlacesToShow=new Place[10];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = new Intent(this, ConnectionService.class);
        bindService(i, myConnection, Context.BIND_AUTO_CREATE);
//        PlacesToShow=tcpService.getPlaces();


        if (isBound) {
            PlacesToShow = tcpService.getPlaces();

            //F̶a̶k̶e̶ ̶d̶a̶t̶a̶ Test data
//        PlacesToShow[0]= new Place("MUSEO","Museo Nacional Agropecuario", 0.15, 0.4, 0.12);
//        PlacesToShow[1]= new Place("MUSEO","Museo Arqueológico Junín",0.10, 0.78, 0.44);
//        PlacesToShow[2]= new Place("MUSEO","Museo Botero", 0.2, 0.8, 0.08);
//        PlacesToShow[3]= new Place("MUSEO","Museo de Zea", 0.3, 0.65, 0.23);
//        PlacesToShow[4]= new Place("MUSEO","MUSEO DEL ORO", 0.13, 0.56, 0.12);
//        PlacesToShow[5]= new Place("MUSEO","MUSEO DE ARTE COLONIAL", 0.3, 0.67, 0.14);
//        PlacesToShow[6]= new Place("MUSEO","MUSEO HISTORICO DE LA POLICIA NACIONAL", 0.34, 0.3, 0.33);
//        PlacesToShow[8]= new Place("MUSEO","MUSEO DE LOS NIÑOS", 0.05, 0.65, 0.03);
//        PlacesToShow[7]= new Place("MUSEO","Museo Nacional", 0.15, 0.4, 0.12);
//        PlacesToShow[9]= new Place("MUSEO","MUSEO MILITAR", 0.07, 0.5, 0.6);
            //Place[] PlacesToShow=tcpService.getPlaces();

            ListAdapter MyAdapter = new MyAdapter(this, PlacesToShow);

            ListView ListPlaces = (ListView) findViewById(R.id.MyList);

            ListPlaces.setAdapter(MyAdapter);

            ListPlaces.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Context context = view.getContext();
                            TextView textViewItem = ((TextView) view.findViewById(R.id.name));
                            String name = textViewItem.getText().toString();
                            TextView textViewItem2 = (TextView) findViewById(R.id.description);
                            String descripcion = textViewItem2.getText().toString();
                            Toast.makeText(context, "lugar: " + name + ", descripcion: " + descripcion, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
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

    private ServiceConnection myConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBinder binder = (LocalBinder) service;
            tcpService = binder.getService();
            PlacesToShow = tcpService.getPlaces();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };


}
