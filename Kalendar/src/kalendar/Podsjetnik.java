package kalendar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Podsjetnik {
	private int dan;
	private int mjesec;
	private int godina;
	private String tekst;

	public Podsjetnik() {
	}

	public Podsjetnik(int dan, int mjesec, int godina, String tekst) {
		this.dan = dan;
		this.mjesec = mjesec;
		this.godina = godina;
		this.tekst = tekst;
	}

	public int getGodina() {
		return godina;
	}

	public int getMjesec() {
		return mjesec;
	}

	public int getDan() {
		return dan;
	}

	public String getTekst() {
		return tekst;
	}
	@Override
	public String toString() {
		return getDan() + "/" + getMjesec() + "/" + getGodina() + "\n"
				+ getTekst();

	}

	/*
	 * Metod daje mogucnost korisniku da unese tekst i datum podsjetnika
	 * provjerava da li su unesene vrijednosti ispravne, a zatim novi podsjetnik
	 * dodaje u listu
	 */
	public static void upis(ArrayList<Podsjetnik> podsjetnik) {
		Scanner input = new Scanner(System.in);
		// Varijable koje predstavljaju datum podsjetnika
		int godina;
		int mjesec;
		int dan;

		System.out.println("Upisite tekst podsjetnika");
		String tekst = input.nextLine();

		/*
		 * Korisnik mora da unese godinu 2015-2020 kako se podsjetnik ne bi
		 * odnosio na proslost ili predaleku buducnost
		 */
		do {
			System.out.println("Upisite godinu(2015-2020):");
			godina = input.nextInt();
			if (godina < 2015 || godina > 2020)
				System.out.println("Pograsan unos,pokusajte ponovo");
			else
				break;
		} while (true);

		// Broj mjeseca mora da bude izmedju 0(Januar) i 11(Decembar)
		do {
			System.out.println("Upisite mjesec(0-Januar...11-Decembar)");
			mjesec = input.nextInt();
			if (mjesec < 0 || mjesec > 11)
				System.out.println("Pograsan unos,pokusajte ponovo");
			else
				break;
		} while (true);
		/*
		 * Vrijednost unesenog dana mora biti veca od 0 i manja od ukupnog broja
		 * dana za uneseni mjesec i godinu
		 */
		int ispravanDan = Kalendar.brojDanaUMjesecu(godina, mjesec);
		System.out.println("Upisite dan u mjesecu");
		do {
			dan = input.nextInt();
			if (dan < 1 || dan > ispravanDan)
				System.out.println("Pogresan unos,pokusajte ponovo");
			else
				break;
		} while (true);

		// Dodajemo kreirani objekat sa unesenim vrijednostima u listu
		podsjetnik.add(new Podsjetnik(dan, mjesec, godina, tekst));

		// Pozivamo metod koji snima podsjetnik
		sacuvajPodsjetnik(podsjetnik);
	}

	public static void sacuvajPodsjetnik(ArrayList<Podsjetnik> podsjetnik) {
		// Fajl u koji snimamo podsjetnike
		File file = new File("podsjetnik.txt");
		PrintWriter pw = null;

		try {
			// Kreirenje PrintWriter-a
			pw = new PrintWriter(file);
			// Snimanje objekata u fajl
			for (Podsjetnik p : podsjetnik) {
				pw.println(p.getDan() + " " + p.getMjesec() + " "
						+ p.getGodina() + " " + p.getTekst());
			}

		} catch (IOException e) {
			e.printStackTrace();
			// Naredbom finally obezbjedjujemo zatvaranje PrintWriter-a
		} finally {
			pw.close();
		}
	}

	// Metod za ispisivanje unesenih podsjetnika
	public static void printPodsjetnik(ArrayList<Podsjetnik> podsjetnik) {
		for (int i = 0; i < podsjetnik.size(); i++) {
			System.out.println(i + " : " + podsjetnik.get(i));
		}
	}

	public static ArrayList<Podsjetnik> ucitajPodsjetnik() {
		File file = new File("Podsjetnik.txt");

		/*
		 * Otvaramo Scanner za iscitavanje podataka iz fajla od kojih kreiramo
		 * novi objekat i pridruzujemo ga ArraList-i
		 */
		Scanner input = null;

		ArrayList<Podsjetnik> podsjetnik = new ArrayList<>();
		try {
			input = new Scanner(file);
			while (input.hasNext()) {
				podsjetnik.add(new Podsjetnik(input.nextInt(), input.nextInt(),
						input.nextInt(), input.nextLine().trim()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}

		return podsjetnik;
	}

	// Metod koji sluzi za brisanje podsjetnika
	public static void izbrisatiPodsjetnik(ArrayList<Podsjetnik> podsjetnik) {
		Scanner in = new Scanner(System.in);
		/*
		 * Printamo listu podsjetnika i dajemo mogucnost korisniku da izabere
		 * podsjetnik za brisanje
		 */
		printPodsjetnik(podsjetnik);

		System.out.println("Upisite broj podsjetnika za brisanje: ");
		int broj = in.nextInt();
        //Provjeravamo da li je korisnikov unos ispravan
		if (broj > podsjetnik.size() || broj < 0) {
			System.out.println("Podsjetnik pod izabranim brojem ne postoji");
		} else {
           podsjetnik.remove(broj);
       //Snimamo promjene
		  sacuvajPodsjetnik(podsjetnik);
		}
	}
}
