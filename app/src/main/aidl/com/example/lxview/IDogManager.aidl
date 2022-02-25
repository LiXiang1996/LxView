package com.example.lxview;

import android.os.IBinder;
import android.os.IInterface;
import com.example.lxview.Dog;
import java.util.List;


 interface IDogManager {

     List<Dog> getDogList();
     void addDog(in Dog dog) ;

}


