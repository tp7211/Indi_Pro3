package com.example.indi_pro3;

import android.os.AsyncTask;

public class DoAsyncDBCall extends AsyncTask<Void, Void, Integer> {

    DoInBackGroundCaller doInBackGroundCaller;
    public DoAsyncDBCall(DoInBackGroundCaller doInBackGroundCaller)
    {
            this.doInBackGroundCaller = doInBackGroundCaller;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        doInBackGroundCaller.doTask();

        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        doInBackGroundCaller.doTaskComplete();
    }
}
