package Accessors;
import com.mongodb.*;
import org.bson.Document;
import com.mongodb.client.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
 
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import server.RouletaTable;
//import com.google.gson.Gson;
//import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Sorts.*;
//import java.io.FileInputStream;
//import java.io.IOException;
import java.util.*;

//import static java.util.Arrays.asList;

//import static java.util.concurrent.TimeUnit.SECONDS;
//import java.net.UnknownHostException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;




public class GameTransactionEccessor{
	
	MongoClient mongoClient = null;
	
	public void saveTranHistory(RouletaTable gameTransaction){
		
		
		try{
			mongoClient = new MongoClient( "52.164.245.164" , 27017 );
			MongoDatabase db = mongoClient.getDatabase("gameTransactionHistory");
			//MongoCollection<Document> table = db.getCollection("gameLog");
			Date now = new Date();
			BasicDBObject timeNow = new BasicDBObject("created at", now);	
			
			//DateFormat format = new SimpleDateFormat(get"yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			db.getCollection("gameTransaction").insertOne(new Document()
		                        .append("gameType", gameTransaction.getGametype())
		                        .append("userID", gameTransaction.getPlayerId())
		                        .append("gameResault", gameTransaction.getGameResault())
		                        .append("amount", gameTransaction.getAmount())
		                        .append("gambleOption", gameTransaction.getGambleOption())
		                        .append("gambleNumber", gameTransaction.getGamblNumber())
		                        .append("gameAmount", gameTransaction.getGambleAmount())
		                        .append("winningNumber", gameTransaction.getWiningNumber())
		                        .append("created at",timeNow)
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

	public int getNumOfWin(RouletaTable rouletaTable) {
		MongoClient mongoClient = null;
	
		try {

			 mongoClient = new MongoClient("52.164.245.164", 27017);
			 DB db = mongoClient.getDB("gameTransactionHistory");
			 DBCollection collection = db.getCollection("gameTransaction");
			 
			
			 BasicDBObject whereQuery = new BasicDBObject();
			  whereQuery.put("gameResault", "lose"); //need to change to "win"
			  whereQuery.put("userID", rouletaTable.getPlayerId());
			  
			  BasicDBObject fields = new BasicDBObject();
			  fields.put("_id", 1);
			  
			  DBCursor cur = collection.find(whereQuery,fields);
			  		 
			  return cur.count();
			  
			 
			 
		}
		finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				}catch (Exception e){
			}
		}	
	}
	
	public int getLuckyNum(RouletaTable rouletaTable){
		MongoClient mongoClient = null;
		
		try {

			 mongoClient = new MongoClient("52.164.245.164", 27017);
			 DB db = mongoClient.getDB("gameTransactionHistory");
			 DBCollection collection = db.getCollection("gameTransaction");
			 
			
			 BasicDBObject whereQuery = new BasicDBObject();
			  whereQuery.put("gameResault", "lose"); //need to change to "win"
			  whereQuery.put("userID", rouletaTable.getPlayerId());
			  
			  BasicDBObject fields = new BasicDBObject();
			  fields.put("winningNumber", 1);
			  
			  DBCursor cur = collection.find(whereQuery,fields);
			  		 
			  //System.out.println(cur.next());
			  DBObject mapObj = cur.next();
			  int winningNumber = ((Number) mapObj.get("winningNumber")).intValue();
			  
			  return winningNumber;
			  
			 //return cur.next();
			 
		}
		finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				}catch (Exception e){
			}
		}
			
	}
		
}



