package com.CouponManagerSpring.Singleton;

import java.util.HashMap;
import java.util.Map;

public class Singleton{
/// dont remove for later.. its good . i didnt use it yet 3shan mn5rb4 alkod
    private static final Singleton instance = new Singleton();

    @SuppressWarnings( "rawtypes")
    private Map<Class,Object> mapHolder = new HashMap<Class,Object>();

    private Singleton() {}


    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> classOf) throws InstantiationException, IllegalAccessException {


        synchronized(instance){

            if(!instance.mapHolder.containsKey(classOf)){

                @SuppressWarnings("deprecation")
				T obj = classOf.newInstance();

                instance.mapHolder.put(classOf, obj);
            }

            return (T)instance.mapHolder.get(classOf);

        }

    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
