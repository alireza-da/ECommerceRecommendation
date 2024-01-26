package main.java.models.database;

public interface IStore {
    public Object get(int ID, Class c);
    public void put(Object o) throws Exception;
}
