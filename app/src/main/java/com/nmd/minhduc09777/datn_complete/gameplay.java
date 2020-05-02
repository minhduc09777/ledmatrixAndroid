package com.nmd.minhduc09777.datn_complete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class gameplay extends AppCompatActivity {

    WifiManager w;
    private AsyncTask<Void, Void, Void> async_udp;
    InetAddress server_ip;
    int server_port = 2392;
    int tt_cp=0;
    String b = " ";
    Button _btntren,_btntrai,_btnphai,_btnduoi,_btnok;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        w = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!w.isWifiEnabled()) {
            Toast.makeText(gameplay.this,"switching ON wifi",Toast.LENGTH_LONG).show();
            w.setWifiEnabled(true);
        } else {
            Toast.makeText(gameplay.this,"Its already ON ",Toast.LENGTH_LONG).show();
        }
        _btnduoi=(Button) findViewById(R.id.btnxuong);
        _btntren=(Button) findViewById(R.id.btnlen);
        _btntrai=(Button) findViewById(R.id.btntrai);
        _btnphai=(Button) findViewById(R.id.btnphai);
        _btnok=(Button) findViewById(R.id.btnok);
        for(int i=b.length();i<64;i++){
            b=b+" ";
        }
        _btntren.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                 if(tt_cp==1) runUdpServer("t100000000000000"+b);
                 else if(tt_cp==2) runUdpServer("s100000000000000"+b);
                 else if(tt_cp==3) runUdpServer("k100000000000000"+b);
                 return false;
            }
        });
        _btnduoi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(tt_cp==1) runUdpServer("t200000000000000"+b);
                else if(tt_cp==2) runUdpServer("s200000000000000"+b);
                else if(tt_cp==3) runUdpServer("k200000000000000"+b);
                return false;
            }
        });
        _btntrai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(tt_cp==1) runUdpServer("t400000000000000"+b);
                else if(tt_cp==2) runUdpServer("s400000000000000"+b);
                else if(tt_cp==3) runUdpServer("k400000000000000"+b);
                return false;
            }
        });
        _btnphai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(tt_cp==1) runUdpServer("t300000000000000"+b);
                else if(tt_cp==2) runUdpServer("s300000000000000"+b);
                else if(tt_cp==3) runUdpServer("k300000000000000"+b);
                return false;
            }
        });
        _btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tt_cp==1) runUdpServer("t500000000000000"+b);
                else if(tt_cp==2) runUdpServer("s500000000000000"+b);
                else if(tt_cp==3) runUdpServer("k500000000000000"+b);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.clear1:
                tt_cp=0;
                runUdpServer("c000000000000000"+b);
                break;
            case R.id.tetris:
                tt_cp=1;
                runUdpServer("t000000000000000"+b);
                break;
            case R.id.snake:
                tt_cp=2;
                runUdpServer("s100000000000000"+b);
                break;
            case R.id.tank:
                tt_cp=3;
                runUdpServer("k000000000000000"+b);
                break;
        }
        return super.onOptionsItemSelected(item);
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
