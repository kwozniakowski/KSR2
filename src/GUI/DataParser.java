package GUI;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataParser {

    public DataParser(){};
    public ArrayList<Float> parse()
    {
        ArrayList<Float> attributes = new ArrayList<>();
        ArrayList<String> stringAttributes = new ArrayList<>();


        MongoClient mongoClient = MongoClients.create("mongodb+srv://user:user@maincluster.kqq8z.mongodb.net/KSR2?retryWrites=true&w=majority");


        return null;
    }
}
