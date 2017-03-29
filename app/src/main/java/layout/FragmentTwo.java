package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swat_john.myfirstapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class FragmentTwo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.dewslandslide.com/monitoring/getOnGoingAndExtended/", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (responseBody != null) {
                    String response = new String(responseBody);
                    Log.d("JSON: ", response);
                } else {

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("ERROR: ", String.valueOf(error));
                Log.d("Response: ", String.valueOf(responseBody));
            }

        });
        return inflater.inflate(R.layout.fragment_fragment_two, container, false);
    }
}
