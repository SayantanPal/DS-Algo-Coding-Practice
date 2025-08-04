package singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

//  Thread Safe - synchronized ensure Thread saftey
//  Serialazation protection - ensure Serialization property during deserialization
// -X- partially Vulnerable to Reflection.utils attack

public class SingletonWithLazyLoadingImproved2 implements Serializable {

    private static final long serialVersionUID = 1L;
    private static boolean hasInstance = false;

    //Its static so that the instance which is created once
    // can be shared by all the class members

    private static SingletonWithLazyLoadingImproved2 instance;

    //Create a private constructor so that it cant be called outside the class
    // except REFLECTION UTILS

    private SingletonWithLazyLoadingImproved2(){
        if(hasInstance){
            throw new RuntimeException("Use getInstance() method to create first");
        }
        hasInstance = true;
    }

    // synchronized ensure Thread saftey
    public static synchronized SingletonWithLazyLoadingImproved2 getInstance(){
        if (instance == null)
            instance = new SingletonWithLazyLoadingImproved2();
        return instance;
    }

    // ensure Serialization property during deserialization
    private Object readResolve() throws ObjectStreamException{
        return instance;
    }

}
