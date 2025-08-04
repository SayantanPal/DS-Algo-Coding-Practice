package singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

//  Thread Safe - synchronized ensure Thread saftey
//  Serialazation protection - ensure Serialization property during deserialization
// X Vulnerable to Reflection.utils attack

// Bill Plugh - using static inner class
public class SingletonWithLazyLoadingImprovedBP implements Serializable {

    private static final long serialVersionUID = 1L;


    //Create a private constructor so that it cant be called outside the class
    // except REFLECTION UTILS
    private SingletonWithLazyLoadingImprovedBP(){}

    private static class Holder{
        private static final SingletonWithLazyLoadingImprovedBP INSTANCE = new SingletonWithLazyLoadingImprovedBP();
    }


    // synchronized ensure Thread saftey
    public static SingletonWithLazyLoadingImprovedBP getInstance(){
        return Holder.INSTANCE;
    }

    // ensure Serialization property during deserialization
    private Object readResolve() throws ObjectStreamException{
        return Holder.INSTANCE;
    }
}
