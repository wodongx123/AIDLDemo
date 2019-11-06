package com.example.aidlclientdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import com.example.aidlserverdemo.MyAIDL;
import com.example.aidlserverdemo.ParcelableAIDL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyAIDL myAIDL;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDL = MyAIDL.Stub.asInterface(service);
            try {
                //由于在那边的getString中设置了耗时操作，这边就看看是否有耗时
                Log.i(TAG, "onServiceConnected: " + SystemClock.uptimeMillis());
                Log.i(TAG, "onServiceConnected: " + myAIDL.getString());
                Log.i(TAG, "onServiceConnected: " + SystemClock.uptimeMillis());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private ParcelableAIDL parcelableAIDL;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            parcelableAIDL = ParcelableAIDL.Stub.asInterface(service);
            try {
                Log.i(TAG, "onServiceConnected: " + parcelableAIDL.getPeople());
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        //bind其他App的Service时，需要同时填写包名和动作，action可以自己取名，不过一般取类名。
        intent.setAction("com.example.aidlserverdemo.Myservice");
        intent.setPackage("com.example.aidlserverdemo");
        bindService(intent, connection, BIND_AUTO_CREATE);

        Intent intent1 = new Intent();
        intent1.setAction("com.example.aidlserverdemo.ParcelableService");
        intent1.setPackage("com.example.aidlserverdemo");
        bindService(intent1, serviceConnection, BIND_AUTO_CREATE);
    }
}
