package com.nmd.minhduc09777.datn_complete;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class PaintView extends View {
    public ViewGroup.LayoutParams params;
    public static Path path = new Path();
    public  static Paint brush = new Paint();
    public PaintView(Context context) {
        super(context);
        brush.setAntiAlias(true);
        brush.setColor(Color.RED);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(8f);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }
    public void clear(){
        path.reset();
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointX =(int) event.getX();
        int pointY =(int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX,pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX,pointY);
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawPath(path,brush);
        canvas.restore();
    }

}


