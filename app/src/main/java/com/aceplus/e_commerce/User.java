package com.aceplus.e_commerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class User extends AppCompatActivity {

    TextView txtProfile, txtWishList, txtCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.home:
                // TODO put your code here to respond to the button tap
                Toast.makeText(this, "Home!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            case android.R.id.home:

                onBackPressed();
                return true;


            case R.id.cart:

                Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, CartListActivity.class);
                intent1.putExtra("Class","User");
                startActivity(intent1);
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);


        }
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "Home!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void toProfile(View view) {

        Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();

    }

    public void toWishList(View view) {

        Toast.makeText(this, "Wishlist", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, WishListActivity.class);
        startActivity(intent);
        finish();

    }

    public void toCartList(View view) {

        Toast.makeText(this, "CartList", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, CartListActivity.class);
        intent.putExtra("Class","User");
        startActivity(intent);
        finish();


    }

    public void toOrderHistory(View view) {

        Toast.makeText(this, "OrderHistory", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, CartListActivity.class);
        startActivity(intent);
        finish();


    }


}
