package edu.eci.logservice;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoContainerClient {
    private final String connUri = "localhost:27017";
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public void save(Document d){
        connect();
        collection.insertOne(d);
        close();
    }

    public List<Document> getLastTenDocs(){
        connect();
        List<Document> last = collection.find().sort(Sorts.descending("_id")).limit(10).into(new ArrayList<>());
        close();
        return last;
    }

    private void connect(){
        this.client = new MongoClient(connUri);
        this.db = client.getDatabase("arep");
        this.collection = db.getCollection("Logs");

    }

    private void close(){
        this.client.close();
    }
}
