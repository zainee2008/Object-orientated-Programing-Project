package part02;

import java.time.LocalDate;

import javax.swing.ImageIcon;

import console.Console;
import part01.ImageManager;
import part01.ImageAlbum;
import part01.ImageRecord;
import part01.ImageType;

import java.awt.Color;
import java.awt.Font;


/**
 * this class pretty much uses the same format as the QUBImages with few changes specifically made to run on console
 */
public class QUBMediaImages {

	private static ImageManager manager = new ImageManager();
	private static Console console = new Console(true);

	public static void main(String[] args) {

		console.setSize(700, 400);
		console.setVisible(true);

		QUBMediaImages run = new QUBMediaImages();
		run.runProgram();

	}

	public void runProgram() {

		console.setBgColour(Color.magenta);
		console.setColour(Color.BLACK);
		boolean running = true;
		String[] options = { "ADD", "Search", "Display All", "Exit" };

		while (running) {
			String userChoice = menu("QUB images", options);

			switch (userChoice) {
			case "1":
				console.setBgColour(Color.CYAN);
				console.setColour(Color.BLACK);
				firstChoice();
				break;
			case "2":
				console.setBgColour(Color.gray);
				console.setColour(Color.WHITE);
				secondChoice();
				break;
			case "3":
				console.setBgColour(Color.BLACK);
				console.setColour(Color.WHITE);
				console.clear();
				thirdChoice();
				break;
			case "4":
				console.setBgColour(Color.LIGHT_GRAY);
				console.setColour(Color.BLACK);
				console.clear();
				console.println("Have a nice day");
				running = false;
				break;
			default:
				console.println("Invalid Choice");
			}

		}
	}

	/**
	 * first Choice and read
	 * methods-------------------------------------------------
	 * 
	 */
	private void firstChoice() {
		console.clear();
		console.println("Please Enter Title: ");
		String title = console.readLn();

		console.println("Please Enter Description: ");
		String description = console.readLn();

		ImageType type = readType();

		LocalDate date = readDate("Please enter Image date eg.(yyyy-mm-dd): ");

		console.println("Please Enter Thumbnail: ");
		String thumbnail = console.readLn();

		ImageRecord image = manager.autoIncrementImage(title, description, type, date, thumbnail);

		console.println("Image Succesfully added, Generated ID: " + image.getID());
		pressToContinue();

	}

	private int readId() {

		while (true) {
			try {
				console.println("Please enter Image ID: ");
				int id = Integer.parseInt(console.readLn());
				return id;

			} catch (Exception e) {
				console.println("Inavlid input, please Enter ID again");
			}
		}
	}

	private ImageType readType() {
		while (true) {
			try {
				console.println(
						"Please enter Image Type eg. (ASTRONOMY, ARCHITECTURE, SPORT, LANDSCAPE, PORTRAIT, NATURE, AERIAL, FOOD, OTHER) : ");

				ImageType type = ImageType.valueOf(console.readLn().toUpperCase());
				return type;
			} catch (IllegalArgumentException e) {
				console.println("Please enter valid input");
			}
		}
	}

	private LocalDate readDate(String message) {

		while (true) {
			try {
				console.println(message);
				LocalDate date = LocalDate.parse(console.readLn());
				return date;
			} catch (Exception e) {
				console.println("Please enter valid input");
			}
		}
	}

	/**
	 * Search Images methods (second Choice)----------------------------------------
	 * 
	 */
	private void secondChoice() {
		boolean running = true;

		String[] options = { "Search Image by ID", "Search Image by Title", "Search Image by Description",
				"Search Image by Type", "Search Image by Dates", "Back" };

		while (running) {

			String userChoice = menu("Search Options", options);

			switch (userChoice) {
			case "1":
				console.setBgColour(Color.CYAN);
				console.setColour(Color.BLACK);
				searchIdChoice();
				break;
			case "2":
				console.setBgColour(Color.LIGHT_GRAY);
				console.setColour(Color.BLACK);
				searchTitleChoice();
				break;
			case "3":
				console.setBgColour(Color.gray);
				console.setColour(Color.WHITE);
				searchDescriptionChoice();
				break;
			case "4":
				console.setBgColour(Color.CYAN);
				console.setColour(Color.BLACK);
				searchTypeChoice();
				break;
			case "5":
				console.setBgColour(Color.red);
				console.setColour(Color.white);
				serachDatesChoice();
				break;
			case "6":
				console.println("\nReturning to previos Menu\n");
				console.setBgColour(Color.magenta);
				console.setColour(Color.BLACK);
				running = false;
				break;
			default:
				console.println("\nPlease enter valid input");
			}
		}

	}

	private void searchIdChoice() {
		console.clear();
		int id = readId();
		ImageRecord image = manager.searchId(id);

		if (image == null) {
			console.println("\nImage ID not found");
			console.println("\nReturning to Search ");
		} else {
			console.clear();
			console.println(image);
		}
		pressToContinue();
	}

	private void searchTitleChoice() {
		console.clear();
		console.println("Plese enter Image Title you want to search: ");
		String title = console.readLn();

		ImageAlbum album = manager.searchTitle(title);
		displayImageAlbum(album);
	}

	private void searchDescriptionChoice() {
		console.clear();
		console.println("please enter Image Description you want to search: ");
		String description = console.readLn();

		ImageAlbum album = manager.searchDescription(description);
		displayImageAlbum(album);
	}

	private void searchTypeChoice() {
		console.clear();
		ImageType type = readType();

		ImageAlbum album = manager.searchType(type);
		displayImageAlbum(album);
	}

	private void serachDatesChoice() {
		console.clear();
		LocalDate start = readDate("Please Enter Start date eg.(yyyy-mm-dd): ");
		console.clear();
		LocalDate end = readDate("Please Enter End date eg.(yyyy-mm-dd): ");

		ImageAlbum album = manager.searchDates(start, end);
		displayImageAlbum(album);
	}

	/**
	 * third choice Display all images--------------------------------------
	 */
	private void thirdChoice() {
		ImageAlbum album = manager.getAllImages();
		displayImageAlbum(album);
	}

	/**
	 * will not continue unless user presses enter
	 */
	private void pressToContinue() {
		console.println("\n Press enter to continue");
		console.readLn();
	}

	/**
	 * Menu fir printing choices---------------------------------
	 * similar to menu class but made with few changes
	 * @param title
	 * @param options
	 * @return
	 */
	private String menu(String title, String[] options) {

		console.clear();
		console.setFont(new Font("Arial", Font.BOLD, 18));
		console.println("\n " + title + " \n");
		console.println("++++++++++++++");

		for (int i = 0; i < options.length; i++) {
			console.println((i + 1) + ". " + options[i]);
		}

		console.println("Enter Selection: ");
		return console.readLn();
	}

	/**
	 * Display album method-----------------------------------------
	 * 
	 * @param album
	 */
	private void displayImageAlbum(ImageAlbum album) {
		console.clear();
		ImageRecord image = album.getFirst();

		if (image == null) {
			console.println("No matching image found");
			return;
		}
		while (image != null) {
			
		
			console.println(image.toString());
			String imgLocation = System.getProperty("user.dir");
			ImageIcon img = new ImageIcon(imgLocation + "/images/images/" + image.getThumbnail());
			console.println(img);
			console.println("------------------------------------------------------------------------------------------------------");
			
			image = album.getNext();
		}
		pressToContinue();
	}

}
