package Accessors;
import com.mongodb.*;
import org.bson.Document;
import com.mongodb.client.*;
//import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Sorts.*;
//import java.io.FileInputStream;
//import java.io.IOException;
import java.util.*;

import static java.util.Arrays.asList;

//import static java.util.concurrent.TimeUnit.SECONDS;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
//import java.text.ParseException;
import java.text.SimpleDateFormat;




public class MongoManager{
	
	MongoClient mongoClient = null;
	
	public void saveToMongo() throws ParseException, UnknownHostException{
		
		
		try{
			mongoClient = new MongoClient( "localhost" , 27017 );
			MongoDatabase db = mongoClient.getDatabase("GameTransactionHistory");
			//MongoCollection<Document> table = db.getCollection("gameLog");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			db.getCollection("restaurants").insertOne(new Document("address",new Document()
		                        .append("street", "2 Avenue")
		                        .append("zipcode", "10075")
		                        .append("building", "1480")
		                        .append("coord", asList(-73.9557413, 40.7720266)))
		                .append("borough", "Manhattan")
		                .append("cuisine", "Italian")
		                .append("grades", asList(
		                        new Document()
		                                .append("date", format.parse("2014-10-01T00:00:00Z"))
		                                .append("grade", "A")
		                                .append("score", 11),
		                        new Document()
		                                .append("date", format.parse("2014-01-16T00:00:00Z"))
		                                .append("grade", "B")
		                                .append("score", 17)))
		                .append("name", "Vella")
		                .append("restaurant_id", "41704620"));
			//boolean auth = db.authenticate(guyhome,12qwaszx);
			//	System.out.println("Authentication: "+auth);
		}finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				}catch (Exception e){
			}
		}
	}
}


