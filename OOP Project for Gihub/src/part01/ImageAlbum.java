package part01;

import java.time.LocalDate;
import java.util.ArrayList;

public class ImageAlbum {

	private ArrayList<ImageRecord> images;
	private int currentPosition;

	public ImageAlbum() {
		images = new ArrayList<ImageRecord>();
		currentPosition = -1;
	}

	/**
	 * Add Image Method------------------------------------------------- It takes an
	 * image sorts it by the earliest date so - it does not have to be sorted every
	 * time images are called
	 * 
	 * @param image
	 */
	public void addImage(ImageRecord image) {

		if (image == null) {
			return;
		}
		if (images.size() == 0) {
			images.add(image);
			return;
		}

		// insertion sort by
		// dates
		LocalDate newDate = image.getDate();

		for (int i = 0; i < images.size(); i++) {
			// current date
			LocalDate currentDate = images.get(i).getDate();

			if (newDate.isBefore(currentDate)) {
				images.add(i, image);
				return;
			}

		}
		images.add(image);
	}

	/**
	 * Get First Method-------------------------------------------------
	 * 
	 * @return
	 */
	public ImageRecord getFirst() {
		if (images.size() == 0) {

			return null;
		}
		currentPosition = 0;
		return images.get(currentPosition);

	}

	/**
	 * Get Next Method--------------------------------------------------
	 * 
	 * @return
	 */
	public ImageRecord getNext() {

		if (images.size() == 0) {
			return null;
		}
		if (currentPosition == -1) {
			return null;
		}
		if(currentPosition + 1 >= images.size()) {
			return null;
		}
		
		currentPosition++;
		return images.get(currentPosition);
	}

	/**
	 * Get Previous Method------------------------------------------------
	 * 
	 * @return
	 */
	public ImageRecord getPrevious() {
		
		if (images.size() == 0) {
			return null;
		}
		if (currentPosition == -1) {
			return null;
		}
		if(currentPosition - 1 < 0) {
			return null;
		}
		
		currentPosition--;
		return images.get(currentPosition);
	} 

}
