package part01;

import java.time.LocalDate;

public class ImageRecord {

	private int id;
	private String title;
	private String description;
	private ImageType type;
	private LocalDate date;
	private String thumbnail;

	public ImageRecord(int id, String title, String description, ImageType type, LocalDate date, String thumbnail) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.date = date;
		this.thumbnail = thumbnail;

	}

	// Getter Methods
	public int getID() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public ImageType getType() {
		return this.type;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	
	// Setter Methods
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String descryption) {
		this.description = descryption;
	}

	public void setType(ImageType type) {
		this.type = type;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	
	// To String Method
	public String toString() {

		return "ID: " + id + " | Title: " + title + " | Description: " + description + " | Image Type: " + type + " | Date:"
				+ date + " | Thumbnail: " + thumbnail+"\n";
	}

}
