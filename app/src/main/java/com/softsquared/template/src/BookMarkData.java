package com.softsquared.template.src;

import android.widget.TextView;

import com.softsquared.template.src.main.adapter.InPageViewPagerAdapter;
import com.softsquared.template.src.main.models.RegionResponse;

public class BookMarkData {
    boolean editFlag = false;
    boolean deleteFlag = false;
    public RegionResponse.result regionResponse_result;
    public InPageViewPagerAdapter mInPagePagerAdapter;


    public BookMarkData(){
        regionResponse_result = new RegionResponse.result();
    }

    public BookMarkData(String location_name) {
        this.location_name = location_name;
        regionResponse_result = new RegionResponse.result();
    }

    public RegionResponse.result getRegionResponse_result() {
        return regionResponse_result;
    }

    public void setRegionResponse_result(RegionResponse.result regionResponse_result) {
        this.regionResponse_result = regionResponse_result;
    }

    String location_name, update_time, mise_status, super_mise_status, no2_status, o3_status, no1_status, so2_status;
    String time_status1, time_status2, time_status3, time_status4, time_status5, time_status6,
            time_status7, time_status8, time_status9, time_status10, time_status11, time_status12;

    String day_status1_morning, day_status1_lunch, day_status1_dinner;
    String day_status2_morning, day_status2_lunch, day_status2_dinner;
    String day_status3_morning;

    public TextView tv_pm10_mang_name, tv_pm25_mang_name, tv_pm10_station, tv_pm25_station, tv_no2_station,
            tv_o3_station, tv_co_station, tv_so2_station, tv_update_time;

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

    public String getNo2_status() {
        return no2_status;
    }

    public void setNo2_status(String no2_status) {
        this.no2_status = no2_status;
    }

    public String getO3_status() {
        return o3_status;
    }

    public void setO3_status(String o3_status) {
        this.o3_status = o3_status;
    }

    public String getNo1_status() {
        return no1_status;
    }

    public void setNo1_status(String no1_status) {
        this.no1_status = no1_status;
    }

    public String getSo2_status() {
        return so2_status;
    }

    public void setSo2_status(String so2_status) {
        this.so2_status = so2_status;
    }

    public String getTime_status1() {
        return time_status1;
    }

    public void setTime_status1(String time_status1) {
        this.time_status1 = time_status1;
    }

    public String getTime_status2() {
        return time_status2;
    }

    public void setTime_status2(String time_status2) {
        this.time_status2 = time_status2;
    }

    public String getTime_status3() {
        return time_status3;
    }

    public void setTime_status3(String time_status3) {
        this.time_status3 = time_status3;
    }

    public String getTime_status4() {
        return time_status4;
    }

    public void setTime_status4(String time_status4) {
        this.time_status4 = time_status4;
    }

    public String getTime_status5() {
        return time_status5;
    }

    public void setTime_status5(String time_status5) {
        this.time_status5 = time_status5;
    }

    public String getTime_status6() {
        return time_status6;
    }

    public void setTime_status6(String time_status6) {
        this.time_status6 = time_status6;
    }

    public String getTime_status7() {
        return time_status7;
    }

    public void setTime_status7(String time_status7) {
        this.time_status7 = time_status7;
    }

    public String getTime_status8() {
        return time_status8;
    }

    public void setTime_status8(String time_status8) {
        this.time_status8 = time_status8;
    }

    public String getTime_status9() {
        return time_status9;
    }

    public void setTime_status9(String time_status9) {
        this.time_status9 = time_status9;
    }

    public String getTime_status10() {
        return time_status10;
    }

    public void setTime_status10(String time_status10) {
        this.time_status10 = time_status10;
    }

    public String getTime_status11() {
        return time_status11;
    }

    public void setTime_status11(String time_status11) {
        this.time_status11 = time_status11;
    }

    public String getTime_status12() {
        return time_status12;
    }

    public void setTime_status12(String time_status12) {
        this.time_status12 = time_status12;
    }

    public String getDay_status1_morning() {
        return day_status1_morning;
    }

    public void setDay_status1_morning(String day_status1_morning) {
        this.day_status1_morning = day_status1_morning;
    }

    public String getDay_status1_lunch() {
        return day_status1_lunch;
    }

    public void setDay_status1_lunch(String day_status1_lunch) {
        this.day_status1_lunch = day_status1_lunch;
    }

    public String getDay_status1_dinner() {
        return day_status1_dinner;
    }

    public void setDay_status1_dinner(String day_status1_dinner) {
        this.day_status1_dinner = day_status1_dinner;
    }

    public String getDay_status2_morning() {
        return day_status2_morning;
    }

    public void setDay_status2_morning(String day_status2_morning) {
        this.day_status2_morning = day_status2_morning;
    }

    public String getDay_status2_lunch() {
        return day_status2_lunch;
    }

    public void setDay_status2_lunch(String day_status2_lunch) {
        this.day_status2_lunch = day_status2_lunch;
    }

    public String getDay_status2_dinner() {
        return day_status2_dinner;
    }

    public void setDay_status2_dinner(String day_status2_dinner) {
        this.day_status2_dinner = day_status2_dinner;
    }

    public String getDay_status3_morning() {
        return day_status3_morning;
    }

    public void setDay_status3_morning(String day_status3_morning) {
        this.day_status3_morning = day_status3_morning;
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
