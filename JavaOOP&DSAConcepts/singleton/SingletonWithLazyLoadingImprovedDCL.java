package singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

//  Thread Safe - synchronized ensure Thread saftey
//  Serialazation protection - ensure Serialization property during deserialization
// X Vulnerable to Reflection.utils attack

public class SingletonWithLazyLoadingImprovedDCL implements Serializable {

    private static final long serialVersionUID = 1L;

    //Its static so that the instance which is created once
    // can be shared by all the class members

    private static volatile SingletonWithLazyLoadingImprovedDCL instance;

    //Create a private constructor so that it cant be called outside the class
    // except REFLECTION UTILS

    private SingletonWithLazyLoadingImprovedDCL(){}

    // synchronized ensure Thread saftey
    public static  SingletonWithLazyLoadingImprovedDCL getInstance(){
        if (instance == null){
            synchronized (SingletonWithLazyLoadingImprovedDCL.class) {
                if (instance == null) {
                    instance = new SingletonWithLazyLoadingImprovedDCL();

                }
            }
        }
        return instance;
    }

    // ensure Serialization property during deserialization
    private Object readResolve() throws ObjectStreamException{
        return instance;
    }

}
