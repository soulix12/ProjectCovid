package com.example.covid2019;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.covid2019.fragment.HomeFragment;

import ai.api.AIServiceContext;
import ai.api.AIServiceException;
import ai.api.android.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class RequestTask extends AsyncTask<AIRequest, Void, AIResponse> {

    Fragment activity;
    private AIDataService aiDataService;
    private AIServiceContext customAIServiceContext;

    public RequestTask(Fragment activity, AIDataService aiDataService, AIServiceContext customAIServiceContext){
        this.activity = activity;
        this.aiDataService = aiDataService;
        this.customAIServiceContext = customAIServiceContext;
    }

    @Override
    protected AIResponse doInBackground(AIRequest... aiRequests) {
        final AIRequest request = aiRequests[0];
        try {
            return aiDataService.request(request, customAIServiceContext);
        } catch (AIServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(AIResponse aiResponse) {
        ((HomeFragment)activity).callback(aiResponse);
    }
}
