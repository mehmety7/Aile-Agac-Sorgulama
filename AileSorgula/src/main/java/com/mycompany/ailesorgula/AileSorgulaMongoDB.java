import com.mongodb.client.*;
import com.mongodb.*;
import java.util.*;

public class Soru2 {
	public static void main(String[] args) {
		Soru2 sample = new Soru2();
		int istenen, ID;
		Scanner reader = new Scanner(System.in);
		System.out.print("Menu \n 0:cikis\n1:soy ağacı sorgula\n2:soyundan gelenleri sorgula\nSeçenek ? ");
		istenen = reader.nextInt();
		System.out.print("ID ? ");
		do{
			if (istenen==0)
				break;
			if (istenen == 1) {
				ID = reader.nextInt();
				sample .ebeveyn(ID);
}
		} while(istenen!=0);
	}
	static void ebeveyn(int idNo) {  
	    
		// Değişkenler
String parentid = null;
int ID = 0;
String isim=null; 

//Bağlantı
		MongoClient mongo = new MongoClient( "localhost" , 27017 ); 		       
		MongoCredential credential = MongoCredential.createCredential("alihanatas", "database", 
		    	  		"password".toCharArray()); 
		
// getdb ve getcollection işlemleri
DB database = mongo.getDB("database");
		DBCollection collection = db.getCollection("aileAgaci");
		
// şarta uygun veriyi sorgulama ve çekme
BasicDBObject sorgu1 = new BasicDBObject();
		sorgu1.put("id", idNo);
		DBCursor cursor1 = collection.find(sorgu1);	
		
// doküman haline getirme / parçalama / değişkenlere atama / yazdırma
DBObject kayıtSatiri = cursor1.next();
		ID = (int) kayıtSatiri.get("id");
		isim= kayıtSatiri.get("isim").toString();
		parentid = kayıtSatiri.get("parentid").toString();
		System.out.println( "ID:"
+ ID 
+ " ("
 + isim 
+ ") "
 + "Soy ağacı: ");

DBCursor cursor2 = null;
BasicDBObject sorgu2 = new BasicDBObject();

// döngü içinde sorgulama / yazdırma
		do {
		    	sorgu2.put("id", Integer.parseInt(parentid));
		      	cursor2 = collection.find(sorgu2);
			while ( cursor2.hasNext()) {
				kayıtSatiri = cur2.next();
				ID = (int) record.get("id");
				isim = kayıtSatiri.get("isim").toString();
				System.out.println( ID + "(" + isim + ") ");
				parentid = kayıtSatiri.get("parentid").toString();
			}
		}while(parentid!=null);
		      
	}	
} 
