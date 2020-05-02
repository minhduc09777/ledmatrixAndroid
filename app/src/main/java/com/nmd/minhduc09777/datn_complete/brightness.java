package com.nmd.minhduc09777.datn_complete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class brightness extends AppCompatActivity {

    TextView txtdosang;
    SeekBar sbdosang;
    String ds="70";
    WifiManager w;
    String b = " ";
    private AsyncTask<Void, Void, Void> async_udp;
    InetAddress server_ip;
    int server_port = 2392;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        txtdosang =(TextView) findViewById(R.id.textViewdosang);
        sbdosang = (SeekBar) findViewById(R.id.seekBardosang);
        w = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!w.isWifiEnabled()) {
            Toast.makeText(brightness.this,"switching ON wifi",Toast.LENGTH_LONG).show();
            w.setWifiEnabled(true);
        } else {
            Toast.makeText(brightness.this,"Its already ON ",Toast.LENGTH_LONG).show();
        }
        sbdosang.setMax(99);
        for(int i=b.length();i<64;i++) {
            b = b + " ";
        }
        sbdosang.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtdosang.setText(progress+"");
                ds=progress+"";
                if (ds.length()==1)
                {
                    ds= '0'+ ds;
                }
                runUdpServer("nd"+ds+"000000000000"+b);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
                b1 = new byte[255];
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

        if (Build.VERSION.SDK_INT >= 11)
        {
            async_udp.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            async_udp.execute();
        }
    }
}
