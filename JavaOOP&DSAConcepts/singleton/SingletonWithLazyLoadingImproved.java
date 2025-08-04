package singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

//  Thread Safe - synchronized ensure Thread saftey
//  Serialazation protection - ensure Serialization property during deserialization
// X Vulnerable to Reflection.utils attack

public class SingletonWithLazyLoadingImproved implements Serializable {

    private static final long serialVersionUID = 1L;

    //Its static so that the instance which is created once
    // can be shared by all the class members

    private static SingletonWithLazyLoadingImproved instance;

    //Create a private constructor so that it cant be called outside the class
    // except REFLECTION UTILS

    private SingletonWithLazyLoadingImproved(){}

    // synchronized ensure Thread saftey
    public static synchronized SingletonWithLazyLoadingImproved getInstance(){
        if (instance == null)
            instance = new SingletonWithLazyLoadingImproved();
        return instance;
    }

    // ensure Serialization property during deserialization
    private Object readResolve() throws ObjectStreamException{
        return instance;
    }

}
