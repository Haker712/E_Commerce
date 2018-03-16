package com.aceplus.e_commerce;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aceplus.e_commerce.NaviExpListView.FirstExpListAdapter;
import com.aceplus.e_commerce.NaviExpListView.MyLinkedMap;
import com.aceplus.e_commerce.apiService.DownloadService;
import com.aceplus.e_commerce.apiService.RetrofitServiceFactory;
import com.aceplus.e_commerce.model.CategoryModelsForNavi.Sub1CategoryData;
import com.aceplus.e_commerce.model.CategoryModelsForNavi.Sub2CategoryData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.CategoryResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.CategoryResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.CategoryResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductCategoriesResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductCategoriesResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductCategoriesResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductColorResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductColorResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductColorResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductDescriptionResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductDescriptionResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductDescriptionResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductDiscountResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductDiscountResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductDiscountResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductGalleryResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductGalleryResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductGalleryResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductGroupResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductGroupResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductGroupResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductSizeResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductSizeResponseData;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductSizeResponseObj;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.SetUpRequest;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.SetUpRequest_Data;
import com.aceplus.e_commerce.model.CategoryModelsForNavi.CategoryDataForView;
import com.aceplus.e_commerce.recyclerviewAdaptersforHomeFragment.MainRecyclerAdapter;
import com.aceplus.e_commerce.utils.Constant;
import com.aceplus.e_commerce.utils.DatabaseContract;
import com.aceplus.e_commerce.utils.MyDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;

    int country_Version, state_Version, township_version, category_Version, product_Version, productColor_Version, product_Size_Version, product_Descriprion_Version, productGallery_Version, productGroup_Version, productRating_Version, productReview_Version, productkeysFeature_Version, productDiscount_Version, stockReserve_Version, brands_Version, productCategories_Version;

    List<SetUpRequest_Data> setupRequestDatas = new ArrayList<>();


    CategoryResponseObj categoryResponseObj;
    List<CategoryResponseData> categoryResponseDatas = new ArrayList<>();
    ProductResponseObj productResponseObj;
    List<ProductResponseData> productResponseDatas = new ArrayList<>();
    ProductColorResponseObj productColorResponseObj;
    List<ProductColorResponseData> productColorResponseDatas = new ArrayList<>();
    ProductSizeResponseObj productSizeResponseObj;
    List<ProductSizeResponseData> productSizeResponseDatas = new ArrayList<>();
    ProductDescriptionResponseObj productDescriptionResponseObj;
    List<ProductDescriptionResponseData> productDescriptionResponseDatas = new ArrayList<>();
    ProductGalleryResponseObj productGalleryResponseObj;
    List<ProductGalleryResponseData> productGalleryResponseDatas = new ArrayList<>();
    ProductGroupResponseObj productGroupResponseObj;
    List<ProductGroupResponseData> productGroupResponseDatas = new ArrayList<>();
    ProductDiscountResponseObj productDiscountResponseObj;
    List<ProductDiscountResponseData> productDiscountResponseDatas = new ArrayList<>();
    ProductCategoriesResponseObj productCategoriesResponseObj;
    List<ProductCategoriesResponseData> productCategoriesResponseDatas = new ArrayList<>();


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    FirstExpListAdapter firstExpListAdapter;
    ExpandableListView naviExpListview;


    ArrayList<CategoryDataForView> categoryDataForViewList = new ArrayList<>();

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainrecyclerView;

    AdapterViewFlipper adapterViewFlipper;
    FlipperAdapter flipperAdapter;
    int[] imgs;

    ProgressDialog pd;

    SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("E_Market");

        sqLiteDatabase = new MyDatabase(MainActivity.this).getDataBase();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        CategoryDownload();
        setAdapter();


        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        naviExpListview.setIndicatorBounds(GetPixelFromDips(300) - GetPixelFromDips(50), GetPixelFromDips(300) - GetPixelFromDips(10));


        imgs = new int[]{R.mipmap.flipperimg1, R.mipmap.flipperimg2, R.mipmap.flipperimg3};

        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.viewFlipper);
        flipperAdapter = new FlipperAdapter(imgs);
        adapterViewFlipper.setAdapter(flipperAdapter);

        flipperview_init();

        adapterViewFlipper.setInAnimation(this, R.animator.right_in);
        adapterViewFlipper.setOutAnimation(this, R.animator.left_out);
        adapterViewFlipper.showNext();

        RatioForLayouts();


        /***
         * For Testing
         */

        prefs = getSharedPreferences("MYPRE", MODE_PRIVATE);

        if (prefs.getString(SearchActivity.MY_PREFS_NAME, "")!=null && !prefs.getString(SearchActivity.MY_PREFS_NAME, "").equals("")){

            Constant.BASE_URL=prefs.getString(SearchActivity.MY_PREFS_NAME,"");
            Log.i("BaseURL",Constant.BASE_URL);

        }else {
            Constant.BASE_URL="http://192.168.7.194:8094/api/";
        }

        /***
         * For Testing
         */


    }


    private void addDrawerItems() {

        RetrieveCategories();

        naviExpListview = (ExpandableListView) findViewById(R.id.navExpList);

        MyLinkedMap<CategoryDataForView, List<Sub1CategoryData>> Sub1List = new MyLinkedMap<>();

        //List<Sub1CategoryData> sub1CategoryDatas=new ArrayList<>();

        for (CategoryDataForView categoryDataForView : categoryDataForViewList) {

            Sub1List.put(categoryDataForView, categoryDataForView.getSub1CategoryDataList());
        }


        firstExpListAdapter = new FirstExpListAdapter(this, categoryDataForViewList, Sub1List);
        naviExpListview.setAdapter(firstExpListAdapter);

    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Categories");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

                adapterViewFlipper.setEnabled(false);
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("E_Market");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {

            case R.id.user:
                // TODO put your code here to respond to the button tap
                Toast.makeText(this, "User!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, User.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.cart:
                // TODO put your code here to respond to the button tap
                Toast.makeText(this, "Cart!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, CartListActivity.class);
                intent1.putExtra("Class","MainActivity");
                startActivity(intent1);
                finish();
                return true;

            case R.id.search:
                // TODO put your code here to respond to the button tap
                Toast.makeText(this, "Cart!", Toast.LENGTH_SHORT).show();
                Backup();
                Intent intent2 = new Intent(this, SearchActivity.class);
                startActivity(intent2);
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        addDrawerItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDrawerItems();
    }


    /***
     * Download for SetUp Data
     */

    public void CategoryDownload() {

        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='categories'", null);

        while (cursor.moveToNext()) {

            category_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("categories");
        setUpRequest_data.setVersion(category_Version);

        setupRequestDatas.add(setUpRequest_data);

        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);

        String param_Data = getJsonFromObject(setUpRequest);

        Log.i("CategoryParam", param_Data);
        Toast.makeText(this, "CategoryParam", Toast.LENGTH_SHORT).show();


        DownloadService downloadService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<CategoryResponse> call = downloadService.downloadCategory(param_Data);
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {

                        categoryResponseObj = response.body().getCategoryResponseObj();

                        category_Version = categoryResponseObj.getVersion();
                        Log.i("Categories_Version", category_Version + "");
                        categoryResponseDatas = categoryResponseObj.getCategoryResponseDatas();
                        Log.i("Size", categoryResponseDatas.size() + "");


                        if (categoryResponseDatas.size() != 0) {

                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from categories");

                            for (CategoryResponseData categoryResponseData : categoryResponseDatas) {


                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Category.id, categoryResponseData.getId());
                                contentValues.put(DatabaseContract.Category.name, categoryResponseData.getName());
                                contentValues.put(DatabaseContract.Category.parent_id, categoryResponseData.getParent_id());
                                contentValues.put(DatabaseContract.Category.image, categoryResponseData.getImage());
                                contentValues.put(DatabaseContract.Category.image_encode, categoryResponseData.getImage_encode());
                                contentValues.put(DatabaseContract.Category.status, categoryResponseData.getStatus());


                                sqLiteDatabase.insert(DatabaseContract.Category.tb, null, contentValues);
                            }

                            UpdateVersion("categories", category_Version);

                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();

                            ProductDownload();


                        } else {

                            ProductDownload();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "GG HERE", Toast.LENGTH_SHORT).show();


                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void ProductDownload() {

        Log.i("LOG", "IM HERE");
        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='products'", null);

        while (cursor.moveToNext()) {

            product_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("products");
        setUpRequest_data.setVersion(product_Version);

        setupRequestDatas.clear();
        setupRequestDatas.add(setUpRequest_data);


        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);


        String param_Data = getJsonFromObject(setUpRequest);
        Log.i("ProductParam", param_Data);


        DownloadService downloadService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<ProductResponse> call = downloadService.downloadProduct(param_Data);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {


                        productResponseObj = response.body().getProductResponseObj();
                        product_Version = productResponseObj.getVersion();
                        productResponseDatas = productResponseObj.getProductResponseDatas();

                        if (productResponseDatas.size() != 0) {

                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from products");

                            for (ProductResponseData productResponseData : productResponseDatas) {

                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Product.id, productResponseData.getId());
                                contentValues.put(DatabaseContract.Product.product_code, productResponseData.getProduct_code());
                                contentValues.put(DatabaseContract.Product.product_group_id, productResponseData.getProduct_group_id());
                                contentValues.put(DatabaseContract.Product.product_size_id, productResponseData.getProduct_size_id());


                                sqLiteDatabase.insert(DatabaseContract.Product.tb, null, contentValues);


                            }

                            Log.i("ProductVersionHERE", product_Version + "");
                            UpdateVersion("products", product_Version);

                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();

                            ProductColorDownload();


                        } else {

                            ProductColorDownload();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();


            }
        });

    }

    public void ProductColorDownload() {

        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='product_color'", null);

        while (cursor.moveToNext()) {

            productColor_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("product_color");
        setUpRequest_data.setVersion(productColor_Version);


        setupRequestDatas.clear();
        setupRequestDatas.add(setUpRequest_data);


        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);


        String param_Data = getJsonFromObject(setUpRequest);

        Log.i("ProductColorParam", param_Data);

        DownloadService downloadService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<ProductColorResponse> call = downloadService.downloadProductColor(param_Data);
        call.enqueue(new Callback<ProductColorResponse>() {
            @Override
            public void onResponse(Call<ProductColorResponse> call, Response<ProductColorResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {

                        productColorResponseObj = response.body().getProductColorResponseObj();
                        productColor_Version = productColorResponseObj.getVersion();
                        productColorResponseDatas = productColorResponseObj.getProductColorResponseDatas();

                        if (productColorResponseDatas.size() != 0) {


                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from product_color");


                            for (ProductColorResponseData productColorResponseData : productColorResponseDatas) {


                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Product_color.id, productColorResponseData.getId());
                                contentValues.put(DatabaseContract.Product_color.name, productColorResponseData.getName());
                                contentValues.put(DatabaseContract.Product_color.remark, productColorResponseData.getRemark());


                                sqLiteDatabase.insert(DatabaseContract.Product_color.tb, null, contentValues);


                            }
                            UpdateVersion("product_color", productColor_Version);


                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();

                            ProductSizeDownload();


                        } else {

                            ProductSizeDownload();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductColorResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void ProductSizeDownload() {

        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='product_size'", null);

        while (cursor.moveToNext()) {

            product_Size_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("product_size");
        setUpRequest_data.setVersion(product_Size_Version);

        setupRequestDatas.clear();
        setupRequestDatas.add(setUpRequest_data);

        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);

        String param_Data = getJsonFromObject(setUpRequest);

        Log.i("ProductSize", param_Data);

        DownloadService downloadService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<ProductSizeResponse> call = downloadService.downloadProductSize(param_Data);
        call.enqueue(new Callback<ProductSizeResponse>() {
            @Override
            public void onResponse(Call<ProductSizeResponse> call, Response<ProductSizeResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {

                        productSizeResponseObj = response.body().getProductSizeResponseObj();
                        product_Size_Version = productSizeResponseObj.getVersion();
                        productSizeResponseDatas = productSizeResponseObj.getProductSizeResponseDatas();

                        Log.i("Size", productSizeResponseDatas.size() + "");

                        if (productSizeResponseDatas.size() != 0) {

                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from product_size");

                            for (ProductSizeResponseData productSizeResponseData : productSizeResponseDatas) {


                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Product_size.id, productSizeResponseData.getId());
                                contentValues.put(DatabaseContract.Product_size.name, productSizeResponseData.getName());
                                contentValues.put(DatabaseContract.Product_size.remark, productSizeResponseData.getRemark());


                                sqLiteDatabase.insert(DatabaseContract.Product_size.tb, null, contentValues);


                            }
                            UpdateVersion("product_size", product_Size_Version);

                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();

                            ProductDescriptionDownload();

                        } else {

                            ProductDescriptionDownload();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductSizeResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ProductDescriptionDownload() {

        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='product_description'", null);

        while (cursor.moveToNext()) {

            product_Descriprion_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("product_description");
        setUpRequest_data.setVersion(product_Descriprion_Version);

        setupRequestDatas.clear();
        setupRequestDatas.add(setUpRequest_data);

        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);

        String param_Data = getJsonFromObject(setUpRequest);

        Log.i("ProductDescriptionParam", param_Data);

        DownloadService sync_downService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<ProductDescriptionResponse> call = sync_downService.downloadProductDescription(param_Data);
        call.enqueue(new Callback<ProductDescriptionResponse>() {
            @Override
            public void onResponse(Call<ProductDescriptionResponse> call, Response<ProductDescriptionResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {

                        productDescriptionResponseObj = response.body().getProductDescriptionResponseObj();
                        product_Descriprion_Version = productDescriptionResponseObj.getVersion();
                        productDescriptionResponseDatas = productDescriptionResponseObj.getProductDescriptionResponseDatas();


                        if (productDescriptionResponseDatas.size() != 0) {

                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from product_description");


                            for (ProductDescriptionResponseData productDescriptionResponseData : productDescriptionResponseDatas) {

                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Product_description.id, productDescriptionResponseData.getId());
                                contentValues.put(DatabaseContract.Product_description.product_group_id, productDescriptionResponseData.getProduct_group_id());
                                contentValues.put(DatabaseContract.Product_description.description, productDescriptionResponseData.getDescription());
                                contentValues.put(DatabaseContract.Product_description.status, productDescriptionResponseData.getStatus());


                                sqLiteDatabase.insert(DatabaseContract.Product_description.tb, null, contentValues);


                            }
                            UpdateVersion("product_description", product_Descriprion_Version);

                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();


                            ProductGalleryDownload();


                        } else {

                            ProductGalleryDownload();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductDescriptionResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void ProductGalleryDownload() {


        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='product_gallery'", null);

        while (cursor.moveToNext()) {

            productGallery_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("product_gallery");
        setUpRequest_data.setVersion(productGallery_Version);

        setupRequestDatas.clear();
        setupRequestDatas.add(setUpRequest_data);

        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);

        String param_Data = getJsonFromObject(setUpRequest);

        DownloadService downloadService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<ProductGalleryResponse> call = downloadService.downloadProductGallery(param_Data);
        call.enqueue(new Callback<ProductGalleryResponse>() {
            @Override
            public void onResponse(Call<ProductGalleryResponse> call, Response<ProductGalleryResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {

                        productGalleryResponseObj = response.body().getProductGalleryResponseObj();
                        productGallery_Version = productGalleryResponseObj.getVersion();
                        productGalleryResponseDatas = productGalleryResponseObj.getProductGalleryResponseDatas();

                        if (productGalleryResponseDatas.size() != 0) {

                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from product_gallery");

                            for (ProductGalleryResponseData productGalleryResponseData : productGalleryResponseDatas) {


                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Product_gallery.id, productGalleryResponseData.getId());
                                contentValues.put(DatabaseContract.Product_gallery.product_id, productGalleryResponseData.getProduct_id());
                                contentValues.put(DatabaseContract.Product_gallery.image, productGalleryResponseData.getImage());
                                contentValues.put(DatabaseContract.Product_gallery.image_encode, productGalleryResponseData.getImage_encode());


                                sqLiteDatabase.insert(DatabaseContract.Product_gallery.tb, null, contentValues);


                            }
                            UpdateVersion("product_gallery", productGallery_Version);

                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();

                            ProductGroupDownload();

                        } else {
                            ProductGroupDownload();
                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductGalleryResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ProductGroupDownload() {

        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='product_group'", null);

        while (cursor.moveToNext()) {

            productGroup_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("product_group");
        setUpRequest_data.setVersion(productGroup_Version);

        setupRequestDatas.clear();
        setupRequestDatas.add(setUpRequest_data);

        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);

        String param_Data = getJsonFromObject(setUpRequest);

        DownloadService downloadService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<ProductGroupResponse> call = downloadService.downloadProductGroup(param_Data);
        call.enqueue(new Callback<ProductGroupResponse>() {
            @Override
            public void onResponse(Call<ProductGroupResponse> call, Response<ProductGroupResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {

                        productGroupResponseObj = response.body().getProductGroupResponseObj();
                        productGroup_Version = productGroupResponseObj.getVersion();
                        productGroupResponseDatas = productGroupResponseObj.getProductGroupResponseDatas();

                        if (productGroupResponseDatas.size() != 0) {

                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from product_group");


                            for (ProductGroupResponseData productGroupResponseData : productGroupResponseDatas) {


                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Product_group.id, productGroupResponseData.getId());
                                contentValues.put(DatabaseContract.Product_group.model, productGroupResponseData.getModel());
                                contentValues.put(DatabaseContract.Product_group.group_code, productGroupResponseData.getGroup_code());
                                contentValues.put(DatabaseContract.Product_group.brand_id, productGroupResponseData.getBrand_id());
                                contentValues.put(DatabaseContract.Product_group.has_variance, productGroupResponseData.getHas_variance());
                                contentValues.put(DatabaseContract.Product_group.product_name, productGroupResponseData.getProduct_name());
                                contentValues.put(DatabaseContract.Product_group.description, productGroupResponseData.getDescription());
                                contentValues.put(DatabaseContract.Product_group.status, productGroupResponseData.getStatus());
                                contentValues.put(DatabaseContract.Product_group.product_sku, productGroupResponseData.getProduct_sku());
                                contentValues.put(DatabaseContract.Product_group.manufacturer_id, productGroupResponseData.getManufacturer_id());
                                contentValues.put(DatabaseContract.Product_group.cost_price, productGroupResponseData.getCost_price());
                                contentValues.put(DatabaseContract.Product_group.base_price, productGroupResponseData.getBase_price());
                                contentValues.put(DatabaseContract.Product_group.weight, productGroupResponseData.getWeight());
                                contentValues.put(DatabaseContract.Product_group.length, productGroupResponseData.getLength());
                                contentValues.put(DatabaseContract.Product_group.width, productGroupResponseData.getWidth());
                                contentValues.put(DatabaseContract.Product_group.height, productGroupResponseData.getHeight());
                                contentValues.put(DatabaseContract.Product_group.product_color_id, productGroupResponseData.getProduct_color_id());


                                sqLiteDatabase.insert(DatabaseContract.Product_group.tb, null, contentValues);


                            }

                            UpdateVersion("product_group", productGroup_Version);

                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();

                            ProductDiscountDownload();


                        } else {
                            ProductDiscountDownload();


                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductGroupResponse> call, Throwable t) {


                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ProductDiscountDownload() {

        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='product_discount'", null);

        while (cursor.moveToNext()) {

            productDiscount_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("product_discount");
        setUpRequest_data.setVersion(productDiscount_Version);

        setupRequestDatas.clear();
        setupRequestDatas.add(setUpRequest_data);

        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);

        String param_Data = getJsonFromObject(setUpRequest);


        Log.i("Param", param_Data + "");

        DownloadService downloadService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<ProductDiscountResponse> call = downloadService.downloadProductDiscount(param_Data);
        call.enqueue(new Callback<ProductDiscountResponse>() {
            @Override
            public void onResponse(Call<ProductDiscountResponse> call, Response<ProductDiscountResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {


                        productDiscountResponseObj = response.body().getProductDiscountResponseObj();
                        productDiscount_Version = productDiscountResponseObj.getVersion();
                        productDiscountResponseDatas = productDiscountResponseObj.getProductDiscountResponseDatas();

                        if (productDiscountResponseDatas.size() != 0) {

                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from product_discount");

                            for (ProductDiscountResponseData productDiscountResponseData : productDiscountResponseDatas) {


                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Product_discount.id, productDiscountResponseData.getId());
                                contentValues.put(DatabaseContract.Product_discount.discount_type, productDiscountResponseData.getDiscount_type());
                                contentValues.put(DatabaseContract.Product_discount.discount_amount, productDiscountResponseData.getDiscount_amount());
                                contentValues.put(DatabaseContract.Product_discount.with_expiry_date, productDiscountResponseData.getWith_expiry_date());
                                contentValues.put(DatabaseContract.Product_discount.discount_from, productDiscountResponseData.getDiscount_from());
                                contentValues.put(DatabaseContract.Product_discount.discount_to, productDiscountResponseData.getDiscount_to());
                                contentValues.put(DatabaseContract.Product_discount.status, productDiscountResponseData.getStatus());
                                contentValues.put(DatabaseContract.Product_discount.product_group_id, productDiscountResponseData.getProduct_group_id());
                                contentValues.put(DatabaseContract.Product_discount.discount_name, productDiscountResponseData.getDiscount_name());


                                sqLiteDatabase.insert(DatabaseContract.Product_discount.tb, null, contentValues);


                            }
                            UpdateVersion("product_discount", productDiscount_Version);

                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();


                            ProductCategoriesDownload();


                        } else {

                            ProductCategoriesDownload();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductDiscountResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void ProductCategoriesDownload() {

        SetUpRequest setUpRequest = new SetUpRequest();
        SetUpRequest_Data setUpRequest_data = new SetUpRequest_Data();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM version WHERE name='product_categories'", null);

        while (cursor.moveToNext()) {

            productCategories_Version = cursor.getInt(cursor.getColumnIndex("version"));

        }

        setUpRequest_data.setName("product_categories");
        setUpRequest_data.setVersion(productCategories_Version);

        setupRequestDatas.clear();
        setupRequestDatas.add(setUpRequest_data);

        setUpRequest.setSite_activation_key("1234567");
        setUpRequest.setSetUpRequest_data(setupRequestDatas);

        String param_Data = getJsonFromObject(setUpRequest);

        DownloadService downloadService = RetrofitServiceFactory.createService(DownloadService.class);
        Call<ProductCategoriesResponse> call = downloadService.downloadProductCategories(param_Data);
        call.enqueue(new Callback<ProductCategoriesResponse>() {
            @Override
            public void onResponse(Call<ProductCategoriesResponse> call, Response<ProductCategoriesResponse> response) {

                if (response.code() == 200) {

                    if (response.body().getAceplusStatusCode() == 200) {

                        productCategoriesResponseObj = response.body().getProductCategoriesResponseObj();
                        productCategories_Version = productCategoriesResponseObj.getVersion();
                        productCategoriesResponseDatas = productCategoriesResponseObj.getProductCategoriesResponseDatas();

                        if (productCategoriesResponseDatas.size() != 0) {

                            sqLiteDatabase.beginTransaction();

                            sqLiteDatabase.execSQL("Delete from product_categories");

                            for (ProductCategoriesResponseData productCategoriesResponseData : productCategoriesResponseDatas) {


                                ContentValues contentValues = new ContentValues();

                                contentValues.put(DatabaseContract.Product_categories.id, productCategoriesResponseData.getId());
                                contentValues.put(DatabaseContract.Product_categories.product_group_id, productCategoriesResponseData.getProduct_group_id());
                                contentValues.put(DatabaseContract.Product_categories.category_id, productCategoriesResponseData.getCategory_id());


                                sqLiteDatabase.insert(DatabaseContract.Product_categories.tb, null, contentValues);


                            }
                            UpdateVersion("product_categories", productCategories_Version);

                            sqLiteDatabase.setTransactionSuccessful();
                            sqLiteDatabase.endTransaction();

                            setAdapter();

                        } else {

                            setAdapter();
                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductCategoriesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getJsonFromObject(Object object) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonString = gson.toJson(object);
        return jsonString;
    }

    public void UpdateVersion(String name, int version) {

        sqLiteDatabase.execSQL("UPDATE version SET version=" + version + " WHERE name='" + name + "'");

    }

    public void Backup() {

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                Toast.makeText(getApplicationContext(), "Backup database is starting...",
                        Toast.LENGTH_SHORT).show();
                String currentDBPath = "/data/com.aceplus.e_commerce/databases/e_commerce.sqlite";
                //String currentDBPath = "/data/com.aceplus.myanmar_padauk/databases/myanmar-padauk.db";

                String backupDBPath = "e_c_DB_Backup_.db";
                File currentDB = new File(data, currentDBPath);

                String folderPath = "mnt/sdcard/E_C_DB_Backup";
                File f = new File(folderPath);
                f.mkdir();
                File backupDB = new File(f, backupDBPath);
                FileChannel source = new FileInputStream(currentDB).getChannel();
                FileChannel destination = new FileOutputStream(backupDB).getChannel();
                destination.transferFrom(source, 0, source.size());
                source.close();
                destination.close();
                Toast.makeText(getApplicationContext(), "Backup database Successful!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Please set Permission for Storage in Setting!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot Backup!",
                    Toast.LENGTH_SHORT).show();
        }

    }


    public void RetrieveCategories() {

        int category_id, sub1_category_id, sub2_category_id;
        String category_name, sub1_category_name, sub2_category_name;
        categoryDataForViewList = new ArrayList<>();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM categories WHERE parent_id='" + 0 + "'", null);

        while (cursor.moveToNext()) {

            CategoryDataForView categorydataforView = new CategoryDataForView();

            category_id = cursor.getInt(cursor.getColumnIndex("id"));
            category_name = cursor.getString(cursor.getColumnIndex("name"));

            categorydataforView.setId(category_id);
            categorydataforView.setName(category_name);

            categoryDataForViewList.add(categorydataforView);

            String query = "SELECT * FROM categories WHERE parent_id=" + category_id;
            Cursor cursor1 = sqLiteDatabase.rawQuery(query, null);

            ArrayList<Sub1CategoryData> sub1CategoryDataArrayList = new ArrayList<>();


            while (cursor1.moveToNext()) {

                Sub1CategoryData sub1CategoryData = new Sub1CategoryData();

                sub1_category_id = cursor1.getInt(cursor1.getColumnIndex("id"));
                sub1_category_name = cursor1.getString(cursor1.getColumnIndex("name"));

                sub1CategoryData.setSub1_id(sub1_category_id);
                sub1CategoryData.setSub1_Name(sub1_category_name);

                sub1CategoryDataArrayList.add(sub1CategoryData);

                categorydataforView.setSub1CategoryDataList(sub1CategoryDataArrayList);


                Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM categories WHERE parent_id=" + sub1_category_id + "", null);

                ArrayList<Sub2CategoryData> sub2CategoryDataArrayList = new ArrayList<>();

                while (cursor2.moveToNext()) {

                    Sub2CategoryData sub2CategoryData = new Sub2CategoryData();

                    sub2_category_id = cursor2.getInt(cursor2.getColumnIndex("id"));
                    sub2_category_name = cursor2.getString(cursor2.getColumnIndex("name"));

                    sub2CategoryData.setSub2_id(sub2_category_id);
                    sub2CategoryData.setSub2_Name(sub2_category_name);

                    sub2CategoryDataArrayList.add(sub2CategoryData);

                    sub1CategoryData.setSub2CategoryDataList(sub2CategoryDataArrayList);
                }

            }


        }

    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }


    public void setAdapter() {

        mainrecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        mainRecyclerAdapter = new MainRecyclerAdapter(this);
        mainRecyclerAdapter.notifyDataSetChanged();
        mainrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainrecyclerView.setAdapter(mainRecyclerAdapter);

    }


    public void flipperview_init() {

        adapterViewFlipper.setFlipInterval(5000);
        adapterViewFlipper.setAutoStart(true);

    }

    public class FlipperAdapter extends BaseAdapter {


        //List<ProductGalleryResponseData> productGalleryList;

        int[] imgs;

        public FlipperAdapter(int[] imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            //return productGalleryList.size();
            return imgs.length;
        }

        @Override
        public Object getItem(int position) {
            return imgs[position];
            // return productGalleryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //productGallery = productGalleryList.get(position);

            Log.i("Position", position + "");

            int img = imgs[position];


            if (convertView == null) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.viewflipperlayout, null);

            }


            ImageView imageView = (ImageView) convertView.findViewById(R.id.ImgView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            imageView.setBackground(getResources().getDrawable(img));


            return convertView;
        }
    }

    public void RatioForLayouts() {


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        int heightRatioForViewflipper = height / 5;


        adapterViewFlipper.setLayoutParams(new LinearLayout.LayoutParams(width, heightRatioForViewflipper));


    }


}
