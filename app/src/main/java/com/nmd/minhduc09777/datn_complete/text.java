package com.nmd.minhduc09777.datn_complete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class text extends AppCompatActivity {

    EditText _edittextnoidung,_edittextvitrix,_edittextvitriy;
    SeekBar _seekbarR,_seekbarG,_seekbarB;
    Button _buttonxn,_btnxoa;
    Spinner _font;
    int tt_th=0;
    String vt_ht;
    int vtfont;
    String red="000",green="000",blue="000",ND;
    RadioGroup _radioG;
    String b=" ";
    WifiManager w;
    private AsyncTask<Void, Void, Void> async_udp;
    InetAddress server_ip;
    int dem=0;
    int server_port = 2392;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        w = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!w.isWifiEnabled()) {
            Toast.makeText(text.this,"switching ON wifi",Toast.LENGTH_LONG).show();
            w.setWifiEnabled(true);
        } else {
            Toast.makeText(text.this,"Its already ON ",Toast.LENGTH_LONG).show();
        }
        for(int i=b.length();i<64;i++){
            b=b+" ";
        }
        _edittextnoidung=(EditText) findViewById(R.id.editTextNoidung);
        _edittextvitrix=(EditText) findViewById(R.id.editTextVitrix);
        _edittextvitriy=(EditText) findViewById(R.id.editTextVitriy);
        _font=(Spinner) findViewById(R.id.spinnerfont);
        _seekbarR=(SeekBar) findViewById(R.id.seekBar);
        _seekbarG=(SeekBar) findViewById(R.id.seekBar2);
        _seekbarB=(SeekBar) findViewById(R.id.seekBar3);
        _buttonxn=(Button) findViewById(R.id.button);
        _btnxoa=(Button) findViewById(R.id.btn_xoa);
        _radioG=(RadioGroup) findViewById(R.id.radioG);
        _seekbarR.setMax(255);
        _seekbarB.setMax(255);
        _seekbarG.setMax(255);

        final ArrayList<String> array = new ArrayList<String>();
        array.add("0");
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");
        array.add("7");
        array.add("8");
        array.add("9");
        final ArrayList<String> arrayfont = new ArrayList<String>();
        arrayfont.add("font Arial16x16");
        arrayfont.add("font Arial16x32");
        arrayfont.add("font swiss16x16");
        arrayfont.add("font nadiane16x16");
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,array);
        final ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayfont);
        _font.setAdapter(arrayAdapter1);
        _buttonxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vtx="000",vty="000";
                vtx=_edittextvitrix.getText().toString().trim();
                vty=_edittextvitriy.getText().toString().trim();
                ND=_edittextnoidung.getText().toString().trim();

                if (vtx.length() == 2){
                    vtx = '0' + vtx;
                }
                else if  (vtx.length() == 1){
                    vtx = "00" +vtx;
                }
                else if(vtx.length() > 3) {
                    vtx="000";
                }

                if (vty.length() == 2){
                    vty = '0' + vty;
                }
                else if  (vty.length() == 1){
                    vty = "00" +vty;
                }
                else if(vty.length() > 3) {
                    vty="000";
                }
                ND=ND.replace("Á",  "|001");
                ND=ND.replace("À",  "|002");
                ND=ND.replace("Ả",  "|003");
                ND=ND.replace("Ã",  "|004");
                ND=ND.replace("Ạ",  "|005");
                ND=ND.replace("Ă",  "|006");
                ND=ND.replace("Ắ",  "|007");
                ND=ND.replace("Ằ",  "|008");
                ND=ND.replace("Ẳ",  "|009");
                ND=ND.replace("Ẵ",  "|010");
                ND=ND.replace("Ặ",  "|011");
                ND=ND.replace("Â",  "|012");
                ND=ND.replace("Ấ",  "|013");
                ND=ND.replace("Ầ",  "|014");
                ND=ND.replace("Ẩ",  "|015");
                ND=ND.replace("Ẫ",  "|016");
                ND=ND.replace("Ậ",  "|017");
                ND=ND.replace("Đ",  "|018");
                ND=ND.replace("É",  "|019");
                ND=ND.replace("È",  "|020");
                ND=ND.replace("Ẻ",  "|021");
                ND=ND.replace("Ẽ",  "|022");
                ND=ND.replace("Ẹ",  "|023");
                ND=ND.replace("Ê",  "|024");
                ND=ND.replace("Ế",  "|025");
                ND=ND.replace("Ề",  "|026");
                ND=ND.replace("Ể",  "|027");
                ND=ND.replace("Ễ",  "|028");
                ND=ND.replace("Ệ",  "|029");
                ND=ND.replace("Í",  "|030");
                ND=ND.replace("Ì",  "|031");
                ND=ND.replace("Ỉ",  "|127");
                ND=ND.replace("Ĩ",  "|128");
                ND=ND.replace("Ị",  "|129");
                ND=ND.replace("Ó",  "|130");
                ND=ND.replace("Ò",  "|131");
                ND=ND.replace("Ỏ",  "|132");
                ND=ND.replace("Õ",  "|133");
                ND=ND.replace("Ọ",  "|134");
                ND=ND.replace("Ô",  "|135");
                ND=ND.replace("Ố",  "|136");
                ND=ND.replace("Ồ",  "|137");
                ND=ND.replace("Ổ",  "|138");
                ND=ND.replace("Ỗ",  "|139");
                ND=ND.replace("Ộ",  "|140");
                ND=ND.replace("Ơ",  "|141");
                ND=ND.replace("Ớ",  "|142");
                ND=ND.replace("Ờ",  "|143");
                ND=ND.replace("Ở",  "|144");
                ND=ND.replace("Ỡ",  "|145");
                ND=ND.replace("Ợ",  "|146");
                ND=ND.replace("Ú",  "|147");
                ND=ND.replace("Ù",  "|148");
                ND=ND.replace("Ủ",  "|149");
                ND=ND.replace("Ũ",  "|150");
                ND=ND.replace("Ụ",  "|151");
                ND=ND.replace("Ư",  "|152");
                ND=ND.replace("Ứ",  "|153");
                ND=ND.replace("Ừ",  "|154");
                ND=ND.replace("Ử",  "|155");
                ND=ND.replace("Ữ",  "|156");
                ND=ND.replace("Ự",  "|157");
                ND=ND.replace("Ý",  "|158");
                ND=ND.replace("Ỳ",  "|159");
                ND=ND.replace("Ỷ",  "|160");
                ND=ND.replace("Ỹ",  "|161");
                ND=ND.replace("Ỵ",  "|162");
                ND=ND.replace("á",  "|163");
                ND=ND.replace("à",  "|164");
                ND=ND.replace("ả",  "|165");
                ND=ND.replace("ã",  "|166");
                ND=ND.replace("ạ",  "|167");
                ND=ND.replace("ă",  "|168");
                ND=ND.replace("ắ",  "|169");
                ND=ND.replace("ằ",  "|170");
                ND=ND.replace("ẳ",  "|171");
                ND=ND.replace("ẵ",  "|172");
                ND=ND.replace("ặ",  "|173");
                ND=ND.replace("â",  "|174");
                ND=ND.replace("ấ",  "|175");
                ND=ND.replace("ầ",  "|176");
                ND=ND.replace("ẩ",  "|177");
                ND=ND.replace("ẫ",  "|178");
                ND=ND.replace("ậ",  "|179");
                ND=ND.replace("đ",  "|180");
                ND=ND.replace("é",  "|181");
                ND=ND.replace("è",  "|182");
                ND=ND.replace("ẻ",  "|183");
                ND=ND.replace("ẽ",  "|184");
                ND=ND.replace("ẹ",  "|185");
                ND=ND.replace("ê",  "|186");
                ND=ND.replace("ế",  "|187");
                ND=ND.replace("ề",  "|188");
                ND=ND.replace("ể",  "|189");
                ND=ND.replace("ễ",  "|190");
                ND=ND.replace("ệ",  "|191");
                ND=ND.replace("í",  "|192");
                ND=ND.replace("ì",  "|193");
                ND=ND.replace("ỉ",  "|194");
                ND=ND.replace("ĩ",  "|195");
                ND=ND.replace("ị",  "|196");
                ND=ND.replace("ó",  "|197");
                ND=ND.replace("ò",  "|198");
                ND=ND.replace("ỏ",  "|199");
                ND=ND.replace("õ",  "|200");
                ND=ND.replace("ọ",  "|201");
                ND=ND.replace("ô",  "|202");
                ND=ND.replace("ố",  "|203");
                ND=ND.replace("ồ",  "|204");
                ND=ND.replace("ổ",  "|205");
                ND=ND.replace("ỗ",  "|206");
                ND=ND.replace("ộ",  "|207");
                ND=ND.replace("ơ",  "|208");
                ND=ND.replace("ớ",  "|209");
                ND=ND.replace("ờ",  "|210");
                ND=ND.replace("ở",  "|211");
                ND=ND.replace("ỡ",  "|212");
                ND=ND.replace("ợ",  "|213");
                ND=ND.replace("ú",  "|214");
                ND=ND.replace("ù",  "|215");
                ND=ND.replace("ủ",  "|216");
                ND=ND.replace("ũ",  "|217");
                ND=ND.replace("ụ",  "|218");
                ND=ND.replace("ư",  "|219");
                ND=ND.replace("ứ",  "|220");
                ND=ND.replace("ừ",  "|221");
                ND=ND.replace("ử",  "|222");
                ND=ND.replace("ữ",  "|223");
                ND=ND.replace("ự",  "|224");
                ND=ND.replace("ý",  "|225");
                ND=ND.replace("ỳ",  "|226");
                ND=ND.replace("ỷ",  "|227");
                ND=ND.replace("ỹ",  "|228");
                ND=ND.replace("ỵ",  "|229");
                for(int i=ND.length();i<63;i++){
                    ND=ND+" ";
                }
                vtfont=_font.getSelectedItemPosition();
                runUdpServer("n"+"x"+vtx+"y"+vty+"000000"+vtfont+vt_ht+ND);

            }
        });
        _btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runUdpServer("n"+"c"+"00000000000000"+b);
            }
        });
        _radioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_dichtrai:
                        runUdpServer("n"+"t"+"0000000000000"+vt_ht+b);
                        break;
                    case R.id.bth:
                        runUdpServer("n"+"n"+"0000000000000"+vt_ht+b);
                        break;
                    case R.id.rbtn_dichphai:
                        runUdpServer("n"+"p"+"0000000000000"+vt_ht+b);
                        break;
                }
            }
        });

        _seekbarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue=progress+"";
                if (blue.length() == 2){
                    blue = '0' + blue;
                }
                else if  (blue.length() == 1){
                    blue = "00" +blue;
                }
                String b = "n"+"R"+red+green+blue+"00000";
                for(int i=b.length();i<64;i++){
                    b=b+" ";
                }
                runUdpServer("n"+"R"+red+green+blue+"0000"+vt_ht+b);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        _seekbarG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green=progress+"";
                if (green.length() == 2){
                    green = '0' + green;
                }
                else if  (green.length() == 1){
                    green = "00" +green;
                }
                String b = "n"+"R"+red+green+blue+"00000";
                for(int i=b.length();i<64;i++){
                    b=b+" ";
                }
                runUdpServer("n"+"R"+red+green+blue+"0000"+vt_ht+b);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        _seekbarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red=progress+"";
                if (red.length() == 2){
                    red = '0' + red;
                }
                else if  (red.length() == 1){
                    red = "00" +red;
                }
                String b = "n"+"R"+red+green+blue+"00000";
                for(int i=b.length();i<64;i++){
                    b=b+" ";
                }
                runUdpServer("n"+"R"+red+green+blue+"0000"+vt_ht+b);
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
