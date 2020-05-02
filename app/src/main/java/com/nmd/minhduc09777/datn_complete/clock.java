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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class clock extends AppCompatActivity {

    Button btnThoiGian, btnTat;
    SeekBar SkChinhvitri;
    Spinner spinnerGio;
    Spinner spinnerPhut;
    Spinner spinnerGiay;
    TextView txtVitriDH;
    String b=" ";
    int tt=1;
    String Gio="00",Phut="00",Giay="00";
    public static String Vtdh="000";
    public static String tat="";
    WifiManager w;
    private AsyncTask<Void, Void, Void> async_udp;
    InetAddress server_ip;
    int server_port = 2392;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        w = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!w.isWifiEnabled()) {
            Toast.makeText(clock.this,"switching ON wifi",Toast.LENGTH_LONG).show();
            w.setWifiEnabled(true);
        } else {
            Toast.makeText(clock.this,"Its already ON ",Toast.LENGTH_LONG).show();
        }
        for(int i=b.length();i<64;i++){
            b=b+" ";
        }
        for(int i=b.length();i<64;i++){
            b=b+" ";
        }
        spinnerGio = (Spinner) findViewById(R.id.spinner);
        spinnerPhut = (Spinner) findViewById(R.id.spinner3);
        spinnerGiay = (Spinner) findViewById(R.id.spinner4);
        btnThoiGian =(Button) findViewById(R.id.buttonThoigian);
        btnTat = (Button) findViewById(R.id.buttonTatDH);
        SkChinhvitri=(SeekBar) findViewById(R.id.seekBarChinhVitri);
        txtVitriDH=(TextView) findViewById(R.id.textViewVitriDH1);
        SkChinhvitri.setMax(255);
        final ArrayList<String> arrayGio = new ArrayList<String>();
        arrayGio.add("1");
        arrayGio.add("2");
        arrayGio.add("3");
        arrayGio.add("4");
        arrayGio.add("5");
        arrayGio.add("6");
        arrayGio.add("7");
        arrayGio.add("8");
        arrayGio.add("9");
        arrayGio.add("10");
        arrayGio.add("11");
        arrayGio.add("12");
        final  ArrayList<String>arrayPhut = new ArrayList<String>();
        arrayPhut.add("1");
        arrayPhut.add("2");
        arrayPhut.add("3");
        arrayPhut.add("4");
        arrayPhut.add("5");
        arrayPhut.add("6");
        arrayPhut.add("7");
        arrayPhut.add("8");
        arrayPhut.add("9");
        arrayPhut.add("10");
        arrayPhut.add("11");
        arrayPhut.add("12");
        arrayPhut.add("13");
        arrayPhut.add("14");
        arrayPhut.add("15");
        arrayPhut.add("16");
        arrayPhut.add("17");
        arrayPhut.add("18");
        arrayPhut.add("19");
        arrayPhut.add("20");
        arrayPhut.add("21");
        arrayPhut.add("22");
        arrayPhut.add("23");
        arrayPhut.add("24");
        arrayPhut.add("25");
        arrayPhut.add("26");
        arrayPhut.add("27");
        arrayPhut.add("28");
        arrayPhut.add("29");
        arrayPhut.add("30");
        arrayPhut.add("31");
        arrayPhut.add("32");
        arrayPhut.add("33");
        arrayPhut.add("34");
        arrayPhut.add("35");
        arrayPhut.add("36");
        arrayPhut.add("37");
        arrayPhut.add("38");
        arrayPhut.add("39");
        arrayPhut.add("40");
        arrayPhut.add("41");
        arrayPhut.add("42");
        arrayPhut.add("43");
        arrayPhut.add("44");
        arrayPhut.add("45");
        arrayPhut.add("46");
        arrayPhut.add("47");
        arrayPhut.add("48");
        arrayPhut.add("49");
        arrayPhut.add("50");
        arrayPhut.add("51");
        arrayPhut.add("52");
        arrayPhut.add("53");
        arrayPhut.add("54");
        arrayPhut.add("55");
        arrayPhut.add("56");
        arrayPhut.add("57");
        arrayPhut.add("58");
        arrayPhut.add("59");
        final  ArrayList<String>arrayGiay = new ArrayList<String>();
        arrayGiay.add("1");
        arrayGiay.add("2");
        arrayGiay.add("3");
        arrayGiay.add("4");
        arrayGiay.add("5");
        arrayGiay.add("6");
        arrayGiay.add("7");
        arrayGiay.add("8");
        arrayGiay.add("9");
        arrayGiay.add("10");
        arrayGiay.add("11");
        arrayGiay.add("12");
        arrayGiay.add("13");
        arrayGiay.add("14");
        arrayGiay.add("15");
        arrayGiay.add("16");
        arrayGiay.add("17");
        arrayGiay.add("18");
        arrayGiay.add("19");
        arrayGiay.add("20");
        arrayGiay.add("21");
        arrayGiay.add("22");
        arrayGiay.add("23");
        arrayGiay.add("24");
        arrayGiay.add("25");
        arrayGiay.add("26");
        arrayGiay.add("27");
        arrayGiay.add("28");
        arrayGiay.add("29");
        arrayGiay.add("30");
        arrayGiay.add("31");
        arrayGiay.add("32");
        arrayGiay.add("33");
        arrayGiay.add("34");
        arrayGiay.add("35");
        arrayGiay.add("36");
        arrayGiay.add("37");
        arrayGiay.add("38");
        arrayGiay.add("39");
        arrayGiay.add("40");
        arrayGiay.add("41");
        arrayGiay.add("42");
        arrayGiay.add("43");
        arrayGiay.add("44");
        arrayGiay.add("45");
        arrayGiay.add("46");
        arrayGiay.add("47");
        arrayGiay.add("48");
        arrayGiay.add("49");
        arrayGiay.add("50");
        arrayGiay.add("51");
        arrayGiay.add("52");
        arrayGiay.add("53");
        arrayGiay.add("54");
        arrayGiay.add("55");
        arrayGiay.add("56");
        arrayGiay.add("57");
        arrayGiay.add("58");
        arrayGiay.add("59");
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayGio);
        final ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayPhut);
        final ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayGiay);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGio.setAdapter(arrayAdapter);
        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerPhut.setAdapter(arrayAdapter1);
        arrayAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGiay.setAdapter(arrayAdapter2);

        SkChinhvitri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtVitriDH.setText(progress+"");
                Vtdh=progress+"";
                if (Vtdh.length()==1)
                {
                    Vtdh= "00"+ Vtdh;
                }
                if (Vtdh.length()==2)
                {
                    Vtdh= '0'+ Vtdh;
                }
                if (tt==0) {
                    tat = "nhv" + Vtdh+"0000000000"+b;
                    runUdpServer(tat);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt=1;
                tat = "nht" + Vtdh+"0000000000"+b;
                runUdpServer(tat);
            }
        });
        btnThoiGian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt=0;
                tat = "nhv" + Vtdh+"0000000000"+b;
                runUdpServer(tat);
            }
        });

        spinnerGiay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Gio = spinnerGio.getSelectedItem().toString();
                if (Gio.length()==1)
                {
                    Gio= '0'+ Gio;
                }
                Phut = spinnerPhut.getSelectedItem().toString();
                if (Phut.length()==1)
                {
                    Phut= '0'+ Phut;
                }
                Giay = spinnerGiay.getSelectedItem().toString();
                if (Giay.length()==1)
                {
                    Giay= '0'+ Giay;
                }
                tt=0;
                tat="nhc"+Gio+Phut+Giay+"0000000"+b;
                runUdpServer(tat);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerPhut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Gio = spinnerGio.getSelectedItem().toString();
                if (Gio.length()==1)
                {
                    Gio= '0'+ Gio;
                }
                Phut = spinnerPhut.getSelectedItem().toString();
                if (Phut.length()==1)
                {
                    Phut= '0'+ Phut;
                }
                Giay = spinnerGiay.getSelectedItem().toString();
                if (Giay.length()==1)
                {
                    Giay= '0'+ Giay;
                }
                tt=0;
                tat="nhc"+Gio+Phut+Giay+"0000000"+b;
                runUdpServer(tat);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerGio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Gio = spinnerGio.getSelectedItem().toString();
                if (Gio.length()==1)
                {
                    Gio= '0'+ Gio;
                }
                Phut = spinnerPhut.getSelectedItem().toString();
                if (Phut.length()==1)
                {
                    Phut= '0'+ Phut;
                }
                Giay = spinnerGiay.getSelectedItem().toString();
                if (Giay.length()==1)
                {
                    Giay= '0'+ Giay;
                }
                tt=0;
                tat="nhc"+Gio+Phut+Giay+"0000000"+b;
                runUdpServer(tat);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
