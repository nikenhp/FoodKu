package com.example.user.foodku.rest;

import android.widget.Spinner;

import com.example.user.foodku.model.LoginModel;
import com.example.user.foodku.model.MenuModel;
import com.example.user.foodku.model.Ordermenu;
import com.example.user.foodku.model.OrdermenuModel;
import com.example.user.foodku.model.OrdermenudetailModel;
import com.example.user.foodku.model.UserModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class APIService {
    public static String BASE_URL = "http://192.168.43.10/foodku_webmaster-master/public/api/";
    public static PostService service_post = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(APIService.PostService.class);

    public static GetService service_get = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(APIService.GetService.class);


    public interface GetService{

        @GET("get_menu_all")
        Call<MenuModel> getMenu();

        @GET("get_order_all")
        Call<OrdermenuModel> getOrder();

    }

    public interface PostService{
        @POST("login")
        @FormUrlEncoded
        Call<LoginModel> postLogin(
                @Field("email") String username,
                @Field("password_hash") String password_hash
        );


        @POST("create_order")
        @FormUrlEncoded
        Call<OrdermenuModel> postOrder(
                @Field("id_table") String id_table,
                @Field("pelanggan") String pelanggan
        );

        @POST("add_menu_to_order")
        @FormUrlEncoded
        Call<OrdermenudetailModel> postDetail(
                @Field("id_order") String id_table,
                @Field("id_menu") String id_menu,
                @Field("kuantitas") String kuantitas
        );
    }
}
