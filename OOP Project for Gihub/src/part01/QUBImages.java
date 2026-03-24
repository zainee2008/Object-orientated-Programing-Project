package part01;


import java.time.LocalDate;
import java.util.Scanner;

public class QUBImages {

	private ImageManager manager;
	private Scanner input;

	/**--------------------------------------------------------------------------------
	 * uses one scanner for all of the methods by making it public
	 * define imageManager as a variable to be re used in other methods
	 */
	public QUBImages() {
		input = new Scanner(System.in);
		manager = new ImageManager();
	}
	
	/**Main method gets users choice from user choice method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		QUBImages select = new QUBImages();
		select.getUserChoice();

	}

	/**
	 * Get userChoice method  ---------------------------------------------------------------
	 * uses menu class to ask the user about the options 
	 * like add, search, display all images
	 */
	public void getUserChoice() {

		String[] ask = { "Add", "Search", "Display All Images ", "Exit" };

		Menu menu = new Menu("qub images", ask);

		int choice = 0;//initialise variable
		
		// while and switch case to c what the user picks
		while (choice != 4) {
			choice = menu.getUserChoice();

			switch (choice) {
			case 1:
				firstChoice();
				break;
			case 2:
				secondChoice();
				break;
			case 3:
				thirdChoice();
				break;
			case 4:
				System.out.println("Have a nice day");
				break;
			default:
				System.out.print("\nPlease enter valid input\n");
			}

		}
	}

	/** first choice method takes in all the image information------------------------------------
	 * an then adds it as an image
	 * 
	 */
	private void firstChoice() {

		String title = readTitle();
		String description = readDescription();
		ImageType type = readType();
		LocalDate date = readDate("Please enter Image date eg.(yyyy-mm-dd): ");
		String thumbnail = readThumbnail();

		//this take the image information from the user and adds it to image manager
		ImageRecord image = manager.autoIncrementImage(title, description, type, date, thumbnail);

		//tells the user that the image has been added and tell the position where its added by giving the id
		System.out.println("Image Succesfully added, Generated ID: " + image.getID());

	}

	/**Second choice method uses either one of the image info-------------------------------------------------------------
	 * which the gets user the image how ever way he wants it 
	 * 
	 */
	private void secondChoice() {
		getImageChoice();//uses another method to keep the data clean and easy to manage

	}

	/** third choice method uses getAllImages method to get -------------------------------------------------------
	 * all images from the image manager and uses display method to display them as an album
	 * 
	 */
	private void thirdChoice() {
		ImageAlbum album = manager.getAllImages();
		displayImageAlbum(album);
	}

	/**
	 * Search Images methods for user interface for (secondChoice) which
	 * re uses the menu class +--------------------------------------------------------------------------------
	 * 
	 */
	private void getImageChoice() {

		System.out.println();
		String[] searchOptions = { "Search Image by ID", "Search Image by Title", "Search Image by Description",
				"Search Image by Type", "Search Image by Dates", "Back" };

		Menu searchMenu = new Menu("Search Options", searchOptions);//reusing menu class for efficient code reuse

		int choice = 0;
		
		//uses similar while loop and switch case idea as the getUser choice method
		while (choice != 6) {
			choice = searchMenu.getUserChoice();

			switch (choice) {
			case 1:
				searchIdChoice();
				break;
			case 2:
				searchTitleChoice();
				break;
			case 3:
				searchDescriptionChoice();
				break;
			case 4:
				searchTypeChoice();
				break;
			case 5:
				serachDatesChoice();
				break;
			case 6:
				System.out.print("\nReturning to previos Menu\n");
				break;
			default:
				System.out.print("\nPlease enter valid input");
			}

		}

	}

	/**Search Id method ------------------------------------------------
	 * reusing readId methods to read and validate user input
	 * calls the imageRecord class to validate its existence in the class
	 * if els statement if image with that id is there or not
	 */
	private void searchIdChoice() {
		int id = readId();
		ImageRecord image = manager.searchId(id);

		if (image == null) {
			System.out.println("\nImage ID not found");
			System.out.print("\nReturning to Search ");
		} else {
			System.out.println(image);
		}

	}

