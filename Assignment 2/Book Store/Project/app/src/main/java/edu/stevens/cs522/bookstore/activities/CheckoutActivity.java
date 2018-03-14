package edu.stevens.cs522.bookstore.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import edu.stevens.cs522.bookstore.R;

public class CheckoutActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// TODO display ORDER and CANCEL options.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.checkout_menu,menu);
		return super.onCreateOptionsMenu(menu);
		//return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		// TODO
		switch(item.getItemId()) {

			// TODO ADD provide the UI for adding a book
			case R.id.order:
				Intent add_book_intent = new Intent();
				setResult(RESULT_OK,add_book_intent);
				Toast.makeText(getApplicationContext(),"Order is confirmed.",Toast.LENGTH_SHORT).show();
				finish();
				break;

			// TODO CHECKOUT provide the UI for checking out
			case R.id.cancel:
				Intent cancel_intent=new Intent();
				setResult(RESULT_CANCELED,cancel_intent);
				finish();
				break;

			default:
		}
		// ORDER: display a toast message of how many books have been ordered and return
		
		// CANCEL: just return with REQUEST_CANCELED as the result code

		return false;
	}
	
}