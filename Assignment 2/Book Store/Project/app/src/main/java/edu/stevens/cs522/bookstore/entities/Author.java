package edu.stevens.cs522.bookstore.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Author implements Parcelable{
	
	// TODO Modify this to implement the Parcelable interface.

	// NOTE: middleInitial may be NULL!
	
	public String firstName;
	
	public String middleInitial;
	
	public String lastName;


		public Author(String[] authorName) {
			if (authorName.length == 1) {
				this.lastName = authorName[0];
			} else if (authorName.length == 2) {
				this.firstName = authorName[0];
				this.lastName = authorName[1];
			} else if (authorName.length >= 3) {
				this.firstName = authorName[0];
				this.middleInitial = authorName[1];
				this.lastName = authorName[2];
			} else if (authorName.length == 0) {
//nothing needs to be done
			}
		}


	protected Author(Parcel in) {
		firstName = in.readString();
		middleInitial = in.readString();
		lastName = in.readString();
	}

	public static final Creator<Author> CREATOR = new Creator<Author>() {
		@Override
		public Author createFromParcel(Parcel in) {
			return new Author(in);
		}

		@Override
		public Author[] newArray(int size) {
			return new Author[size];
		}
	};

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (firstName != null && !"".equals(firstName)) {
			sb.append(firstName);
			sb.append(' ');
		}
		if (middleInitial != null && !"".equals(middleInitial)) {
			sb.append(middleInitial);
			sb.append(' ');
		}
		if (lastName != null && !"".equals(lastName)) {
			sb.append(lastName);
		}
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(firstName);
		dest.writeString(middleInitial);
		dest.writeString(lastName);
	}
}
