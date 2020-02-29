package com.softsquared.template.src;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.softsquared.template.src.main.adapter.InPageViewPagerAdapter;
import com.softsquared.template.src.main.adapter.RecyclerPreDayAdapter;
import com.softsquared.template.src.main.adapter.RecyclerPreTimeAdapter;
import com.softsquared.template.src.main.models.RegionResponse;

public class BookMarkData {
    boolean editFlag = false;
    public boolean deleteFlag = false;
    public boolean noticeDialogFlag = true;
    public RegionResponse.result regionResponse_result;
    public InPageViewPagerAdapter mInPagePagerAdapter;
    public RecyclerPreTimeAdapter mRecyclerPreTimeAdapter;
    public RecyclerPreDayAdapter mRecyclerPreDayAdapter;
    public Double tm_x, tm_y;

    public BookMarkData(){
        regionResponse_result = new RegionResponse.result();
    }

    public BookMarkData(String location_name) {
        this.location_name = location_name;
        regionResponse_result = new RegionResponse.result();
    }
    public BookMarkData(String location_name, Double tm_x, Double tm_y) {
        this.location_name = location_name;
        this.tm_x = tm_x;
        this.tm_y = tm_y;
        regionResponse_result = new RegionResponse.result();
    }

    public RegionResponse.result getRegionResponse_result() {
        return regionResponse_result;
    }

    public void setRegionResponse_result(RegionResponse.result regionResponse_result) {
        this.regionResponse_result = regionResponse_result;
    }



    String location_name, update_time, mise_status, super_mise_status;

    // 뷰 내부 색 지정을 위한 컴포넌트
    public View v_main;
    public CardView card_day_pre, card_time_pre, card_ad, card_details, card_searcher_info, card_six_status;


    // 뷰 내부 컨텐츠 지정을 위한 컴포넌트
    public TextView tv_current_location_flag, tv_api_update_time, tv_mise_status, tv_mise_status_details, tv_location_name;
    public ImageView iv_statusImage;
    public TextView tv_pm10_mang_name, tv_pm25_mang_name, tv_pm10_station, tv_pm25_station, tv_no2_station,
            tv_o3_station, tv_co_station, tv_so2_station, tv_update_time, tv_details_total_value, tv_details_total_status;

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public boolean isEditFlag() {
        return editFlag;
    }

    public void setEditFlag(boolean editFlag) {
        this.editFlag = editFlag;
    }

    String day_status3_lunch;
    String day_status3_dinner;


    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getMise_status() {
        return mise_status;
    }

    public void setMise_status(String mise_status) {
        this.mise_status = mise_status;
    }

    public String getSuper_mise_status() {
        return super_mise_status;
    }

    public void setSuper_mise_status(String super_mise_status) {
        this.super_mise_status = super_mise_status;
    }

    public String getDay_status3_lunch() {
        return day_status3_lunch;
    }

    public void setDay_status3_lunch(String day_status3_lunch) {
        this.day_status3_lunch = day_status3_lunch;
    }

    public String getDay_status3_dinner() {
        return day_status3_dinner;
    }

    public void setDay_status3_dinner(String day_status3_dinner) {
        this.day_status3_dinner = day_status3_dinner;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

}
