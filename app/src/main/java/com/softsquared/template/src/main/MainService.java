package com.softsquared.template.src.main;

import android.util.Log;

import com.softsquared.template.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.template.src.main.models.DayForecastResponse;
import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.GradeResponse;
import com.softsquared.template.src.main.models.HourForecastResponse;
import com.softsquared.template.src.main.models.NoticeResponse;
import com.softsquared.template.src.main.models.RegionResponse;
import com.softsquared.template.src.main.interfaces.MainActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class MainService {
    private final MainActivityView mMainActivityView;


    public MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    public void getRegion(final String region, final int idx) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getRegion(region).enqueue(new Callback<RegionResponse>() {
            @Override
            public void onResponse(Call<RegionResponse> call, Response<RegionResponse> response) {
                final RegionResponse regionResponse = response.body();
                if (regionResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                Log.e("onResponse", "응답");
//                mMainActivityView.getRegionCode(regionResponse.getCode());
                if (regionResponse.getCode() == 100)
                    mMainActivityView.getRegionResult(regionResponse.getResult(), region, idx);
            }

            @Override
            public void onFailure(Call<RegionResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
                Log.e("onFailure", t.getLocalizedMessage());
            }
        });
    }

    public void getEtc(final String region, final int idx) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getEtc(region).enqueue(new Callback<EtcResponse>() {
            @Override
            public void onResponse(Call<EtcResponse> call, Response<EtcResponse> response) {
                final EtcResponse etcResponse = response.body();
                if (etcResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.e("ectOnResponse", "응답");
                if (etcResponse.getCode() == 100)
                    mMainActivityView.getEtcResult(etcResponse.getEtcResult(), region, idx);
            }

            @Override
            public void onFailure(Call<EtcResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
                Log.e("etcOnFailure", t.getLocalizedMessage());
            }
        });
    }
    public void getEtc2(double x, double y, final int idx) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getEtc2(x, y).enqueue(new Callback<EtcResponse>() {
            @Override
            public void onResponse(Call<EtcResponse> call, Response<EtcResponse> response) {
                final EtcResponse etcResponse = response.body();
                if (etcResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.e("ectOnResponse2", etcResponse.getCode() + "");
                if (etcResponse.getCode() == 100) {
                    mMainActivityView.getEtcResult2(etcResponse.getEtcResult(), idx);
                    getDayForecast(0);
                    getHourForecast(0);


                    getRegion(etcResponse.getEtcResult().getRegion_2depth_name() + " " + etcResponse.getEtcResult().getRegion_3depth_name(), 0);
                    getGrade(etcResponse.getEtcResult().getRegion_2depth_name() + " " + etcResponse.getEtcResult().getRegion_3depth_name(), 0);

                    Log.e("ectOnResponse2", etcResponse.getEtcResult().getRegion_2depth_name());
                }

            }

            @Override
            public void onFailure(Call<EtcResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
                Log.e("etcOnFailure2", t.getLocalizedMessage());
            }
        });
    }


    public void getGrade(final String region, final int pos) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getGrade(region).enqueue(new Callback<GradeResponse>() {


            @Override
            public void onResponse(Call<GradeResponse> call, Response<GradeResponse> response) {
                final GradeResponse gradeResponse = response.body();
                if (gradeResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.e("gradeOnResponse", "응답");
                Log.e("gradeCode", gradeResponse.getCode() + "");
                if (gradeResponse.getCode() == 100)
                    mMainActivityView.getGradeResult(gradeResponse.getGradeResult(), region, pos);
            }

            @Override
            public void onFailure(Call<GradeResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
                Log.e("gradeOnFailure", t.getLocalizedMessage());
            }
        });
    }

    public void getNotice() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getNotice().enqueue(new Callback<NoticeResponse>() {

            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                final NoticeResponse noticeResponse = response.body();
                if (noticeResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.e("NoticeOnResponse", "응답");
//                Log.e("NoticeCode", noticeResponse.getCode() + "");
                if (noticeResponse.getCode() == 100)
                    mMainActivityView.getNoticeResult(noticeResponse.getNoticeResult());
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                Log.e("noticeOnFailure", t.getLocalizedMessage());
            }
        });
    }

    public void getHourForecast(final int pos) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getHourForecast().enqueue(new Callback<HourForecastResponse>() {
            @Override
            public void onResponse(Call<HourForecastResponse> call, Response<HourForecastResponse> response) {
                final HourForecastResponse hourForecastResponse = response.body();
                if (hourForecastResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.e("HourForecastOnResponse", "응답");
                if (hourForecastResponse.getCode() == 100) {
                    mMainActivityView.getHourForecastResult(hourForecastResponse.getResult(), pos);
                }
            }

            @Override
            public void onFailure(Call<HourForecastResponse> call, Throwable t) {
                Log.e("hourForecastOnFailure", t.getLocalizedMessage());
            }
        });
    }
    public void getDayForecast(final int pos) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getDayForecast().enqueue(new Callback<DayForecastResponse>() {
            @Override
            public void onResponse(Call<DayForecastResponse> call, Response<DayForecastResponse> response) {
                final DayForecastResponse dayForecastResponse = response.body();
                if (dayForecastResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.e("DayForecastOnResponse", "응답");
                if (dayForecastResponse.getCode() == 100) {
                    mMainActivityView.getDayForecastResult(dayForecastResponse.getResult(), pos);
                }
            }
            @Override
            public void onFailure(Call<DayForecastResponse> call, Throwable t) {
                Log.e("dayForecastOnFailure", t.getLocalizedMessage());
            }
        });
    }

}
