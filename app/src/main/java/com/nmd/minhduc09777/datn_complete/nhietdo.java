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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class nhietdo extends AppCompatActivity {

    EditText _edithang,_editcot;
    Button _btnon,_btnoff;
    String b=" ";
    WifiManager w;
    private AsyncTask<Void, Void, Void> async_udp;
    InetAddress server_ip;
    int server_port = 2392;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhietdo);
        _editcot=(EditText) findViewById(R.id.editTextCot);
        _edithang=(EditText) findViewById(R.id.editTextHang);
        _btnon=(Button) findViewById(R.id.buttonOn);
        _btnoff=(Button) findViewById(R.id.buttonOf);
        w = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!w.isWifiEnabled()) {
            Toast.makeText(nhietdo.this,"switching ON wifi",Toast.LENGTH_LONG).show();
            w.setWifiEnabled(true);
        } else {
            Toast.makeText(nhietdo.this,"Its already ON ",Toast.LENGTH_LONG).show();
        }
        for(int i=b.length();i<64;i++){
            b=b+" ";
        }
        _btnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=_edithang.getText().toString().trim();
                String y=_editcot.getText().toString().trim();
                if(x.length()==2){
                    x="0"+x;
                }
                else if(x.length()==1){
                    x="00"+x;
                }
                if(x.isEmpty()==true){
                    x="000";
                }
                if(y.length()==2){
                    y="0"+y;
                }
                else if(y.length()==1){
                    y="00"+y;
                }
                if(y.isEmpty()==true){
                    y="000";
                }
                runUdpServer("nf1"+x+y+"0000000"+b);
            }
        });
        _btnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runUdpServer("nf0"+"0000000000000"+b);
            }
        });
    }
    public void runUdpServer(final String str2)
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
    }
}
