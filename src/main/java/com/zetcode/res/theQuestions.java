package com.zetcode.res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@Path("testing")
public class theQuestions {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestions() {
        MongoClient mongoClient = new MongoClient( "localhost" , 3001 );
        MongoDatabase database = mongoClient.getDatabase("meteor");
        MongoCollection<Document> collection = database.getCollection("questions");
        MongoCursor<Document> cursor = collection.find().iterator();

        String returnMe = "";
        try {
            while (cursor.hasNext()) {
                Document holder = cursor.next(); 
                String jsonOut = cursor.next().toJson();
                Integer category =  (Integer)holder.get("Category");
                String Text =  (String)holder.get("Text");
                String leftText =  (String)holder.get("LeftText");
                // String sumOfAnswers =  (String)holder.get("SumOfAnswers");

returnMe += "Category: "+category+"\nSubject: "+Text+"\nQuestion: "+leftText+"\n ------------------------------------ \n"+jsonOut+"\n\n";
            }
        } finally {
            cursor.close();
        }
        return returnMe;
    }
}