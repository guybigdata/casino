package Accessors;

import server.RouletaTable;

//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.List;
//import com.mongodb.WriteResult;
//import com.google.gson.Gson;
//import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Sorts.*;
//import java.io.FileInputStream;
//import java.io.IOException;
//import static java.util.Arrays.asList;
//import static java.util.concurrent.TimeUnit.SECONDS;
//import java.net.UnknownHostException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import com.mongodb.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.*;
import com.sun.org.apache.bcel.internal.classfile.DescendingVisitor;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2.DescendantIterator;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;

import static java.util.Arrays.asList;
import java.util.*;
import static com.mongodb.client.model.Filters.*;

public class GameTransactionEccessor {

	ConnectionStrings mongoConnection = new ConnectionStrings();
	MongoClient mongoClient = null;
	public int winningNumber;
	
	public void saveNumberTranHistory(RouletaTable gameTransaction) {

		try {
			
			mongoClient = new MongoClient(mongoConnection.getConnectionString(), mongoConnection.getConnectionInt());
			MongoDatabase db = mongoClient.getDatabase("gameTransactionHistory");

			Date now = new Date();
			BasicDBObject timeNow = new BasicDBObject("created at", now);

			db.getCollection("gameTransaction")
					.insertOne(new Document().append("gameType", gameTransaction.getGametype())
							.append("userID", gameTransaction.getPlayerId())
							.append("gameResault", gameTransaction.getGameResault())
							.append("amount", gameTransaction.getAmount())
							.append("gambleOption", gameTransaction.getGambleOption())
							.append("gambleNumber", gameTransaction.getGamblNumber())
							.append("gameAmount", gameTransaction.getGambleAmount())
							.append("winningNumber", gameTransaction.getWiningNumber()).append("created at", timeNow));

		} finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				} catch (Exception e) {
				}
		}
	}
	
	public void saveColorTranHistory(RouletaTable gameTransaction) {

		try {
			
			mongoClient = new MongoClient(mongoConnection.getConnectionString(), mongoConnection.getConnectionInt());
			MongoDatabase db = mongoClient.getDatabase("gameTransactionHistory");

			Date now = new Date();
			BasicDBObject timeNow = new BasicDBObject("created at", now);

			db.getCollection("gameTransaction")
					.insertOne(new Document().append("gameType", gameTransaction.getGametype())
							.append("userID", gameTransaction.getPlayerId())
							.append("gameResault", gameTransaction.getGameResault())
							.append("amount", gameTransaction.getAmount())
							.append("gambleOption", gameTransaction.getGambleOption())
							.append("gambleColor", gameTransaction.getGambleColor())
							.append("gameAmount", gameTransaction.getGambleAmount())
							.append("winningColor", gameTransaction.getWinningColor()).append("created at", timeNow));

		} finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				} catch (Exception e) {
				}
		}
	}

	public long getNumOfWin(RouletaTable rouletaTable) {

		try {

			mongoClient = new MongoClient(mongoConnection.getConnectionString(), mongoConnection.getConnectionInt());
			MongoDatabase db = mongoClient.getDatabase("gameTransactionHistory");
			MongoCollection<Document> collection = db.getCollection("gameTransaction");

			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("gameResault", "win"); 
			whereQuery.put("userID", rouletaTable.getPlayerId());

			BasicDBObject fields = new BasicDBObject();
			fields.put("_id", 1);

			long cur = collection.count(whereQuery);

			return cur;
		} finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				} catch (Exception e) {
				}
		}
	}

	public void getLuckyNu(RouletaTable rouletaTable) {
		MongoClient mongoClient = null ;

		try {

			mongoClient = new MongoClient(mongoConnection.getConnectionString(), mongoConnection.getConnectionInt());
			MongoDatabase db = mongoClient.getDatabase("gameTransactionHistory");
			MongoCollection<Document> collection = db.getCollection("gameTransaction");

			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("gameResault", "win"); 
			whereQuery.put("userID", rouletaTable.getPlayerId());

			BasicDBObject fields = new BasicDBObject();
			fields.put("winningNumber", 1);
			
			FindIterable<Document> iterable = collection.find(whereQuery).sort(new BasicDBObject("winningNumber",1));
		
			iterable.forEach(new Block<Document>() {
			            @Override
			            public void apply(Document document) {
			                System.out.println(document.get("winningNumber"));
			                
			            }
			        });
			 
		//return myDoc;
		} finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				} catch (Exception e) {
				}
		}
	}

	public Object getHighlyWinAmount(RouletaTable rouletaTable) {
		MongoClient mongoClient = null;

		try {

			mongoClient = new MongoClient(mongoConnection.getConnectionString(), mongoConnection.getConnectionInt());
			MongoDatabase db = mongoClient.getDatabase("gameTransactionHistory");
			MongoCollection<Document> collection = db.getCollection("gameTransaction");
			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("gameResault", "lose"); // need to change to "win"
			whereQuery.put("userID", rouletaTable.getPlayerId());

			BasicDBObject fields = new BasicDBObject();
			fields.put("amount", 1);
			BasicDBObject sort = new BasicDBObject();
			sort.put("amount", -1);
			
			Document myDoc = collection.find(exists("amount")).sort(sort).first();
			Object highlyWinAmount = myDoc.get("amount");
			return highlyWinAmount;	
					
		} finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				} catch (Exception e) {
				}
		}
	}
}
