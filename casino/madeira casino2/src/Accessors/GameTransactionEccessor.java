package Accessors;
import com.mongodb.*;
import org.bson.Document;
import com.mongodb.client.*;

import server.RouletaTable;

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




public class GameTransactionEccessor{
	
	MongoClient mongoClient = null;
	
	public void saveTranHistory(RouletaTable gameTransaction){
		
		
		try{
			mongoClient = new MongoClient( "52.164.245.164" , 27017 );
			MongoDatabase db = mongoClient.getDatabase("gameTransactionHistory");
			//MongoCollection<Document> table = db.getCollection("gameLog");
			//DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			db.getCollection("gameTransaction").insertOne(new Document()
		                        .append("gameType", gameTransaction.getGametype())
		                        .append("userID", gameTransaction.getPlayerId())
		                        .append("gameResault", gameTransaction.getGameResault())
		                        .append("amount", gameTransaction.getAmount())
		                        .append("gambleOption", gameTransaction.getGambleOption())
		                        .append("gambleNumber", gameTransaction.getGamblNumber())
		                        .append("gameAmount", gameTransaction.getGambleAmount())
		                        .append("winningNumber", gameTransaction.getWiningNumber())
		                        );
//		                        .append("coord", asList(-73.9557413, 40.7720266)))
//		                .append("borough", "Manhattan")
//		                .append("cuisine", "Italian")
		            
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


