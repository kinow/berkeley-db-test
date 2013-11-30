package com.tupilabs.berkeley_db;

import java.io.File;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;

public class Main {

    public static void main(String[] args) throws Exception {
        File envHome = new File("target/test.db");
        envHome.mkdirs();
        EnvironmentConfig configuration = new EnvironmentConfig();
        configuration.setAllowCreate(true);
        configuration.setTransactional(true);
        Environment env = new Environment(envHome, configuration);
        
        DatabaseConfig databaseConfiguration = new DatabaseConfig();
        databaseConfiguration.setAllowCreate(true);
        Database db = env.openDatabase(null, "tupilabs", databaseConfiguration);
        
        db.put(null, new DatabaseEntry("name".getBytes()), new DatabaseEntry("bruno".getBytes()));
        
        DatabaseEntry result = new DatabaseEntry();
        db.get(null, new DatabaseEntry("name".getBytes()), result, LockMode.DEFAULT);
        
        System.out.println(new String(result.getData(), "utf-8"));
        
        db.close();
        env.close();
    }
    
}
