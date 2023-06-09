package com.example.pokemoncard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemoncard.Api.PokeService;
import com.example.pokemoncard.entities.PokemonInfo;
import com.example.pokemoncard.entities.StatesPoke;
import com.example.pokemoncard.entities.TypesPoke;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GridItemActivity extends AppCompatActivity {

        private int id;
        private int currentProgress =0;
        private ProgressBar progressBar ;



        private ImageView imgView;
        private TextView namePoke;
        private TextView weight;
        private TextView height;
        private TextView type1;


        private Retrofit retrofit;
        private ArrayList<TypesPoke> typesData= new ArrayList<>();
        private ArrayList<StatesPoke> stateData =new ArrayList<>();
         View v;



        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            System.out.println("Im here  eeeeeeeeeeee");
            setContentView(R.layout.grid_item);
            Intent intent = getIntent();
            id = intent.getIntExtra("id", 0);

            imgView = (ImageView) findViewById(R.id.imagePoke); // init a ImageView
            namePoke = (TextView) findViewById(R.id.namePoke); // init a ImageView
            weight = (TextView) findViewById(R.id.weight); // init a ImageView
            height = (TextView) findViewById(R.id.height);
            Glide.with(this)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"+id+".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgView);

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PokeService pokeService = retrofit.create(PokeService.class);
            Call<PokemonInfo> pokemonInfo = pokeService.pokemonInfo(id);



            pokemonInfo.enqueue(new Callback<PokemonInfo>() {

                @Override
                public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {


                    if(response.isSuccessful()){

                        PokemonInfo pokemonInfo = response.body();

                        //Get name of types Pokemon and Color Types
                        List<TypesPoke> typesList = pokemonInfo.getTypes();

                        for (int i = 0; i < typesList.size(); i++) {
                            TypesPoke type = typesList.get(i);
                            String nameType = type.getType().getName();
                            System.out.println("   naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaame  ::"+nameType);
                            System.out.println("   naaaaaaiddddddddddddddd  ::"+i);
                            typesData.add(new TypesPoke(nameType));
                             if(i==0){
                                 type1 = findViewById(R.id.type1);
                                 type1.setText(nameType);
                             }
                            if(i==1){
                                type1 = findViewById(R.id.type2);
                                type1.setText(nameType);
                            }
                        }

                        List<StatesPoke> stateList = pokemonInfo.getStats();
                        for (int i = 0; i < stateList.size(); i++) {
                            StatesPoke state = stateList.get(i);
                            String baseState = state.getBaseStat();

                            String stateName = state.getState().getName();
                            //System.out.println("    :::"+baseState);
                            stateData.add(new StatesPoke(stateName,baseState));
                            if(stateName.equals("hp")){
                                progressBar = findViewById(R.id.hpbar);
                                progressBar.setMax(300);
                                progressBar.setProgress(Integer.parseInt(baseState));
                            }
                            if(stateName.equals("attack")){
                                progressBar = findViewById(R.id.atkbar);
                                progressBar.setMax(300);
                                progressBar.setProgress(Integer.parseInt(baseState));
                            }
                            if(stateName.equals("defense")){
                                progressBar = findViewById(R.id.defbar);
                                progressBar.setMax(300);
                                progressBar.setProgress(Integer.parseInt(baseState));
                            }
                            if(stateName.equals("speed")){
                                progressBar = findViewById(R.id.spdbar);
                                progressBar.setMax(300);
                                progressBar.setProgress(Integer.parseInt(baseState));
                            }
                        }


                        PokemonInfo pkInfo = new PokemonInfo(pokemonInfo.getName(), stateData, typesData, pokemonInfo.getHeight(), pokemonInfo.getWeight(),pokemonInfo.getExpBase());
                        System.out.println(pkInfo.toString());
                        namePoke.setText(pkInfo.getName());
                        String heightFormatted = String.format("%.1f M", (float) pkInfo.getHeight() / 10);
                        String weightFormatted = String.format("%.1f KG", (float) pkInfo.getWeight() / 10);
                        height.setText(heightFormatted);
                        weight.setText(weightFormatted);
                        progressBar = findViewById(R.id.expbar);
                        progressBar.setMax(1000);
                        progressBar.setProgress(pokemonInfo.getExpBase());
                        /*progressBarLiveData.setValue(false);
                        pokemonInfoLiveData.setValue(pkInfo);*/
                    }else{
                        Log.e("Info", String.valueOf(response.code()));
                        return;
                    }
                }

                @Override
                public void onFailure(Call<PokemonInfo> call, Throwable t) {
                    Log.e("Info", " Error: " + t.getMessage());
                }
            });






        }

}