	/**Search title method takes in the title information from user 
	 * validates the data by using the image manager
	 * returns it as an album if one or more of them exists
	 * 
	 */
	private void searchTitleChoice() {
		System.out.println("Plese enter Image Title you want to search: ");
		String title = input.nextLine();
		
		ImageAlbum album = manager.searchTitle(title);
		displayImageAlbum(album);
	}

	/**Search Description method takes in the Description information from user 
	 * validates the data by using the image manager
	 * returns it as an album if one or more of them exists
	 * similar to search title
	 */
	private void searchDescriptionChoice() {
		System.out.println("please enter Image Description you want to search: ");
		String description = input.nextLine();
		
		ImageAlbum album = manager.searchDescription(description);
		displayImageAlbum(album);
	}
	
	/**
	 * recalls the read type method for the data validation from the user---------------------------------
	 * input an then uses search method in image manager to check data existence
	 * returns as album if one or more exists
	 */
	private void searchTypeChoice() {
		ImageType type = readType();// method re use for efficient code
		
		ImageAlbum album = manager.searchType(type);
		displayImageAlbum(album);
	}

	/**
	 * asks user to enter dates in desired format
	 * uses read dates methods to validate and
	 * search date methods in image manager to the call the images
	 * as an album which exists within the dates
	 */
	private void serachDatesChoice() {
		
		LocalDate start = readDate("Please Enter Start date eg.(yyyy-mm-dd): ");
		LocalDate end = readDate("Please Enter End date eg.(yyyy-mm-dd): ");
		
		ImageAlbum album = manager.searchDates(start, end);
		displayImageAlbum(album);
	}
	/**Display album method---------------------------------------------------
	 * take the get first methods from the album to get the latest picture first
	 * uses if statement to validate the image existence
	 * uses get next method from image album to then print the next image in line
	 * 
	 * @param album
	 */
	private void displayImageAlbum(ImageAlbum album) {
		ImageRecord image = album.getFirst();
		
		if(image == null) {
			System.out.println("No matching image found");
			return;
		}
		while(image != null) {
			System.out.println(image);
			image = album.getNext();
		}
	}

	/**
	 * All the read methods for valid user
	 * input--------------------------------------------------------------------------------------
	 * @return
	 */
	private int readId() {

		while (true) {
			try {
				System.out.println("Please enter Image ID: ");
				int id = Integer.parseInt(input.nextLine());
				return id;

			} catch (Exception e) {
				System.out.println("Inavlid input, please Enter ID again");
			}
		}
	}
	
	
	private String readTitle() {

		System.out.println("Please enter Image Title: ");
		return input.nextLine();
	}

	private String readDescription() {
		System.out.println("please enter Image Description: ");
		return input.nextLine();
	}

	private ImageType readType() {
		while (true) {
			try {
				System.out.println(
						"Please enter Image Type eg. (ASTRONOMY, ARCHITECTURE, SPORT, LANDSCAPE, PORTRAIT, NATURE, AERIAL, FOOD, OTHER) : ");

				ImageType type = ImageType.valueOf(input.nextLine().toUpperCase());
				return type;
			} catch (IllegalArgumentException e) {
				System.out.println("Please enter valid input");
			}
		}
	}

	/**-------------------------------------------------------------------------------------------------------
	 * Read date method make it easier for code re use as the date are 
	 * read twice start and end
	 * @param message
	 * @return
	 */
	private LocalDate readDate(String message) {

		while (true) {
			try {
				System.out.println(message);
				LocalDate date = LocalDate.parse(input.nextLine());
				return date;
			} catch (Exception e) {
				System.out.println("Please enter valid input");
			}
		}
	}

	/**Read Thumbnail method was created just in case if want to call
	 * multiple times tho was only called once
	 * 
	 * @return
	 */
	private String readThumbnail() {
		System.out.println("Plese enter Image Thumbnail: ");
		return input.nextLine();
	}

}
