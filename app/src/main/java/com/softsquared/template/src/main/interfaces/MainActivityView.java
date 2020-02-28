package com.softsquared.template.src.main.interfaces;

import com.softsquared.template.src.main.models.DayForecastResponse;
import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.GradeResponse;
import com.softsquared.template.src.main.models.HourForecastResponse;
import com.softsquared.template.src.main.models.NoticeResponse;
import com.softsquared.template.src.main.models.RegionResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface MainActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getRegionCode(int code);

    void getRegionResult(RegionResponse.result result, String name, int pos);

    void getEtcCode(int code);

    void getEtcResult(EtcResponse.etcResult etcResult, String name, int pos);

    void getEtcResult2(EtcResponse.etcResult etcResult, int pos);

    void getGradeResult(GradeResponse.gradeResult gradeResult, String name, int pos);

    void getNoticeResult(NoticeResponse.noticeResult result);

    void getHourForecastResult(ArrayList<HourForecastResponse.ForecastResult> result, int pos);

    void getDayForecastResult(ArrayList<DayForecastResponse.ForecastResult> result, int pos);
}
