package com.zetcode.res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@Path("questions")
public class ourQuestions {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static void main(String[] args) {
        
        try (MongoClient client = new MongoClient("localhost", 3001)) {
            
            MongoDatabase database = client.getDatabase("meteor");
            MongoCollection<Document> collection = database.getCollection("questions");
            
            Document query = new Document();
            Document projection = new Document();
            projection.append("LeftText", "$LeftText");
            
            Block<Document> processBlock = new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    System.out.println(document);
                }
            };
            
            collection.find(query).forEach(processBlock);
            
        } catch (MongoException e) {
            // handle MongoDB exception
        }
    }
}
