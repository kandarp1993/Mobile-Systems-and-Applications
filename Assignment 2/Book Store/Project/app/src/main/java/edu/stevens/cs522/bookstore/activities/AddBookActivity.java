package edu.stevens.cs522.bookstore.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.stevens.cs522.bookstore.R;
import edu.stevens.cs522.bookstore.entities.Author;
import edu.stevens.cs522.bookstore.entities.Book;

public class AddBookActivity extends AppCompatActivity {

	// Use this as the key to return the book details as a Parcelable extra in the result intent.
	public static final String BOOK_RESULT_KEY = "book_result";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_book);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu bookadd_menu) {
		//super.onCreateOptionsMenu(menu);
		// TODO provide ADD and CANCEL options
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.bookadd_menu,bookadd_menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		// TODO
		switch(item.getItemId()) {

			// TODO ADD provide the UI for adding a book
			case R.id.add:
				//Book bookResults=addBook();
				Book book_add = addBook();
				Intent add_intent = new Intent();
				//addIntent.putExtra(BOOK_RESULT_KEY,bookResults);
				add_intent.putExtra(BOOK_RESULT_KEY,book_add);
				setResult(RESULT_OK,add_intent);
				finish();
				break;

			// TODO CHECKOUT provide the UI for checking out
			case R.id.cancel:
				Intent cancel_intent=new Intent(this,MainActivity.class);
				startActivity(cancel_intent);
				finish();
				break;

			default:
		}
		return false;

		// ADD: return the book details to the BookStore activity

		// CANCEL: cancel the request


	}

	public Book addBook(){
		// TODO Just build a Book object with the search criteria and return that.
		EditText title_book = (EditText) findViewById(R.id.search_title);
		String b_title=title_book.getText().toString();
		EditText author_book = (EditText)findViewById(R.id.search_author);
		String b_author=author_book.getText().toString();
		String[] authors_book=b_author.split(" , ");
		Author[] authors_book_array=new Author[authors_book.length];
		for(int i=0; i<authors_book.length; i++){
			authors_book_array[i]= new Author(authors_book[i].split(" "));
		}
		EditText isbn_book = (EditText)findViewById(R.id.search_isbn);
		String b_isbn=isbn_book.getText().toString();

		Book book_info=new Book(b_title,authors_book_array,b_isbn);
		return book_info;
	}

}

