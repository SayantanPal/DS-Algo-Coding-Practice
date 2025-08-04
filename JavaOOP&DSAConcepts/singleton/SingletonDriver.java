package singleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class SingletonDriver {
    public static void main(String[] args) throws Exception {
        SingletonWithLazyLoading ll1 = SingletonWithLazyLoading.getInstance();
        SingletonWithLazyLoading ll2 = SingletonWithLazyLoading.getInstance();
        SingletonWithLazyLoadingImproved llImproved = SingletonWithLazyLoadingImproved.getInstance();
        SingletonWithLazyLoadingImprovedDCL llImpDCL = SingletonWithLazyLoadingImprovedDCL.getInstance();
        SingletonWithLazyLoadingImprovedBP llImpBP = SingletonWithLazyLoadingImprovedBP.getInstance();
        SingletonWithEagerInitialization llEI = SingletonWithEagerInitialization.getInstance();

        System.out.println("Is Singleton ? : ll1 == ll2  " + (ll1 == ll2));

        SingletonWithLazyLoading deseializedll = (SingletonWithLazyLoading)serializationDeserialization(ll1);


        System.out.println("Is Deserialization Protected for SingletonWithLazyLoading ? : ll1 == deseializedll  " + (ll1 == deseializedll));

        // Improved SYN REF
        SingletonWithLazyLoadingImproved deseializedllImproved = (SingletonWithLazyLoadingImproved)serializationDeserialization(llImproved);
        System.out.println("Is Deserialization Protected for SingletonWithLazyLoadingImproved ? : llImproved == deseializedllImproved  " + (llImproved == deseializedllImproved));

        SingletonWithLazyLoadingImproved reflectionObjllImproved = (SingletonWithLazyLoadingImproved)breakSingletonViaReflection(llImproved);
        System.out.println("Is Reflection Protected for SingletonWithLazyLoadingImproved ? : llImproved == reflectionObjllImproved  " + (llImproved == reflectionObjllImproved));

        // DCL SYN & Ref
        SingletonWithLazyLoadingImprovedDCL deseializedllImpDCL = (SingletonWithLazyLoadingImprovedDCL)serializationDeserialization(llImpDCL);
        System.out.println("Is Deserialization Protected for SingletonWithLazyLoadingImprovedDCL ? : llImpDCL == deseializedllImpDCL  " + (llImpDCL == deseializedllImpDCL));

        SingletonWithLazyLoadingImprovedDCL reflectionObjllImpDCL = (SingletonWithLazyLoadingImprovedDCL)breakSingletonViaReflection(llImpDCL);
        System.out.println("Is Reflection Protected for SingletonWithLazyLoadingImprovedDCL ? : llImpDCL == reflectionObjllImpDCL  " + (llImpDCL == reflectionObjllImpDCL));

        // BP SYN & Ref
        SingletonWithLazyLoadingImprovedBP deseializedllImpBP = (SingletonWithLazyLoadingImprovedBP)serializationDeserialization(llImpBP);
        System.out.println("Is Deserialization Protected for SingletonWithLazyLoadingImprovedDCL ? : llImpBP == deseializedllImpBP  " + (llImpBP == deseializedllImpBP));

        SingletonWithLazyLoadingImprovedBP reflectionObjllImpBP = (SingletonWithLazyLoadingImprovedBP)breakSingletonViaReflection(llImpBP);
        System.out.println("Is Reflection Protected for SingletonWithLazyLoadingImprovedDCL ? : llImpBP == reflectionObjllImpBP  " + (llImpBP == reflectionObjllImpBP));

        // EI SYN & Ref
        SingletonWithEagerInitialization deseializedllEI = (SingletonWithEagerInitialization)serializationDeserialization(llEI);
        System.out.println("Is Deserialization Protected for SingletonWithEagerInitialization ? : llEI == deseializedllEI  " + (llEI == deseializedllEI));

        SingletonWithEagerInitialization reflectionObjllEI = (SingletonWithEagerInitialization)breakSingletonViaReflection(llEI);
        System.out.println("Is Reflection Protected for SingletonWithEagerInitialization ? : llEI == reflectionObjllEI  " + (llEI == reflectionObjllEI));


        // Partially protected against reflection Util using boolean flag
        // Why Partial ? - technically fully protected but semantically partially protected
        // Means even if the object is singleton and the object creation happens only once, but still
        // since the object creation power/capacbility also resides in another reflection util class
        // which is foreign class unauthorized to create object of singleton object
        // hence, meaningfully it is not singleton because the responsibility of object creation
        // in case of Singleton class should only reside with Singleton

        Constructor constructor = SingletonWithLazyLoadingImproved2.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonWithLazyLoadingImproved2 brokenSingleton1 = (SingletonWithLazyLoadingImproved2) constructor.newInstance();
        SingletonWithLazyLoadingImproved2 lli2 = SingletonWithLazyLoadingImproved2.getInstance();

        System.out.println("SingletonWithLazyLoadingImproved2 => brokenSingleton1 == lli2 ? : " + (brokenSingleton1 == lli2)); // reflection utils allows only 1 fake obj creation
   }

    public static Object serializationDeserialization(String fileName, Object ob) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(ob);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Object deseializedOb = ois.readObject();
        ois.close();
        return deseializedOb;
    }

    public static Object serializationDeserialization(Object ob) throws IOException, ClassNotFoundException {
        return serializationDeserialization("example.txt", ob);
    }

    public static Object breakSingletonViaReflection(Object ob) throws Exception {
        Class cls = ob.getClass();
        Constructor constructor = cls.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }
}
