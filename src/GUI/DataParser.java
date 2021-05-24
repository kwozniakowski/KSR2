package GUI;
import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Value;
import Memberships.Membership;
import Memberships.TrapezoidMembership;
import org.json.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public DataParser(){};
    public ArrayList<Attribute> parse()
    {
        ArrayList<Attribute> attributes = new ArrayList<>();
        ArrayList<Value> winner_rank_values = new ArrayList<>();
        ArrayList<Value> loser_rank_values = new ArrayList<>();
        ArrayList<Value> winner_age_values = new ArrayList<>();
        ArrayList<Value> winner_aces_values = new ArrayList<>();
        ArrayList<Value> loser_aces_values = new ArrayList<>();
        ArrayList<Value> winner_df_values = new ArrayList<>();
        ArrayList<Value> loser_df_values = new ArrayList<>();
        ArrayList<Value> first_serve_values = new ArrayList<>();
        ArrayList<Value> winner_height_values = new ArrayList<>();
        ArrayList<Value> match_length_values = new ArrayList<>();
        MongoClient mongoClient = MongoClients.create("mongodb+srv://user:user@maincluster.kqq8z.mongodb.net/KSR2?retryWrites=true&w=majority");
        MongoCollection<Document> collection = mongoClient.getDatabase("KSR2").getCollection("matches");
        List<Document> matches = collection.find().into(new ArrayList<>());
        matches.forEach(d ->
        {

            String jsonString = d.toJson();
            JSONObject obj = new JSONObject(jsonString);
            try{
                winner_rank_values.add(new Value(obj.getInt("winner_rank"), obj.getJSONObject("_id").getString("$oid")));
                loser_rank_values.add(new Value(obj.getInt("loser_rank"), obj.getJSONObject("_id").getString("$oid")));
                winner_age_values.add(new Value(obj.getInt("winner_age"), obj.getJSONObject("_id").getString("$oid")));
                winner_aces_values.add(new Value(obj.getInt("w_ace"), obj.getJSONObject("_id").getString("$oid")));
                loser_aces_values.add(new Value(obj.getInt("l_ace"), obj.getJSONObject("_id").getString("$oid")));
                winner_df_values.add(new Value(obj.getInt("w_df"), obj.getJSONObject("_id").getString("$oid")));
                loser_df_values.add(new Value(obj.getInt("l_df"), obj.getJSONObject("_id").getString("$oid")));
                first_serve_values.add(new Value((float) obj.getInt("w_1stIn") / (float) obj.getInt("w_svpt") * 100, obj.getJSONObject("_id").getString("$oid")));
                winner_height_values.add(new Value(obj.getInt("winner_ht"), obj.getJSONObject("_id").getString("$oid")));
                match_length_values.add(new Value(obj.getInt("minutes"), obj.getJSONObject("_id").getString("$oid")));
            }
            catch (Exception e)
            {
            }

        });



        attributes.add(new Attribute("Ranking zwyciezcy",winner_rank_values,getMemberships().get(0)));
        attributes.add(new Attribute("Ranking przegranego",loser_rank_values,getMemberships().get(1)));
        attributes.add(new Attribute("Wiek zwyciezcy",winner_age_values,getMemberships().get(2)));
        attributes.add(new Attribute("Liczba asow zwyciezcy",winner_aces_values,getMemberships().get(3)));
        attributes.add(new Attribute("Liczba DF zwyciezcy",winner_df_values,getMemberships().get(5)));
        attributes.add(new Attribute("Liczba asow przegranego",loser_aces_values,getMemberships().get(4)));
        attributes.add(new Attribute("Liczba DF przegranego",loser_df_values,getMemberships().get(6)));
        attributes.add(new Attribute("Procent pierwszego serwisu zwyciezcy",first_serve_values,getMemberships().get(7)));
        attributes.add(new Attribute("Wzrost zwyciezcy",winner_height_values,getMemberships().get(8)));
        attributes.add(new Attribute("Dlugosc meczu",match_length_values,getMemberships().get(9)));


        return attributes;
    }
    public ArrayList<ArrayList<Membership>> getMemberships()
    {
        ArrayList<ArrayList<Membership>> memberships = new ArrayList<>();

        for(int i = 0; i < 10; i++)
        {
            memberships.add(new ArrayList<>());
        }
        //ranking zwyciezcy
        memberships.get(0).add(new TrapezoidMembership("Topowy",0,1,8,16));
        memberships.get(0).add(new TrapezoidMembership("Wysoki",8, 12, 24, 36));
        memberships.get(0).add(new TrapezoidMembership("Sredni",32, 38, 78, 100));
        memberships.get(0).add(new TrapezoidMembership("Niski",88, 104, 160, 200));
        memberships.get(0).add(new TrapezoidMembership("Bardzo niski",180, 210, 2101, 2200));
        //ranking przegranego
        memberships.get(1).add(new TrapezoidMembership("Topowy",0,1,8,16));
        memberships.get(1).add(new TrapezoidMembership("Wysoki",8, 12, 24, 36));
        memberships.get(1).add(new TrapezoidMembership("Sredni",32, 38, 78, 100));
        memberships.get(1).add(new TrapezoidMembership("Niski",88, 104, 160, 200));
        memberships.get(1).add(new TrapezoidMembership("Bardzo niski",180, 210, 2101, 2200));
        //wiek zwyciezcy
        memberships.get(2).add(new TrapezoidMembership("Bardzo mlody",10, 16.52f,18, 19));
        memberships.get(2).add(new TrapezoidMembership("Mlody",18,19,22,25));
        memberships.get(2).add(new TrapezoidMembership("W srednim wieku",23,26,31,34));
        memberships.get(2).add(new TrapezoidMembership("Stary",32,35,  37.24f, 39));
        //liczba asow zwyciezcy
        memberships.get(3).add(new TrapezoidMembership("Mało",-1, 0, 6, 9 ));
        memberships.get(3).add(new TrapezoidMembership("Srednio",8, 11, 17, 20));
        memberships.get(3).add(new TrapezoidMembership("Duzo",16,20,61,70));
        //liczba asow przegranego
        memberships.get(4).add(new TrapezoidMembership("Mało",-1, 0, 5, 8 ));
        memberships.get(4).add(new TrapezoidMembership("Srednio",7, 9, 15, 18));
        memberships.get(4).add(new TrapezoidMembership("Duzo",15,18,52,60));
        //liczba DF przegranego
        memberships.get(5).add(new TrapezoidMembership("Mało",-1, 0, 3, 4 ));
        memberships.get(5).add(new TrapezoidMembership("Srednio",3,5,6,7));
        memberships.get(5).add(new TrapezoidMembership("Duzo",6,8,10,15));
        memberships.get(5).add(new TrapezoidMembership("Bardzo duzo",12,15,20,25));
        //liczba DF zwyciezcy
        memberships.get(6).add(new TrapezoidMembership("Mało",-1, 0, 3, 4 ));
        memberships.get(6).add(new TrapezoidMembership("Srednio",3,5,6,7));
        memberships.get(6).add(new TrapezoidMembership("Duzo",6,8,10,15));
        memberships.get(6).add(new TrapezoidMembership("Bardzo duzo",12,15,23,25));
        //Procent pierwszego serwisu zwyciezcy
        memberships.get(7).add(new TrapezoidMembership("Bardzo slaby",-1, 0, 40, 50 ));
        memberships.get(7).add(new TrapezoidMembership("Slaby",40,50,55,60));
        memberships.get(7).add(new TrapezoidMembership("Sredni",55,60,65,70));
        memberships.get(7).add(new TrapezoidMembership("Dobry",65,75,80,90));
        memberships.get(7).add(new TrapezoidMembership("Bardzo dobry",83, 90, 100, 110));
        //Wzrost zwyciezcy
        memberships.get(8).add(new TrapezoidMembership("Niski",160,163,174,178 ));
        memberships.get(8).add(new TrapezoidMembership("Sredni",175, 180, 188, 190  ));
        memberships.get(8).add(new TrapezoidMembership("Wysoki",188, 192, 198, 202 ));
        memberships.get(8).add(new TrapezoidMembership("Olbrzymi",200, 204, 208, 210 ));
        //Dlugosc meczu
        memberships.get(9).add(new TrapezoidMembership("Bardzo krotki",-1, 0,50, 60));
        memberships.get(9).add(new TrapezoidMembership("Krotki",50,70,75,90  ));
        memberships.get(9).add(new TrapezoidMembership("Zwykly",80,100,120,130 ));
        memberships.get(9).add(new TrapezoidMembership("Dlugi",120,150,200,210 ));
        memberships.get(9).add(new TrapezoidMembership("Bardzo dlugi",180,210,353,400 ));
        return memberships;
    }

}
