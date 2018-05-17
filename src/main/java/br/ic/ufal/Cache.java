package br.ic.ufal;

import java.util.HashMap;

public class Cache {

    public HashMap<String, String> requestDatabase;

    public Cache () {
        this.requestDatabase = new HashMap<>();
    }

    public void saveToCache(String key, String value) {
        this.requestDatabase.put(key, value);
    }

    public String getFromCache(String key) {
        return this.requestDatabase.get(key);
    }

}