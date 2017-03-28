package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.swat_john.myfirstapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.dewslandslide.com/temp/data/PublicAlert.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (responseBody != null){
                    ArrayList<String> listItems=new ArrayList<String>();
                    ArrayList<String> listItemsValid=new ArrayList<String>();
                    ArrayList<String> listRoutine=new ArrayList<String>();
                    ArrayAdapter<String> adapter;
                    String response = new String(responseBody);
                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        String invalids = null;
                        String alerts = null;

                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            invalids = jsonobject.getString("invalids");
                            alerts = jsonobject.getString("alerts");
                        }

                        JSONArray arr_invalids = new JSONArray(invalids);
                        JSONArray arr_alerts = new JSONArray(alerts);
                        TextView tv = (TextView) getView().findViewById(R.id.jsonres);
                        ListView list = (ListView) getView().findViewById(R.id.invalid_alerts);
                        ListView list_valid = (ListView) getView().findViewById(R.id.valid_alerts);
                        tv.setText("Invalid Alerts: "+String.valueOf(arr_invalids.length()));

                        for (int counter_invalid = 0; counter_invalid < arr_invalids.length();counter_invalid++){
                            JSONObject jsonobject = arr_invalids.getJSONObject(counter_invalid);
                            String invalid_alert = jsonobject.getString("alert");
                            String invalid_site = jsonobject.getString("site");
                            String invalid_timestamp = jsonobject.getString("timestamp");

                            Log.d("INVALIDS: ",invalid_alert+" , "+invalid_site);
                            String constructed = invalid_alert+" "+invalid_site;
                            listItems.add(constructed);
                        }

                        Log.d("INVALIDS: ", String.valueOf(listItems));
                        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listItems);
                        list.setAdapter(adapter);

                        for (int counter_valid = 0; counter_valid < arr_alerts.length();counter_valid++) {
                            JSONObject jsonObject = arr_alerts.getJSONObject(counter_valid);
                            String constructed_alerts = jsonObject.getString("alert")+" "+jsonObject.getString("site");
                            if (!listItems.contains(constructed_alerts) && !jsonObject.get("alert").equals("A0")) {
                                Log.d("VALIDS: ",constructed_alerts);
                                listItemsValid.add(constructed_alerts);
                            } else {
                                listRoutine.add(constructed_alerts);
                            }
                        }
                        Log.d("VALIDS: ", String.valueOf(listItemsValid));
                        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listItemsValid);
                        list_valid.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

}
