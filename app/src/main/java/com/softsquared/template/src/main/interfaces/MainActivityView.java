package com.softsquared.template.src.main.interfaces;

import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.RegionResponse;
import com.softsquared.template.src.main.models.EtcResponse;

import java.util.ArrayList;

public interface MainActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getRegionCode(int code);

    void getRegionResult(RegionResponse.result result, String name, int idx);

    void getEtcCode(int code);

    void getEtcResult(EtcResponse.etcResult etcResult, String name, int idx);
}
