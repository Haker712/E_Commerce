package com.aceplus.e_commerce;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.aceplus.e_commerce.model.modelForView.CartlistdataforView;
import com.aceplus.e_commerce.model.modelforAddtoCart.ForTotalAmount;
import com.aceplus.e_commerce.utils.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {

    int id, product_id, qty;
    String product_name, image_encode;
    double product_price, product_discount_amount, product_sell_price;
    String product_discount_type;

    double OriginalValue = 0.0;

    TextView txtTotalAmount;
    TextView txtItemCount;

    Button btnCheckOut;

    int totalPrice = 0;

    SQLiteDatabase sqLiteDatabase;

    CartlistdataforView cartlistdataforView;
    List<CartlistdataforView> cartlistdataforViewList = new ArrayList<>();

    CartListAdapter cartListAdapter;
    RecyclerView cartRecyclerView;

    ForTotalAmount forTotalAmount;
    List<ForTotalAmount> forTotalAmountList = new ArrayList<>();

    String come_from_which_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        sqLiteDatabase = new MyDatabase(this).getDataBase();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cart List");

        Intent intent = getIntent();
        come_from_which_class = intent.getStringExtra("Class");

        findViewById();

        SetCartAdapter();

    }

    public void RetrieveCartListData() {

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM stock_reserve", null);


        while (cursor.moveToNext()) {

            cartlistdataforView = new CartlistdataforView();
            forTotalAmount = new ForTotalAmount();

            id = cursor.getInt(cursor.getColumnIndex("id"));
            product_id = cursor.getInt(cursor.getColumnIndex("product_id"));
            qty = cursor.getInt(cursor.getColumnIndex("qty"));


            Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM product_group  WHERE id='" + product_id + "'", null);

            if (cursor1.getCount() == 0) {

                sqLiteDatabase.execSQL("DELETE FROM stock_reserve WHERE  product_id=" + product_id + "");

            } else {

                while (cursor1.moveToNext()) {

                    product_name = cursor1.getString(cursor1.getColumnIndex("product_name"));
                    product_price = cursor1.getDouble(cursor1.getColumnIndex("cost_price"));

                }

                Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM product_gallery WHERE product_id='" + product_id + "'", null);


                while (cursor2.moveToNext()) {
                    Log.i("Cur", cursor2.getCount() + "");

                    image_encode = cursor2.getString(cursor2.getColumnIndex("image_encode"));
                    Log.i("Image", image_encode);


                }

                Cursor cursor3 = sqLiteDatabase.rawQuery("SELECT * FROM product_discount WHERE product_group_id = " + product_id + "", null);

                if (cursor3.getCount() != 0) {

                    while (cursor3.moveToNext()) {

                        product_discount_amount = cursor3.getDouble(cursor3.getColumnIndex("discount_amount"));
                        product_discount_type = cursor3.getString(cursor3.getColumnIndex("discount_type"));

                        if (product_discount_type.equalsIgnoreCase("%")) {

                            product_discount_amount = (product_discount_amount / 100) * product_price;
                            product_sell_price = product_price - product_discount_amount;

                        } else {

                            product_sell_price = product_price - product_discount_amount;

                        }


                    }
                } else {

                    product_sell_price = product_price;
                }

                cartlistdataforView.setId(id);
                cartlistdataforView.setProduct_id(product_id);
                cartlistdataforView.setProduct_name(product_name);
                cartlistdataforView.setSell_price(product_sell_price);
                cartlistdataforView.setImage_encode(image_encode);
                cartlistdataforView.setQty(qty);

                forTotalAmount.setId(id);
                forTotalAmount.setProduct_id(product_id);
                forTotalAmount.setProduct_name(product_name);
                forTotalAmount.setSell_price(product_sell_price);
                forTotalAmount.setImage_encode(image_encode);
                forTotalAmount.setQty(qty);

                cartlistdataforViewList.add(cartlistdataforView);

                forTotalAmountList.add(forTotalAmount);

                for (ForTotalAmount forTotalAmount : forTotalAmountList) {

                    Log.i("SellPrice", forTotalAmount.getSell_price() + "");


                }

            }


        }


    }

    public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

        List<CartlistdataforView> cartlistdataforViewList = new ArrayList<>();
        List<ForTotalAmount> forTotalAmountList = new ArrayList<>();
        LayoutInflater layoutInflater;
        Context context;
        View view;

        public CartListAdapter(Context context1, List<CartlistdataforView> cartlistdataforViewList1, List<ForTotalAmount> forTotalAmountList) {

            this.cartlistdataforViewList = cartlistdataforViewList1;
            this.forTotalAmountList = forTotalAmountList;
            layoutInflater = LayoutInflater.from(context1);
            this.context = context1;

        }

        @Override
        public CartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = layoutInflater.inflate(R.layout.cartlist_cardview, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final CartListAdapter.ViewHolder holder, final int position) {

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 2, height / 4);
            holder.cartImageLayout.setLayoutParams(params);

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(width / 2, height / 4);
            holder.infoLayout.setLayoutParams(param);


            holder.txtName.setText(cartlistdataforViewList.get(position).getProduct_name());
            product_sell_price = cartlistdataforViewList.get(position).getSell_price();
            holder.txtPrice.setText(product_sell_price + " MMK");
            image_encode = cartlistdataforViewList.get(position).getImage_encode();
            byte[] decodeValue = Base64.decode(image_encode, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeValue, 0, decodeValue.length);
            holder.imageView.setImageBitmap(bitmap);
            holder.txtQty.setText(cartlistdataforViewList.get(position).getQty() + "");

            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int qty = cartlistdataforViewList.get(position).getQty();

                    if (qty < 3) {

                        cartlistdataforViewList.get(position).setQty(qty + 1);
                        OriginalValue = forTotalAmountList.get(position).getSell_price();
                        double d1 = cartlistdataforViewList.get(position).getSell_price();
                        int i1 = cartlistdataforViewList.get(position).getQty();
                        holder.txtPrice.setText(OriginalValue + d1 + "  MMK");
                        cartListAdapter.notifyDataSetChanged();
                        cartlistdataforViewList.get(position).setSell_price(OriginalValue * i1);


                    } else {

                        Toast.makeText(context, "Lol", Toast.LENGTH_SHORT).show();

                    }

                    getTotalPrice();
                }
            });


            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int qty = cartlistdataforViewList.get(position).getQty();

                    if (qty > 1) {

                        cartlistdataforViewList.get(position).setQty(qty - 1);
                        OriginalValue = forTotalAmountList.get(position).getSell_price();
                        double d1 = cartlistdataforViewList.get(position).getSell_price();
                        int i1 = cartlistdataforViewList.get(position).getQty();
                        holder.txtPrice.setText(OriginalValue + d1 + "  MMK");
                        cartListAdapter.notifyDataSetChanged();
                        cartlistdataforViewList.get(position).setSell_price(OriginalValue * i1);


                    } else {

                        Toast.makeText(context, "Lol", Toast.LENGTH_SHORT).show();

                    }

                    getTotalPrice();

                }
            });


            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int remove_id = cartlistdataforViewList.get(position).getId();

                    sqLiteDatabase.beginTransaction();

                    sqLiteDatabase.execSQL("DELETE FROM stock_reserve WHERE id=" + remove_id + "");

                    sqLiteDatabase.setTransactionSuccessful();
                    sqLiteDatabase.endTransaction();

                    cartlistdataforViewList.remove(position);
                    forTotalAmountList.remove(position);

                    cartListAdapter.notifyDataSetChanged();
                    getTotalPrice();

                }
            });


        }

        @Override
        public int getItemCount() {
            return cartlistdataforViewList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtName, txtPrice, txtQty;
            ImageView imageView;
            Button plus, minus, remove;
            LinearLayout cartImageLayout;
            LinearLayout infoLayout;

            public ViewHolder(View itemView) {
                super(itemView);

                txtName = (TextView) itemView.findViewById(R.id.txtCartProductName);
                txtPrice = (TextView) itemView.findViewById(R.id.txtCartProductPrice);
                txtQty = (TextView) itemView.findViewById(R.id.txtQty);
                imageView = (ImageView) itemView.findViewById(R.id.imgCart);
                plus = (Button) itemView.findViewById(R.id.btnQtyPlus);
                minus = (Button) itemView.findViewById(R.id.btnQtyMinus);
                remove = (Button) itemView.findViewById(R.id.btnCartRemove);
                cartImageLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout_imgage_cart);
                infoLayout = (LinearLayout) itemView.findViewById(R.id.infoLayout);

            }
        }
    }

    private void getTotalPrice() {
        totalPrice = 0;
        for (int i = 0; i < cartlistdataforViewList.size(); i++) {
            totalPrice += cartlistdataforViewList.get(i).getSell_price();
        }

        Log.i("t_price", "" + totalPrice);


        txtTotalAmount.setText(totalPrice + " MMK");
        if (cartlistdataforViewList.size() > 1) {
            txtItemCount.setText("Total " + cartlistdataforViewList.size() + " items");
        } else {
            txtItemCount.setText("Total " + cartlistdataforViewList.size() + " item");
        }

    }

    private void SetCartAdapter() {

        RetrieveCartListData();

        getTotalPrice();

        cartRecyclerView = (RecyclerView) findViewById(R.id.cartlist_recycler_view);
        cartListAdapter = new CartListAdapter(this, cartlistdataforViewList, forTotalAmountList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(layoutManager);
        cartRecyclerView.setItemAnimator(new DefaultItemAnimator());
        cartRecyclerView.setAdapter(cartListAdapter);
        cartListAdapter.notifyDataSetChanged();


    }

    public void findViewById() {


        txtItemCount = (TextView) findViewById(R.id.totalQty);
        txtTotalAmount = (TextView) findViewById(R.id.totalPrice);
        btnCheckOut = (Button) findViewById(R.id.btnCheckOut);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (come_from_which_class.equalsIgnoreCase("MainActivity")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (come_from_which_class.equalsIgnoreCase("ProductActivity")) {
            Intent intent = new Intent(this, ProductActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", ProductActivity.productDataForViews);
            intent.putExtra("BUNDLE", args);
            startActivity(intent);
            finish();
        } else if (come_from_which_class.equalsIgnoreCase("WishListActivity")) {
            Intent intent = new Intent(this, WishListActivity.class);
            startActivity(intent);
            finish();
        } else if (come_from_which_class.equalsIgnoreCase("User")) {
            Intent intent = new Intent(this, User.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
