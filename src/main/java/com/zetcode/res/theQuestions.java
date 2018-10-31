package com.zetcode.res;

import javax.ws.rs.Path;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@Path("questions2")
public class theQuestions {
    
    public static void main(String[] args) {
        
        try (MongoClient client = new MongoClient("localhost", 3001)) {
            
            MongoDatabase database = client.getDatabase("meteor");
            MongoCollection<Document> collection = database.getCollection("questions");
            
            Document query = new Document();
            
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