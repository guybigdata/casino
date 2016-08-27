package Accessors;

import server.RouletaTable;
//import com.mongodb.*;
import org.bson.Document;
import com.mongodb.client.*;
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
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.*;

public class GameTransactionEccessor {

	ConnectionStrings mongoConnection = new ConnectionStrings();
	MongoClient mongoClient = null;

	public void saveTranHistory(RouletaTable gameTransaction) {

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

	public int getNumOfWin(RouletaTable rouletaTable) {

		try {

			mongoClient = new MongoClient(mongoConnection.getConnectionString(), mongoConnection.getConnectionInt());
			DB db = mongoClient.getDB("gameTransactionHistory");
			DBCollection collection = db.getCollection("gameTransaction");

			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("gameResault", "lose"); // need to change to "win"
			whereQuery.put("userID", rouletaTable.getPlayerId());

			BasicDBObject fields = new BasicDBObject();
			fields.put("_id", 1);

			DBCursor cur = collection.find(whereQuery, fields);

			return cur.count();
		} finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				} catch (Exception e) {
				}
		}
	}

	public int getLuckyNum(RouletaTable rouletaTable) {
		MongoClient mongoClient = null;

		try {

			mongoClient = new MongoClient(mongoConnection.getConnectionString(), mongoConnection.getConnectionInt());
			DB db = mongoClient.getDB("gameTransactionHistory");
			DBCollection collection = db.getCollection("gameTransaction");

			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("gameResault", "lose"); // need to change to "win"
			whereQuery.put("userID", rouletaTable.getPlayerId());

			BasicDBObject fields = new BasicDBObject();
			fields.put("winningNumber", 1);

			DBCursor cur = collection.find(whereQuery, fields);

			DBObject mapObj = cur.next();
			int winningNumber = ((Number) mapObj.get("winningNumber")).intValue();

			return winningNumber;

		} finally {
			if (mongoClient != null)
				try {
					mongoClient.close();
				} catch (Exception e) {
				}
		}

	}

}
