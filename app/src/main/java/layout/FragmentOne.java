package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swat_john.myfirstapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
                    String response = new String(responseBody);
//                    TextView tv = (TextView) getView().findViewById(R.id.jsonres);
//                    tv.setText(response.toString());

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
                        Log.d("INVALIDS:", String.valueOf(arr_invalids.length()));
                        Log.d("ALERTS:", String.valueOf(arr_alerts.length()));

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
