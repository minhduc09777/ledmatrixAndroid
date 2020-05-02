package com.nmd.minhduc09777.datn_complete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import static  com.nmd.minhduc09777.datn_complete.PaintView.path;
import static  com.nmd.minhduc09777.datn_complete.PaintView.brush;

public class paint_acti extends AppCompatActivity {
    WifiManager w;
    private AsyncTask<Void, Void, Void> async_udp;
    InetAddress server_ip;
    int server_port = 2392;
    String b=" ";
    String Rp="255",Gp="000",Bp="000";
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=b.length();i<64;i++){
            b=b+" ";
        }
        final PaintView paintView = new PaintView(this);
        setContentView(paintView);
        w = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!w.isWifiEnabled()) {
            Toast.makeText(paint_acti.this,"switching ON wifi",Toast.LENGTH_LONG).show();
            w.setWifiEnabled(true);
        } else {
            Toast.makeText(paint_acti.this,"Its already ON ",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.clear:
                path.reset();
                runUdpServer("c000000000000000"+b);
                brush.setColor(Color.RED);
                Rp="255";Gp="000";Bp="000";
                break;
            case R.id.red:
                brush.setColor(Color.RED);
                Rp="255";Gp="000";Bp="000";
                break;
            case R.id.green:
                brush.setColor(Color.GREEN);
                Rp="000";Gp="255";Bp="000";
                break;
            case R.id.blue:
                brush.setColor(Color.BLUE);
                Rp="000";Gp="000";Bp="255";
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointX =(int) event.getX();
        int pointY =(int) event.getY();
        int x=(int)(pointX/5.5);
        int y=(int)(pointY/5);
        x=x;
        y=256-y;
        String x1= x+"";
        String y1= y+"";
        if (x1.length()==1){
            x1="00"+x1;
        }
        else if (x1.length()==2){
            x1='0'+x1;
        }
        if (y1.length()==1){
            y1="00"+y1;
        }
        else if (y1.length()==2){
            y1='0'+y1;
        }
        runUdpServer('p'+x1+y1+Rp+Gp+Bp+b);
        return false;
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
