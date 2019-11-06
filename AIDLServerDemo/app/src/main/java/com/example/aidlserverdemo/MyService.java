package com.example.aidlserverdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    class MyBind extends MyAIDL.Stub {

        @Override
        public String getString() throws RemoteException {
            String s = "this String from AIDLServerDemo";

            try {
                Thread.sleep(3000);
                //假装做了很耗时的操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        }

    }
}
