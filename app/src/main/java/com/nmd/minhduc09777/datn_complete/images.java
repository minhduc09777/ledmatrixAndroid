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
import android.widget.Button;
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

public class images extends AppCompatActivity {

    Spinner spinneranh, spinneranhdong,_spinnervt;
    String b=" ";
    Button _clearall;
    String vthtanh;
    Switch swanh,swanhdong;
    boolean gamma=false;
    int vtanh=1,vtanhdong=1;
    WifiManager w;
    private AsyncTask<Void, Void, Void> async_udp;
    InetAddress server_ip;
    int server_port = 2392;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        w = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!w.isWifiEnabled()) {
            Toast.makeText(images.this,"switching ON wifi",Toast.LENGTH_LONG).show();
            w.setWifiEnabled(true);
        } else {
            Toast.makeText(images.this,"Its already ON ",Toast.LENGTH_LONG).show();
        }
        for(int i=b.length();i<64;i++){
            b=b+" ";
        }
      //  okgamma=(Switch) findViewById(R.id.switch3);
        swanhdong=(Switch) findViewById(R.id.switch2);
        swanh=(Switch) findViewById(R.id.switch4);
        _clearall = (Button) findViewById(R.id.clearall);
        spinneranh = (Spinner) findViewById(R.id.spinnerAnh);
        spinneranhdong = (Spinner) findViewById(R.id.spinnerAnhdong);
        _spinnervt = (Spinner) findViewById(R.id.spinnervt);
        final ArrayList<String> array = new ArrayList<String>();
        array.add("vị trí 0");
        array.add("vị trí 1");
        array.add("vị trí 2");
        array.add("vị trí 3");
        array.add("vị trí 4");
        array.add("vị trí 5");
        array.add("vị trí 6");
        array.add("vị trí 7");
        array.add("vị trí 8");
        array.add("vị trí 9");
        array.add("vị trí 10");
        array.add("vị trí 11");
        array.add("vị trí 12");
        array.add("vị trí 13");
        array.add("vị trí 14");
        array.add("vị trí 15");
        final ArrayAdapter arrayAdaptervt = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,array);
        arrayAdaptervt.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        _spinnervt.setAdapter(arrayAdaptervt);

        final ArrayList<String> arrayanh = new ArrayList<String>();
        arrayanh.add("Ảnh 1");
        arrayanh.add("Ảnh 2");
        arrayanh.add("Ảnh 3");
        arrayanh.add("Ảnh 4");
        arrayanh.add("Ảnh 5");
        arrayanh.add("Ảnh 6");
        arrayanh.add("Ảnh 7");
        arrayanh.add("Ảnh 8");
        arrayanh.add("Ảnh 9");

        final ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayanh);
        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        final ArrayList<String> arraydong = new ArrayList<String>();
        arraydong.add("ảnh động 1");
        arraydong.add("ảnh động 2");
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arraydong);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinneranh.setAdapter(arrayAdapter);
        spinneranh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vtanhdong=position+1;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinneranhdong.setAdapter(arrayAdapter1);
        spinneranhdong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vtanh=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       /* okgamma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gamma=isChecked;
                int vthtanh1=_spinnervt.getSelectedItemPosition();
                vthtanh=vthtanh1+"";
                if(vthtanh.length()==1){
                    vthtanh="0"+vthtanh;
                }
                String vta=vtanh+"";
                if(vta.length()==1){
                    vta="0"+vta;
                }
                if(gamma==true){
                    runUdpServer("na"+vta+"g"+vthtanh+"000000000"+b);
                }
                else{
                    runUdpServer("na"+vta+"0"+vthtanh+"000000000"+b);
                }

            }
        });*/
        swanh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int vthtanh1=_spinnervt.getSelectedItemPosition();
                vthtanh=vthtanh1+"";
                if(vthtanh.length()==1){
                    vthtanh="0"+vthtanh;
                }
                String vta=vtanh+"";
                if(vta.length()==1){
                    vta="0"+vta;
                }
                Toast.makeText(images.this,vthtanh,Toast.LENGTH_LONG).show();
                if(isChecked==true) {
                    if (gamma == true) {
                        runUdpServer("na" + vta + "g" + vthtanh + "000000000" + b);
                    } else {
                        runUdpServer("na" + vta + "0" + vthtanh + "000000000" + b);
                    }
                }
                else{
                    runUdpServer("na" + "00" + "0" + vthtanh + "000000000" + b);
                }


            }
        });
        swanhdong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int vthtanh1=_spinnervt.getSelectedItemPosition();
                vthtanh=vthtanh1+"";
                if(vthtanh.length()==1){
                    vthtanh="0"+vthtanh;
                }
                String vta=vtanhdong+"";
                if(vta.length()==1){
                    vta="0"+vta;
                }
                if(isChecked==true) {
                    if (gamma == true) {
                        runUdpServer("ng" + vta + "g" + vthtanh + "000000000" + b);
                    } else {
                        runUdpServer("ng" + vta + "0" + vthtanh + "000000000" + b);
                    }
                }
                else{
                    runUdpServer("ng" + "00" + "0" + vthtanh + "000000000" + b);
                }

            }
        });
        _clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runUdpServer("n"+"c"+"00000000000000"+b);
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
