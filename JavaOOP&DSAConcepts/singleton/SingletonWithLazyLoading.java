package singleton;

import java.io.Serializable;

// X not Thread Safe
// X No serialazation protection
// X Vulnerable to Reflection.utils attack

// here we are not using serializble
public class SingletonWithLazyLoading implements Serializable {

    private static final long serialVersionUID = 1L;

    //Its static so that the instance which is created once
    // can be shared by all the class members

    private static SingletonWithLazyLoading instance;

    //Create a private constructor so that it cant be called outside the class
    // except REFLECTION UTILS

    private SingletonWithLazyLoading(){}

    public static SingletonWithLazyLoading getInstance(){
        if (instance == null)
            instance = new SingletonWithLazyLoading();
        return instance;
    }

}
