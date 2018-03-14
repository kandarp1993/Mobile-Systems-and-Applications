package edu.stevens.cs522.bookstore.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.SparseBooleanArray;
import android.widget.Toast;

import edu.stevens.cs522.bookstore.entities.Book;
import edu.stevens.cs522.bookstore.R;
import edu.stevens.cs522.bookstore.util.BooksAdapter;

public class MainActivity extends AppCompatActivity {

	// Use this when logging errors and warnings.
	private static final String TAG = MainActivity.class.getCanonicalName();

	// These are request codes for subactivity request calls
	static final private int ADD_REQUEST = 1;

	static final private int CHECKOUT_REQUEST = ADD_REQUEST + 1;
	private  ListView listView_book;
	//private  ArrayAdapter<String> adapter;
	private static final String cart =  "list";
	public static final String BOOK_DETAILS = "Book_Results";
	BooksAdapter books_adapter;


	// There is a reason this must be an ArrayList instead of a List.
	private ArrayList<Book> books_cart;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		// TODO check if there is saved UI state, and if so, restore it (i.e. the cart contents)
		if (savedInstanceState != null)
			books_cart = savedInstanceState.getParcelableArrayList("list");

		// TODO Set the layout (use cart.xml layout)
			setContentView(R.layout.cart);


		// TODO use an array adapter to display the cart contents.
		books_cart=new ArrayList<Book>();

		books_adapter=new BooksAdapter(this,books_cart);
		listView_book=(ListView)findViewById(android.R.id.list);
		listView_book.setAdapter(books_adapter);


		listView_book.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

		listView_book.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
				@Override

				public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

					//list_item.add(shoppingCart.get(position));
					mode.setTitle(listView_book.getCheckedItemCount() + "Books selected.");
				}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					mode.getMenuInflater().inflate(R.menu.my_contextual_menu, menu);
					return true;
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					return false;
				}

				@Override
				public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
					if(item.getItemId() == R.id.delete) {
						deleteSelectedItems();
						mode.finish(); // Action picked, so close the CAB
						return true;

					}
					return false;
				}

				@Override
				public void onDestroyActionMode(ActionMode mode) {

				}

			});

		listView_book.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id){
					Intent intent = new Intent(getApplicationContext(),ViewBookActivity.class);
					Book bookselected = books_cart.get(position);
					intent.putExtra(BOOK_DETAILS,bookselected);
					startActivity(intent);
				}


			});


	}
	private void deleteSelectedItems(){

		SparseBooleanArray checked = listView_book.getCheckedItemPositions();
		Book [] books = new Book[checked.size()];
		for(int i =0; i < checked.size(); i++){
			if(checked.valueAt(i)){
				Book no_selected_book = (Book) listView_book.getItemAtPosition(checked.keyAt(i));
				books[i] = no_selected_book;
				books_adapter.remove(no_selected_book);
			}
		}
		for(Book b : books){
			books_cart.remove(b);
		}
		books_adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// TODO inflate a menu with ADD and CHECKOUT options
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.bookstore_menu,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
        switch(item.getItemId()) {

            // TODO ADD provide the UI for adding a book
            case R.id.add:
                Intent addIntent = new Intent(this, AddBookActivity.class);
				startActivityForResult(addIntent,ADD_REQUEST);
                break;

            // TODO CHECKOUT provide the UI for checking out
            case R.id.checkout:
				Intent checkoutIntent = new Intent(MainActivity.this, CheckoutActivity.class);
				startActivityForResult(checkoutIntent,CHECKOUT_REQUEST);
                break;

            default:
        }
        return false;
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		// TODO Handle results from the Search and Checkout activities.

        // Use ADD_REQUEST and CHECKOUT_REQUEST codes to distinguish the cases.
       if(requestCode==ADD_REQUEST){
		   if(resultCode==RESULT_OK){
			   Book book_add_req=intent.getExtras().getParcelable(AddBookActivity.BOOK_RESULT_KEY);
			   books_cart.add(book_add_req);

		   }
        }else if(requestCode==CHECKOUT_REQUEST){
		   if(resultCode==RESULT_OK){
			   books_cart.clear();
		   }else if(requestCode==RESULT_CANCELED){
			   Toast.makeText(getApplicationContext(),"Your process has been canceled",Toast.LENGTH_SHORT).show();
		   }
	   }
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO save the shopping cart contents (which should be a list of parcelables).
		savedInstanceState.putParcelableArrayList(cart,books_cart);

	}



}