import java.sql.*;
import java.util.*;

public class Soru1 {
	public static void main(String[] args) {
		Soru1 sample = new Soru1();
		int istenen, ID;
		Scanner reader = new Scanner(System.in);
		System.out.print("Menu \n 0:cikis\n1:soy ağacı sorgula\n2:soyundan gelenleri sorgula\nSeçenek ? ");
		istenen = reader.nextInt();
		System.out.print("ID ? ");
		do{
			if (istenen ==0)
				break;
			if (istenen == 1) {
				ID = reader.nextInt();
				sample .ebeveyn(ID);
}
			if (istenen == 2 ){
				ID = reader.nextInt();
				sample.cocuk(ID);
}
		} while(istenen!=0);
	}
	void ebeveyn(int idNo) {
		String parentID = null;
		int ID = 0;
String isim = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection  c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/postgres",
							"postgres", "alihan123");
			c.setAutoCommit(false);
			PreparedStatement  sorgu1 = c.prepareStatement("SELECT * FROM aileAgaci WHERE id=? ;");
			sorgu1.setInt(1, idNo);
			ResultSet rs1 = sorgu1.executeQuery(); 
			while ( rs1.next() ) {
				ID = rs1.getInt("id");
				isim = rs1.getString("name");
				parentID = rs1.getString("parentid");
				System.out.println( "ID:" + ID + "(" + isim + ") " + "Soy ağacı: ");
			}
			
PreparedStatement sorgu2 = null;
			ResultSet rs2 = null;
			do {
				sorgu2 = c.prepareStatement("SELECT * FROM aileAgaci WHERE id=? ;");
				sorgu2.setInt(1, Integer.parseInt(parentID));
				rs2 = sorgu2.executeQuery();
				while ( rs2.next() ) {
						ID = rs2.getInt("id");
						isim = rs2.getString("isim ");
System.out.println( "ID:" + ID + "(" + isim + ") ");					
						parentID =rs2.getString("parentid");
				}
			}while(parentID!=null);
			c.close();
		 } catch ( Exception e ) {System.exit(0);}
	}
	void cocuk(int idNo) {
		List<Integer> numaralar = new ArrayList<Integer>();
		int ID = 0;
		String isim = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection  c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/postgres",
					"postgres", " alihan123");
			c.setAutoCommit(false);
			PreparedStatement  sorgu1 = c.prepareStatement("SELECT * FROM aileAgaci WHERE id=? ;");
			sorgu1.setInt(1, idNo);
			ResultSet rs1 = sorgu1.executeQuery(); 
			while ( rs1.next() ) {
				ID = rs1.getInt("id");
				isim = rs1.getString("name");
				System.out.println( "ID:" + ID + "(" + isim + ") " + "'in soyundan gelenler: ");
			}
			PreparedStatement sorgu2 = null;
			ResultSet rs2 = null;
			do {
			sorgu2 = c.prepareStatement("SELECT * FROM aileAgaci WHERE parentid=? ;");
				sorgu2.setString(1, Integer.toString(idNo));
				rs2 = sorgu2.executeQuery();
				while ( rs2.next() ) {
						ID = rs2.getInt("id");
						isim = rs2.getString("isim");
						System.out.println( "ID:" + ID + "(" + isim + ") ");
						numaralar.add(ID);
				}
				if(numaralar.size() != 0) {
					idNo= numaralar.get(0);
					numaralar.remove(0);
				}

			}while(numaralar.size() != 0);			
			c.close();

		 	} catch ( Exception e ) {System.exit(0);}
 
	}
}
 

