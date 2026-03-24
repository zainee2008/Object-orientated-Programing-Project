package part01;

import java.time.LocalDate;
import java.util.ArrayList;

public class ImageManager {

	private ArrayList<ImageRecord> images = new ArrayList<ImageRecord>();


	/**
	 * Default Image constructor
	 */
	public ImageManager() {
		defaultImages();
	}

	private void defaultImages() {
		addImage(new ImageRecord(1, "Andromeda Galaxy", "mage of the Andromeda galaxy.", ImageType.ASTRONOMY,
				LocalDate.of(2023, 01, 01), "Andromeda.png"));

		addImage(new ImageRecord(2, "Lanyon QUB", "An image of the QUB Lanyon building.",
				ImageType.ARCHITECTURE, LocalDate.of(2023, 02, 01), "LanyonQUB.png"));

		addImage(new ImageRecord(3, "Kermit Plays Golf", "An image of Kermit the frog playing golf.",
				ImageType.SPORT, LocalDate.of(2023, 03, 01), "KermitGolf.png"));

		addImage(new ImageRecord(4, "Mourne Mountains", "A panoramic view of the Mourne mountains.",
				ImageType.LANDSCAPE, LocalDate.of(2023, 04, 01), "Mournes.png"));

		addImage(new ImageRecord(5, "Homer Simpson", "Homer Simpson- A portrait of the man.",
				ImageType.PORTRAIT, LocalDate.of(2023, 03, 01), "Homer.png"));

		addImage(new ImageRecord(6, "Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE,
				LocalDate.of(2023, 04, 01), "RedKite.png"));

		addImage(new ImageRecord(7, "Central Park ", "An overhead view of Central Park New York USA.",
				ImageType.AERIAL, LocalDate.of(2023, 05, 01), "CentralPark.png"));

		addImage(new ImageRecord(8, "Apples", "A bunch of apples.", ImageType.FOOD, LocalDate.of(2023, 06, 01),
				"Apples.png"));

		addImage(new ImageRecord(9, "Programming Meme", "A Chat GPT programming meme.png", ImageType.OTHER,
				LocalDate.of(2023, 07, 01), "ChatGPT.png"));
	}

	public void addImage(ImageRecord image) {
		if (image == null) {
			return;
		}
		images.add(image);

	}

	private int generateNextId() {
		int maxId = 9;

		for (int i = 0; i < images.size(); i++) {
			ImageRecord currentImage = images.get(i);
			if (currentImage.getID() > maxId) {
				maxId = currentImage.getID();
			}
		}

		maxId++;
		return maxId;
	}

	public ImageRecord autoIncrementImage(String title, String description, ImageType type, LocalDate date,
			String thumbnail) {
		int newId = generateNextId();

		ImageRecord image = new ImageRecord(newId, title, description, type, date, thumbnail);
		addImage(image);
		return image;
	}

	/**
	 * search id method------------------------------
	 * 
	 * @param id
	 * @return
	 */
	public ImageRecord searchId(int id) {

		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);

			if (img.getID() == id) {
				return img;
			}

		}
		return null;

	}

	/**
	 * search title method -------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public ImageAlbum searchTitle(String str) {
		ImageAlbum album = new ImageAlbum();

		if (str == null) {
			return album;
		}

		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);

			if (img.getTitle().equalsIgnoreCase(str)) {
				album.addImage(img);
			}

		}

		return album;
	}

	/**
	 * search Description method------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public ImageAlbum searchDescription(String str) {
		ImageAlbum album = new ImageAlbum();

		if (str == null) {
			return album;
		}

		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);

			if (img.getDescription().toLowerCase().contains(str.toLowerCase())) {
				album.addImage(img);
			}

		}

		return album;
	}

	/**
	 * search genre method---------------------------------------------------
	 * 
	 * @param type
	 * @return
	 */
	public ImageAlbum searchType(ImageType type) {

		ImageAlbum album = new ImageAlbum();

		if (type == null) {
			return album;
		}

		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);

			if (img.getType() == type) {
				album.addImage(img);
			}

		}

		return album;
	}

	/**
	 * Search dates
	 * method-----------------------------------------------------------
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		ImageAlbum album = new ImageAlbum();

		if (start == null || end == null) {
			return album;
		}

		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);
			LocalDate date = img.getDate();

			if ((date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end))) {
				album.addImage(img);
			}

		}

		return album;
	}

	/**
	 * Get all images method-----------------------------------
	 * 
	 * @return
	 */
	public ImageAlbum getAllImages() {
		ImageAlbum album = new ImageAlbum();

		for (int i = 0; i < images.size(); i++) {
			album.addImage(images.get(i));
		}
		return album;

	}
}
