package com.example.lyavon.tourism;

import android.app.Application;
import com.example.lyavon.tourism.Models.DirectionModel;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 13.09.2017.
 */

 class ParseProvider   extends Application {

    ArrayList<String> startLocLat = new ArrayList<String>();

    public ParseProvider() {
        Retrofit2App.getApi().getData("Брест", "Берёза", "optimize:true|Кобрин|Жабинка", "driving")
                .enqueue(new Callback<DirectionModel>() {

                    @Override
                    public void onResponse(Call<DirectionModel> call, Response<DirectionModel> response) {

                        for (int rou = 0; rou < response.body().getRoutes().size(); rou++) {
                            for (int leg = 0; leg < response.body().getRoutes().get(rou).getLegs().size() - 1; leg++) {
                                for (int step = 0; step < response.body().getRoutes().get(rou).getLegs().get(leg).getSteps().size() - 1; step++) {

                                    startLocLat.add(response.body().getRoutes().get(rou).getLegs().get(leg).getSteps()
                                            .get(step).getStartLocation().getLat().toString());

                                }
                            }
                        }

                    }


                    @Override
                    public void onFailure(Call<DirectionModel> call, Throwable t) {

                    }

                });






    }

    public ArrayList<String> getStartLocLat() {
        return startLocLat;
    }
}



/*Retrofit2App.getApi().getData("Брест", "Берёза", "optimize:true|Кобрин|Жабинка", "driving")
        .enqueue(new Callback<DirectionModel>() {

@Override
public void onResponse(Call<DirectionModel> call, Response<DirectionModel> response) {


        for (int rou = 0; rou < response.body().getRoutes().size(); rou++) {
        for (int leg = 0; leg < response.body().getRoutes().get(rou).getLegs().size() - 1; leg++) {

        for (int step = 0; step < response.body().getRoutes().get(rou).getLegs().get(leg).getSteps().size() - 1; step++) {

        startLocLat.add(response.body().getRoutes().get(rou).getLegs().get(leg).getSteps()
        .get(step).getStartLocation().getLat().toString());

        }
        }
        }
        }


@Override
public void onFailure(Call<DirectionModel> call, Throwable t) {

        }

        });

        return  startLocLat;
*/