import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DBController {
	DB database;
	
	public DBController(){
		MongoClient mongoClient = new MongoClient();
		database = mongoClient.getDB("contacts");
		DBCollection cards = database.getCollection("cards");
	}
	
	private static String view(String info){
		
		return "";
	}
	
	private static void add(String info){
//		DBObject card = new BasicDBObject("first_name","Connor")
//				.append("last_name":"Barnhart")
//				.append("company":"Align")
//				.append("number":"5138469660");
//		collection.insert(card);
//		  db.cards.insertOne({first_name:"Connor",last_name:"Barnhart",company:"Align",number:"5138469660"})
	}
	
	private static String update(String info){
//		DBObject card = new BasicDBObject("first_name","Connor")
//		.append("last_name":"Barnhart")
//		.append("company":"Align")
//		.append("number":"5138469660");


//		db.cards.update({"first_name":"Connor"},{$set:{"company":"MetLife"}})
		return "";
	}
	
	private static void remove(String info){
//		DBObject card = new BasicDBObject("first_name","Connor")
//		.append("last_name":"Barnhart")
//		.append("company":"Align")
//		.append("number":"5138469660");

//		collection.remove(card);
		//db.cards.remove({"first_name":"Connor"})
		
	}
	
	private static void exit(){
		
	}
}
