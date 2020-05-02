package com.nmd.minhduc09777.datn_complete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class effect extends AppCompatActivity {

  /*  Spinner spinnerNen,spinnerKhung;
    Switch dongmo,modong;
    int vtnen=0,vtkhung=0;
    String b=" ";
    WifiManager w;
    private AsyncTask<Void, Void, Void> async_udp;
    InetAddress server_ip;
    int server_port = 2392;
    @SuppressLint("WifiManagerLeak")*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect);
       /* w = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!w.isWifiEnabled()) {
            Toast.makeText(effect.this,"switching ON wifi",Toast.LENGTH_LONG).show();
            w.setWifiEnabled(true);
        } else {
            Toast.makeText(effect.this,"Its already ON ",Toast.LENGTH_LONG).show();
        }
        for(int i=b.length();i<64;i++){
            b=b+" ";
        }
        spinnerNen=(Spinner) findViewById(R.id.spinnerNen);
        dongmo=(Switch) findViewById(R.id.switchNen);
        spinnerKhung=(Spinner) findViewById(R.id.spinnerKhung);
        modong=(Switch) findViewById(R.id.switchKhung);
        final ArrayList<String> arrayKhung = new ArrayList<String>();
        arrayKhung.add("Khung1");
        arrayKhung.add("Khung2");
        final ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayKhung);
        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        final ArrayList<String> arrayNen = new ArrayList<String>();
        arrayNen.add("Pháo Hoa");
        arrayNen.add("matrix rain");
        arrayNen.add("Chord");
        arrayNen.add("Trái Tim");
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayNen);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerNen.setAdapter(arrayAdapter);
        spinnerNen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(DongHo1.this,arrayGio.get(position),Toast.LENGTH_SHORT).show();
                vtnen=position;
                dongmo.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerKhung.setAdapter(arrayAdapter1);
        spinnerKhung.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(DongHo1.this,arrayGio.get(position),Toast.LENGTH_SHORT).show();
                vtkhung=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dongmo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(vtnen==0){
                    if(isChecked==true){
                        runUdpServer("nb1"+"0000000000000"+b);
                    }
                    if(isChecked==false){
                        runUdpServer("nb2"+"0000000000000"+b);
                    }
                }
                if(vtnen==1){
                    if(isChecked==true){
                        runUdpServer("nb3"+"0000000000000"+b);
                    }
                    if(isChecked==false){
                        runUdpServer("nb4"+"0000000000000"+b);
                    }
                }
                if(vtnen==2){
                    if(isChecked==true){
                        runUdpServer("nb5"+"0000000000000"+b);
                    }
                    if(isChecked==false){
                        runUdpServer("nb6"+"0000000000000"+b);
                    }
                }
                if(vtnen==3){
                    if(isChecked==true){
                        runUdpServer("nb7"+"0000000000000"+b);
                    }
                    if(isChecked==false){
                        runUdpServer("nb8"+"0000000000000"+b);
                    }
                }
            }
        });

        modong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(vtkhung==0){
                    if(isChecked==true){
                        runUdpServer("nk1"+"0000000000000"+b);
                    }
                    if(isChecked==false){
                        runUdpServer("nk2"+"0000000000000"+b);
                    }
                }
                if(vtkhung==1){
                    if(isChecked==true){
                        runUdpServer("nk3"+"0000000000000"+b);
                    }
                    if(isChecked==false){
                        runUdpServer("nk4"+"0000000000000"+b);
                    }
                }
            }
        });*/

    }
  /*  public void runUdpServer(final String str2)
    {

        int x;
        WifiInfo info = w.getConnectionInfo();
        x = info.getIpAddress();
        String str1 = info.getMacAddress();
        try {
            server_ip = InetAddress.getByName("192.168.4.1"); // ip of THE OTHER DEVICE - NOT THE PHONE

        } catch (UnknownHostException e) {

        }
        async_udp = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                byte b1[];
                b1 = new byte[100];
                b1 = str2.getBytes();
                //DatagramPacket p1 = new DatagramPacket(b1, b1.length, server_ip, server_port);
                try {
                    //DatagramSocket s = new DatagramSocket(server_port, server_ip);
                    DatagramSocket s = new DatagramSocket(server_port);
                    s.connect(server_ip, server_port);

                    DatagramPacket p0 = new DatagramPacket(b1, b1.length, InetAddress.getByName("192.168.4.1"), server_port);
                    s.send(p0);
                    //The above two line can be used to send a packet - the other code is only to recieve

                    s.close();


                } catch (SocketException e)
                {
                    //status.append("Error creating socket");
                    //this doesnt work!
                } catch (IOException e)
                {
                    //status.append("Error recieving packet");
                    //this doesnt work!
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        };

        if (Build.VERSION.SDK_INT >= 11) {
            async_udp.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else {
            async_udp.execute();
        }
    }*/

}
