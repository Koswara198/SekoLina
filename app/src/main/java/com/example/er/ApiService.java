package com.example.er;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("sekolah_by_kode_kecamatan?kode_wilayah_kecamatan_id=020511")
    Call<List<School>> getSekolahByKodeKecamatan(
            @Header("X-RapidAPI-Key") String apiKey,
            @Query("kode_wilayah_kecamatan_id") String kodeKecamatan
    );

    @GET("sekolah_by_kode_kecamatan?kode_wilayah_kecamatan_id=020510")
    Call<List<School>> getBentukPendidikanByNama(
            @Header("X-RapidAPI-Key") String apiKey,
            @Query("bentuk_pendidikan") String bentukPendidikan,
            @Query("nama") String nama

    );
}
