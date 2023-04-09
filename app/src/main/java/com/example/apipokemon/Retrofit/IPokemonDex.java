package com.example.apipokemon.Retrofit;

import android.database.Observable;

import com.example.apipokemon.Model.Pokedex;

import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
