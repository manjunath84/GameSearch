package com.mistplay.game.entities;

public class Game{

	private String genre;
	
	private String imageUrl;
	
	private String subgenre;
	
	private String title;
	
	private String pid;
	
	private float rating;
	
	private int rCount;

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the subgenre
	 */
	public String getSubgenre() {
		return subgenre;
	}

	/**
	 * @param subgenre the subgenre to set
	 */
	public void setSubgenre(String subgenre) {
		this.subgenre = subgenre;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	 * @return the rCount
	 */
	public int getrCount() {
		return rCount;
	}

	/**
	 * @param rCount the rCount to set
	 */
	public void setrCount(int rCount) {
		this.rCount = rCount;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((subgenre == null) ? 0 : subgenre.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (subgenre == null) {
			if (other.subgenre != null)
				return false;
		} else if (!subgenre.equals(other.subgenre))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [genre=" + genre + ", imageUrl=" + imageUrl + ", subgenre=" + subgenre + ", title=" + title
				+ ", pid=" + pid + ", rating=" + rating + ", rCount=" + rCount + "]";
	}
	
	
	
}
