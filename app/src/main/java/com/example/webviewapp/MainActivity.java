package com.example.webviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Cat> cats = new ArrayList<Cat>();

    private ArrayList<RecyclerViewItem> items;

    private int showExternalPagePressed = 0;
    private int showInternalPagePressed = 0;

    // compared to the example code, we need to move the adapter out
    // as we are using two extra classes to do some updates
    RecyclerViewAdapter adapter;

    public void showExternalWebPage() {
        // TODO: Add your code for showing external web page here
        showExternalPagePressed++;
        Log.d("==>", "EXTERNAL_PRESSED" + showExternalPagePressed);
        items.add(new RecyclerViewItem("External page pressed " + showExternalPagePressed + " times"));
        adapter.notifyItemInserted(items.size()-1);
    }

    public void showInternalWebPage() {
        // TODO: Add your code for showing internal web page here
        showInternalPagePressed++;
        Log.d("==>", "INTERNAL_PRESSED" + showInternalPagePressed);
        items.add(new RecyclerViewItem("Internal page pressed " + showInternalPagePressed + " times"));
        adapter.notifyItemInserted(items.size()-1);

        // note: the following adapter notify refreshes the whole list, but it is a bit slower
        // you can still use it if you are making complex changes
        //adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cats.add(new Cat("Fritz"));
        cats.add(new Cat("Frida"));

        // note: we are having items here locally
        // but cats is existing on the class scope
        // in case you need multiple functions accessing or manipulating a single container
        // then you need to put the variable in the class scope

        items = new ArrayList<>(Arrays.asList(new RecyclerViewItem("Matterhorn"), new RecyclerViewItem("Mont Blanc"), new RecyclerViewItem("Denali")));

        // the RecycleViewItem takes string as an input
        // as long as we only use string it will work.
        for (Cat cat : cats)
            items.add(new RecyclerViewItem(cat.getInfo()));

        for (int i = 0; i < 20; i++) {
            // note: we do a string conversion here putting together an int with a string
            //
            items.add(new RecyclerViewItem("number_" + i));
        }

        // note: compared to the example we must remove RecyclerViewAdapter
        // as that would create a new variable by declaring it
        // the original example shows the adapter on class scope
        // but that way, we cannot update the adapter and with it, the user interface
        /*RecyclerViewAdapter*/ adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            //@Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        /*
        * Rename your App. Tip: Values->Strings
        * Enable Internet access for your App. Tip: Manifest
        * Create a WebView element in the layout file main_activity.xml
        * Give the WebView element ID "my_webview"
        -- Commit and push to your github fork
        * Create a private member variable called "myWebView" of type WebView
        * Locate the WebView element created in step 1 using the ID created in step 2
        * Create a new WebViewClient to attach to our WebView. This allows us to
          browse the web inside our app.
        -- Commit and push to your github fork
        * Enable Javascript execution in your WebViewClient
        * Enter the url to load in our WebView
        -- Commit and push to your github fork
        * Move the code that loads a URL into your WebView into the two methods
          "showExternalWebPage()" and "showInternalWebPage()".
        * Call the "showExternalWebPage()" / "showInternalWebPage()" methods
          when you select menu options "External Web Page" or "Internal Web Page"
          respectively
        -- Commit and push to your github fork
        * Take two screenshots using the "Take a screenshot" tool in the AVD
           showing your App. One (1) screenshot showing your internal web page and
           one (1) screenshot showing your external web page.
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_external_web) {
            Log.d("==>", "Will display external web page");
            showExternalWebPage();
            return true;
        }

        if (id == R.id.action_internal_web) {
            Log.d("==>", "Will display internal web page");
            showInternalWebPage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
