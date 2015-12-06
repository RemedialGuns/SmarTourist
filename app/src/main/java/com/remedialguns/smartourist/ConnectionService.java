package com.remedialguns.smartourist;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Locale;

public class ConnectionService extends Service {

    Socket s;
    PrintStream os;
    Socket miCliente;
    ObjectInputStream ois;

    private static final String TAG="com.remedialguns.smartourist";
    private final IBinder myBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public void sendProfileData(){
        //send profile data to server
    }
    Place[] PlacesToShow = new Place[10];
    public Place[] getPlaces(){


        //F̶a̶k̶e̶ ̶d̶a̶t̶a̶ Test data
        enableStrictMode();
        Connect();

        PlacesToShow[0]= new Place("MUSEO","Museo Nacional Agropecuario", 0.15, 0.4, 0.12);
        PlacesToShow[1]= new Place("MUSEO","Museo Arqueológico Junín",0.10, 0.78, 0.44);
        PlacesToShow[2]= new Place("MUSEO","Museo Botero", 0.2, 0.8, 0.08);
        PlacesToShow[3]= new Place("MUSEO","Museo de Zea", 0.3, 0.65, 0.23);
        PlacesToShow[4]= new Place("MUSEO","MUSEO DEL ORO", 0.13, 0.56, 0.12);
        PlacesToShow[5]= new Place("MUSEO","MUSEO DE ARTE COLONIAL", 0.3, 0.67, 0.14);
        PlacesToShow[6]= new Place("MUSEO","MUSEO HISTORICO DE LA POLICIA NACIONAL", 0.34, 0.3, 0.33);
        PlacesToShow[8]= new Place("MUSEO","MUSEO DE LOS NIÑOS", 0.05, 0.65, 0.03);
        PlacesToShow[7]= new Place("MUSEO","Museo Nacional", 0.15, 0.4, 0.12);
        PlacesToShow[9]= new Place("MUSEO","MUSEO MILITAR", 0.07, 0.5, 0.6);

        return PlacesToShow;
    }

    public void enableStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }


    //Conectamos
    public boolean Connect() {
        //Obtengo datos ingresados en campos
        String IP = "192.168.0.13";
        int PORT = 5555;

        try {//creamos sockets con los valores anteriores
            miCliente = new Socket(IP, PORT);
            //Accedo a flujo de salida
            ois = new ObjectInputStream(miCliente.getInputStream());

            Object[] aux;
            int aux2 = 0;
            Place aux3;
            // int tzise = (int)ois.readObject();
            for(int i = 0;i<12;i++) {
                aux = (Object[]) ois.readObject();
                //Object resp = ois.readObject();
                // miCliente.close();
                aux3  = new Place("Type", (String) aux[0], (double) aux[1], (double) aux[2], 1);
                aux2++;
                Log.d("Mensaje recibido", (String) aux3.getName());

            }
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

    public class LocalBinder extends Binder {
        ConnectionService getService(){
            return ConnectionService.this;
        }
    }
}