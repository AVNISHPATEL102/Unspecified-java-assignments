// ----------------------------------------------------------
// Assignment# (1)
// Written by: Avnish Patel  ,  Id : 40024628
// One student Section U
// Comp 249 Ass3
// Due Date MArch 19th,2018
// ----------------------------------------------------------
package PC1;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class BibCreator 
{

	
	public static void main(String[] args) 
	{

		System.out.print("\\\\~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~//\n"
				+ "               Welcome to BibCreator Program!\n"
				+ "//~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\\\\\n\n"
				+ "It will begin by checking files for errors if any ...: \n");
		
		processFilesForValidation(checkFiles());
		
		System.out.print("\\\\~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~//\n"
				+ "                             Processing complete \n"
				+ "//~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\\\\\n\n"
				+ "Will now read files...: ");
		
		readFiles();
		
		System.out.print("\\\\~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~//\n"
				+ "                     Thank you for using program! \n"
				+ "//~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\\\\\n\n");
		
		System.exit(0);

	}

	private static boolean[] checkFiles() 
	{
		boolean[] invalidArticles = new boolean[10];
		
		System.out.println("\n\t     Errors in the files are as follows :- \n");
		
		for (int i = 1; i < 11; i++) 
		{
			
			String five = Integer.toString(i);
			
			String a = "Latex" + five +".bib";
			invalidArticles[i - 1] = true;
			Scanner sc = null;
			String currentLine;
			
			try 
			{
				sc = new Scanner(new FileInputStream(new File(a)));
				while (sc.hasNextLine()) 
				{
					currentLine = sc.nextLine();
					StringTokenizer st = new StringTokenizer(currentLine, "\n");
					while (st.hasMoreTokens())
					{
						String d = st.nextToken();
						if (d.matches(".*\\{\\s*\\}.*"))
						{
							System.out.println("Invalid field: " + d.substring(0,d.indexOf("=")));
							throw new FileInvalidException();
						}
						
					}
				}
			} 
			catch (FileInvalidException e2)
			{
				System.out.println(e2.getMessage() + "...");
				System.out.println("\tThe invalid file is: latex" + i + ".bib");
				System.out.println("");
				invalidArticles[i - 1] = false;
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("Fatal Error, try restarting this program. Goodbye!");
				System.exit(0);
			}
			finally
			{
				sc.close();
			}

		}
		return invalidArticles;
	}

	

	private static void processFilesForValidation(boolean[] illegalFiles) 
	{
		System.out.println("\n----------------------------------------------");
		int numberOfInvalidFiles = 0;
		
		for (int i = 1; i < 11; i++) 
		{
			PrintWriter pw = null;
			PrintWriter pw2 = null;
			PrintWriter pw3 = null;
			File[] f = new File[30];
			try 
			{
				pw = new PrintWriter(new FileOutputStream("IEEE" + i + ".txt"));
				pw2 = new PrintWriter(new FileOutputStream("ACM" + i + ".txt"));
				pw3 = new PrintWriter(new FileOutputStream("NJ" + i + ".txt"));
				pw.close();
				pw2.close();
				pw3.close();
				f[i - 1] = new File("IEEE" + i + ".txt");
				f[i + 9] = new File("ACM" + i + ".txt");
				f[i + 19] = new File("NJ" + i + ".txt");
				if (illegalFiles[i - 1] == false) 
				{
					numberOfInvalidFiles++;
					f[i - 1].delete();
					f[i + 9].delete();
					f[i + 19].delete();
				}
			}
			catch (FileNotFoundException e3) 
			{
				// what goes here?
			}
			if (illegalFiles[i - 1] == true) 
			{
				String a = "Latex" + i + ".bib";
				int articles = CheckNumArticles(a);
				String authors[] = new String[articles];
				String journal[] = new String[articles];
				String title[] = new String[articles];
				String year[] = new String[articles];
				String volume[] = new String[articles];
				String number[] = new String[articles];
				String pages[] = new String[articles];
				String doi[] = new String[articles];
				String ISSN[] = new String[articles];
				String month[] = new String[articles];
				authors = getInfo(a, articles, "author=");
				journal = getInfo(a, articles, "journal=");
				title = getInfo(a, articles, "title=");
				year = getInfo(a, articles, "year=");
				volume = getInfo(a, articles, "volume=");
				number = getInfo(a, articles, "number=");
				pages = getInfo(a, articles, "pages=");
				doi = getInfo(a, articles, "doi=");
				ISSN = getInfo(a, articles, "ISSN=");
				month = getInfo(a, articles, "month=");
				
				for (int j = 0; j < articles; j++) 
				{
					String[] author = new String[splitAuthors(authors[j]).length];
					author = splitAuthors(authors[j]);
					String IEEEAuthors = "";
					String ACMAuthors = "";
					String NJAuthors = "";
					ACMAuthors = ACMAuthors + author[0] + " et al. ";
					
					for (int k = 0; k < (splitAuthors(authors[j]).length); k++)
					{
						if (k > 0)
							IEEEAuthors = IEEEAuthors + ", " + author[k];
						else
							IEEEAuthors = IEEEAuthors + author[k];
						if (k > 0)
							NJAuthors = NJAuthors + " & " + author[k];
						else
							NJAuthors = NJAuthors + author[k];
						if (k == (splitAuthors(authors[j]).length) - 1) 
						{
							IEEEAuthors = IEEEAuthors + ".";

						}
					}
					
					WriteIEEE(IEEEAuthors, journal[j], title[j], year[j], volume[j], number[j], pages[j], ISSN[j],
							month[j], i);
					WriteACM(ACMAuthors, journal[j], title[j], year[j], volume[j], number[j], pages[j], ISSN[j],
							month[j], doi[j], j, i);
					WriteNJ(NJAuthors, journal[j], title[j], year[j], volume[j], number[j], pages[j], ISSN[j], month[j],
							i);
				}
			}
		}
		System.out.println("All Files which were invalid are  Created.");
		System.out.println("A total of " + numberOfInvalidFiles + " invalid files were found and are deleted after that");
		System.out.println("All other " + (10 - numberOfInvalidFiles) + " files  have been written");
		System.out.println("----------------------------------------------\n");
	}

	private static int CheckNumArticles(String a) 
	{
		Scanner sc = null;
		int count = 0;
		String currentLine;
		
		try
		{
			sc = new Scanner(new FileInputStream(a));
			while (sc.hasNextLine()) 
			{
				currentLine = sc.nextLine();
				StringTokenizer st = new StringTokenizer(currentLine, "\n");
				while (st.hasMoreTokens()) {
					String d = st.nextToken();
					// System.out.println(d);
					if (d.length() > 8)
						if (d.substring(0, 8).equals("@ARTICLE")) 
						{
							count++;
						}
					if (d.length() > 11)
						if (d.substring(0, 11).equals("﻿@ARTICLE"))
						{
							count++;
						}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Fatal Error, try restarting this program. Goodbye!");
			System.exit(0);
		}
		finally 
		{
			sc.close();
		}
		return count;
	}

	
	private static String[] getInfo(String a, int NUMBERS, String attribute)
	{
		Scanner sc = null;
		String currentLine;
		String currentToken = null;
		int count = 0;
		String info[] = new String[NUMBERS];
		
		try
		{
			sc = new Scanner(new FileInputStream(a));
			while (sc.hasNextLine()) 
			{
				currentLine = sc.nextLine();
				StringTokenizer st = new StringTokenizer(currentLine, ",");
				while (st.hasMoreTokens()) 
				{
					currentToken = st.nextToken();
					if (currentToken.length() > 7)
						if (currentToken.substring(0, attribute.length()).equals(attribute)) 
						{
							if (count < NUMBERS) 
							{
								info[count] = currentToken.substring((attribute.length() + 1),
										(currentToken.length() - 1));
								count++;
							}
						}
				}
			}
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Fatal Error, try restarting this program. Goodbye!");
			System.exit(0);
		}
		finally 
		{
			sc.close();
		}
		return info;
	}

	
	private static void WriteIEEE(String authors, String journal, String title, String year, String volume,
			String number, String pages, String issn, String month, int filecount) 
	{
		PrintWriter pw = null;
		try 
		{
			pw = new PrintWriter(new FileOutputStream("IEEE" + filecount + ".txt", true));
			pw.println(authors + title + "\", " + journal + ", vol. " + volume + ", no. " + number + ",p " + pages
					+ ", " + month + " " + year);
			pw.println("");
			pw.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	
	private static void WriteACM(String authors, String journal, String title, String year, String volume,
			String number, String pages, String issn, String month, String doi, int jj, int filecount) 
	{
		PrintWriter pw = null;
		try 
		{
			pw = new PrintWriter(new FileOutputStream("ACM" + filecount + ".txt", true));
			pw.println("[" + jj + "]\t" + authors + year + ". " + title + ". " + journal + ". " + volume + ", " + number
					+ " (" + year + "), " + pages + ". DOI:https://doi.org/" + doi + ".");
			pw.println("");
			pw.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	
	private static void WriteNJ(String authors, String journal, String title, String year, String volume, String number,
			String pages, String issn, String month, int filecount) 
	{
		PrintWriter pw = null;
		try 
		{
			pw = new PrintWriter(new FileOutputStream("NJ" + filecount + ".txt", true));
			pw.println(authors + title + ". " + journal + ". PP, " + pages + "(" + year + ").");
			pw.println("");
			pw.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}

	
	private static String[] splitAuthors(String authorLine)
	{
		authorLine = authorLine.replaceAll("\\sand\\s", "&");
		StringTokenizer stAuthors = new StringTokenizer(authorLine, "&");
		String[] authors = new String[stAuthors.countTokens()];
		int count = 0;
		while (stAuthors.hasMoreTokens()) 
		{
			authors[count] = stAuthors.nextToken();
			count++;
		}
		return authors;
	}

	
	private static void readFiles() 
	{
		Scanner sc = new Scanner(System.in);
		int i = -1;
		String referenceChoice = "";
		String line;
		String readAgain;
		boolean wrongInput = true;
		
		while (i > 10 || i < 1) 
		{
			System.out.println("Which latex file would you like to view? Please enter a number between 1 and 10");
			do 
			{
				wrongInput = false;
				try
				{

					i = sc.nextInt();
				}
				catch (InputMismatchException e)
				{
					System.out.println("Not a valid input. Please enter a number between 1 and 10:");
					wrongInput = true;
					sc.nextLine();
				}
			} while (wrongInput);
		}
		System.out.println("What format would you like to read this in? Please enter either IEEE, ACM or NJ");
		
		do 
		{
			wrongInput = false;
			try 
			{
				referenceChoice = sc.next();
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Not a valid input. Please enter either IEEE, ACM, or NJ:");
				wrongInput = true;
				sc.nextLine();
			}
		} while (wrongInput);
		
		if (referenceChoice.equalsIgnoreCase("ACM")) 
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader("ACM" + i + ".txt"));
				while ((line = br.readLine()) != null) 
				{
					System.out.println(line);
				}
				br.close();
			} catch (FileNotFoundException e) 
			{
				System.out.print("This file does not exist. It was likely invalid");
			}
			catch (IOException e2) 
			{
				System.out.print("Unexpected error!");
			}
		}
		else if (referenceChoice.equalsIgnoreCase("IEEE")) 
		{
			try 
			{
				BufferedReader br = new BufferedReader(new FileReader("NJ" + i + ".txt"));
				while ((line = br.readLine()) != null) 
				{
					System.out.println(line);
				}
				br.close();
			}
			catch (FileNotFoundException e) 
			{
				System.out.println("This file does not exist. It was likely invalid.");
			}
			catch (IOException e2) 
			{
				System.out.println("Unexpected error!");
			}
		} 
		else if (referenceChoice.equalsIgnoreCase("NJ")) 
		{
			try 
			{
				BufferedReader br = new BufferedReader(new FileReader("NJ" + i + ".txt"));
				while ((line = br.readLine()) != null) 
				{
					System.out.println(line);
				}
				br.close();
			}
			catch (FileNotFoundException e) 
			{
				System.out.print("This file does not exist. It was likely invalid");
			}
			catch (IOException e2)
			{
				System.out.print("Unexpected error!");
			}
		} 
		else 
		{
			System.out.println("Sorry, I didn't catch that. Was expecting IEEE, ACM, or NJ as input");
		}
		System.out.println("");
		System.out.print(
				"Would you like to continue? Enter y to continue (all other inputs will terminate this program): ");
		readAgain = sc.next();
		if (readAgain.equalsIgnoreCase("y"))
		{
			readFiles();
		} 
		else
		{
			System.out.println("\nYou did not wish to continue, so program will now close. \n");
		}
		sc.close();
	}
}