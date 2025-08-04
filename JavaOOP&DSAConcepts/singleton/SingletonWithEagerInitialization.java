package singleton;

//  Thread Safe - synchronized ensure Thread saftey
//  Serialazation protection - ensure Serialization property during deserialization
//  Vulnerable to Reflection.utils attack

import java.io.ObjectStreamException;
import java.io.Serializable;

public class SingletonWithEagerInitialization implements Serializable {

    private static final Long serialVersionUID = 1L;

    private static final SingletonWithEagerInitialization INSTANCE = new SingletonWithEagerInitialization();

    private SingletonWithEagerInitialization(){
        // To prevent Reflection utils from breaking,
        // throw an exception which is only way to prevent this explicitly
//        if (INSTANCE != null) {
//            throw new RuntimeException("Use getInstance() method to create");
//        }
    }

    public static SingletonWithEagerInitialization getInstance(){
        return INSTANCE;
    }

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
}
