package main.java.models.database.persistancefecade;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.models.database.IStore;
import main.java.models.database.utils.HibernateUtils;
import main.java.models.factory.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class PersistenceFecade {
    private IStore storageUnit;
    private HashMap<Class, HashMap<Integer, Object>> cache;
    private static PersistenceFecade instance;

    public static PersistenceFecade getInstance() throws YamlException, FileNotFoundException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(instance == null) instance = new PersistenceFecade();
        return instance;
    }
    public Object get(int OID, Class c){
        if(getFromCache(OID, c) == null){
            Object o = storageUnit.get(OID, c);
            cache.get(c).put(OID, o);
            return o;
        }
        return getFromCache(OID, c);
    }
    public void put(Object o) throws Exception {
        storageUnit.put(o);
    }
    public Object getFromCache(int OID, Class c){
        return cache.get(c).get(OID);
    }
    public PersistenceFecade() throws YamlException, FileNotFoundException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        storageUnit = Factory.getInstance().getStorageUnit();
    }
}
