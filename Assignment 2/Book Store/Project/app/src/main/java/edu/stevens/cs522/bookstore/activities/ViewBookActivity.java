package edu.stevens.cs522.bookstore.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import edu.stevens.cs522.bookstore.R;
import edu.stevens.cs522.bookstore.entities.Book;
import edu.stevens.cs522.bookstore.activities.MainActivity.*;

public class ViewBookActivity extends Activity {
	
	// Use this as the key to return the book details as a Parcelable extra in the result intent.
	public static final String BOOK_KEY = "book";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_book);

		// TODO get book as parcelable intent extra and populate the UI with book details.
		savedInstanceState=getIntent().getExtras();
		Book book_details=savedInstanceState.getParcelable(MainActivity.BOOK_DETAILS);
		TextView title=(TextView)findViewById(R.id.view_title);
		title.setText(book_details.getTitle());
		TextView author=(TextView)findViewById(R.id.view_author);
		author.setText(book_details.getFirstAuthor());
		TextView isbn=(TextView)findViewById(R.id.view_isbn);
		isbn.setText(book_details.getIsbn());
	}

}