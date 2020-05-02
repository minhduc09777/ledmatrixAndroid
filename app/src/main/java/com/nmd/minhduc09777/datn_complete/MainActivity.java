package com.nmd.minhduc09777.datn_complete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    GridView _ds;
    ArrayList<HinhAnh> hinhanhArrayList;
    Handicapper handicapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _ds=(GridView) findViewById(R.id.ds);
        hinhanhArrayList = new ArrayList<>();
      //  hinhanhArrayList.add(new HinhAnh("  Paint", R.drawable.paint1));
     //   hinhanhArrayList.add(new HinhAnh("Game Play", R.drawable.game));
        hinhanhArrayList.add(new HinhAnh("  Text", R.drawable.text1));
        hinhanhArrayList.add(new HinhAnh("  Effect", R.drawable.effect));
        hinhanhArrayList.add(new HinhAnh("    Clock", R.drawable.clock1));
        hinhanhArrayList.add(new HinhAnh("   Temp", R.drawable.temp1));
        hinhanhArrayList.add(new HinhAnh("Brightness", R.drawable.brightness));
        hinhanhArrayList.add(new HinhAnh("     Image", R.drawable.imgea));
     //   hinhanhArrayList.add(new HinhAnh("    Video", R.drawable.video));

        handicapper= new Handicapper(this,R.layout.dong_danh_sach,hinhanhArrayList);

        _ds.setAdapter(handicapper);
        _ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* if(position==0){
                    Intent intent=new Intent(MainActivity.this,paint_acti.class);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent=new Intent(MainActivity.this,gameplay.class);
                    startActivity(intent);
                }*/
                if(position==0){
                    Intent intent=new Intent(MainActivity.this,text.class);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent=new Intent(MainActivity.this,effect.class);
                    startActivity(intent);
                }
                if(position==2){
                    Intent intent=new Intent(MainActivity.this,clock.class);
                    startActivity(intent);
                }
                if(position==3){
                    Intent intent=new Intent(MainActivity.this,nhietdo.class);
                    startActivity(intent);
                }
                if(position==4){
                    Intent intent=new Intent(MainActivity.this,brightness.class);
                    startActivity(intent);
                }
                if(position==5){
                    Intent intent=new Intent(MainActivity.this,images.class);
                    startActivity(intent);
                }
               /* if(position==8){
                    Intent intent=new Intent(MainActivity.this,video.class);
                    startActivity(intent);
                }*/
            }
        });
    }
}
