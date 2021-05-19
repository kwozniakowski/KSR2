package Attributes;

import Memberships.TrapezoidMembership;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import java.util.ArrayList;

public class Attribute {
    private String name;
    private ArrayList<TrapezoidMembership> trapezoidMemberships;
    private ArrayList<Value> values;

    public Attribute(String name, ArrayList<TrapezoidMembership> trapezoidMemberships)
    {
        this.trapezoidMemberships = trapezoidMemberships;
        this.name = name;
        parseValues();
    }

    private void parseValues()
    {
        //TODO: connection with database and parse values

        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("KSR");
        MongoCollection<Document> collection = database.getCollection("matches");
        //on the bottom we set labels
        for(Value v:values)
        {
            float maxDegree = 0;
            String maxLabel = null;
            for(TrapezoidMembership t:trapezoidMemberships)
            {
                String label = t.getName();
                float degree = t.getDegree(v.getValue());
                if(degree > maxDegree)
                {
                    maxDegree = degree;
                    maxLabel = label;
                }
            }
            v.setLabel(maxLabel);
        }
    }

    public ArrayList<Value> getValues()
    {
        return values;
    }
}
