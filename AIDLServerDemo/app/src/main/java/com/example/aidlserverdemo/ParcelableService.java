package com.example.aidlserverdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class ParcelableService extends Service {
    public ParcelableService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ParcelableBinder();
    }

    class ParcelableBinder extends ParcelableAIDL.Stub{


        @Override
        public People getPeople() throws RemoteException {
            People people = new People("aaa", "男", 20);
            return people;
        }
    }
}
