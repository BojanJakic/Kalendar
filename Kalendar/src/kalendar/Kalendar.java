package kalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Kalendar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);

		ArrayList<Podsjetnik> podsjetnik = Podsjetnik.ucitajPodsjetnik();

		/*
		 * Petlja omogucava korisniku da bira opcije iz menija sve dok je ne
		 * prekine izborom odgovarajuce opcije
		 */
		while (true) {
			System.out.println(meni());
			int unos = in.nextInt();
            //Ispis kalendara
			if (unos == 1)
				kalendar();
           //Unos podsjetnika
			else if (unos == 2)
				Podsjetnik.upis(podsjetnik);
          //Ispis unesenih podsjetnika
			else if (unos == 3)
				Podsjetnik.printPodsjetnik(podsjetnik);
           //Brisanje podsjetnika
			else if (unos == 4)
				Podsjetnik.izbrisatiPodsjetnik(podsjetnik);
            //Prekid korisnikovog unosa
			else if (unos == 5)
				break;
            //Poruka u slucaju nepravilnog unosa
			else
				System.out.println("Pogresan izbor");
		}

	}

	// Metod koji vraca korisnicki meni sa opcijama
	public static String meni() {

		return "   * MENI *" + "\nIzaberite opciju:" + "\n1.Ispis kalendara"
				+ "\n2.Dodati podsjetnik" + "\n3.Pogledati podsjetnike"
				+ "\n4.Izbrisati podsjetnik(e)" + "\n5.Exit";

	}

	public static void kalendar() {
		Scanner in = new Scanner(System.in);
		int godina; // Unesena godin
		int mjesec; // Uneseni mjesec
		int prviDan = 1; // Prvi dan u mjesecu

		// Provjera korisnikovog unosa
		while (true) {
			System.out.println("Upisite godinu i mjesec:");
			godina = in.nextInt();
			mjesec = in.nextInt();
			if (godina <= 0 || mjesec < 0 || mjesec > 11)
				System.out.println("Pogresan unos, pokusajte ponovo");
			else
				break;
		}

		// Formiramo objekat preko konstruktora koji kao parametre prima godinu,
		// mjesec i prvi dan u mjesecu
		Calendar c = new GregorianCalendar(godina, mjesec, prviDan);

		c.set(Calendar.YEAR, godina);
		c.set(Calendar.MONTH, mjesec);
		/*
		 * Varijabli prviDan dodjeljujemo konkretnu vrijednost za unesenu godinu
		 * i mjesec (nedelja - 1, ponedeljak - 2...)
		 */
		prviDan = c.get(Calendar.DAY_OF_WEEK);

		// Pozivamo metod koji ispisuje kalendar za korisnikove vrijednosti
		print(godina, mjesec, prviDan);
	}

	public static void print(int godina, int mjesec, int prviDan) {

		// Stampamo zaglavlje kalendara
		System.out.println("     " + godina + "," + imeMjeseca(mjesec));
		System.out.println("-------------------------------");
		System.out.println("  Ned Pon Uto Srj Cet Pet Sub");
		int datum = 1;
		// Stampamo tijelo kalendara
		for (int i = 0; i < brojDanaUMjesecu(godina, mjesec) + prviDan; i++) {
			if (i < prviDan)
				System.out.print("    ");
			else {
				System.out.printf("%4d", datum);
				datum++;
			}
			if (i % 7 == 0)
				System.out.println();
		}

	}

	// metod koji odredjuje ukupan broj dana za unesenu vrijednost mjeseca
	public static int brojDanaUMjesecu(int godina, int mjesec) {
		int broj = 0;
		switch (mjesec) {
		// Mjeseci koji imaju 31 dan
		case 0:
		case 2:
		case 4:
		case 6:
		case 7:
		case 9:
		case 11:
			broj = 31;
			break;
		// Mjeseci koji imaju 30 dana
		case 3:
		case 5:
		case 8:
		case 10:
			broj = 30;
			break;
		// Ukoliko je mjesec februar, provjeravamo da li je godine prestupna
		case 1:
			if (isLeap(godina))
				broj = 29;
			else
				broj = 28;
			break;

		}
		return broj;
	}

	// Metod odredjuje ime mjeseca na osnovu unesene numericke vrijednosti
	public static String imeMjeseca(int mjesec) {
		String s = "";
		switch (mjesec) {
		case 0:
			s = "Januar";
			break;
		case 1:
			s = "Februar";
			break;
		case 2:
			s = "Mart";
			break;
		case 3:
			s = "April";
			break;
		case 4:
			s = "Maj";
			break;
		case 5:
			s = "Jun";
			break;
		case 6:
			s = "Jul";
			break;
		case 7:
			s = "Avgust";
			break;
		case 8:
			s = "Septembar";
			break;
		case 9:
			s = "Oktobar";
			break;
		case 10:
			s = "Novembar";
			break;
		case 11:
			s = "Decembar";
			break;
		}
		return s;
	}

	// Metod provjerava da li je godina prestupna
	public static boolean isLeap(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}

}