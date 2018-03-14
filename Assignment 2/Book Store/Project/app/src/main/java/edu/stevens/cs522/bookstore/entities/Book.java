package edu.stevens.cs522.bookstore.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{
	
	// TODO Modify this to implement the Parcelable interface.

	//public int id;
	
	public String title;
	
	public Author[] authors;
	
	public String isbn;
	
	//public String price;

	public Book(String title, Author[] author, String isbn) {
		//this.id = id;
		this.title = title;
		this.authors = author;
		this.isbn = isbn;
		//this.price = price;
	}

	protected Book(Parcel in) {
		//id = in.readInt();
		title = in.readString();
		authors=in.createTypedArray(Author.CREATOR);
		isbn = in.readString();

		//price = in.readString();
	}

	public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
		@Override
		public Book createFromParcel(Parcel in) {
			return new Book(in);
		}

		@Override
		public Book[] newArray(int size) {
			return new Book[size];
		}
	};

	public String getFirstAuthor() {
		if (authors != null && authors.length > 0) {
			return authors[0].toString();
		} else {
			return "";
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		//dest.writeInt(id);
		dest.writeString(title);
		dest.writeTypedArray(authors ,flags);
		dest.writeString(isbn);

		//dest.writeString(price);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author[] getAuthors() {
		return authors;
	}

	public void setAuthors(Author[] authors) {
		this.authors = authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public static Creator<Book> getCREATOR() {
		return CREATOR;
	}
}