package GUI;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;

public class DataParser {

    public DataParser(){};
    public ArrayList<Float> parse()
    {
        ArrayList<Float> attributes = new ArrayList<>();

        MongoClient mongoClient = MongoClients.create("mongodb+srv://user:user@maincluster.kqq8z.mongodb.net/KSR2?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("KSR2");
        MongoCollection<Document> collection = database.getCollection("matches");


        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        return null;
    }
}
