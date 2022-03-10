package com.example.mediastreamplatformsdkandroidsamples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String TAG = "SampleApp";

    ListView listView;
    ArrayList<ItemList> configList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Log.d(TAG, "OnCreate");
        setContentView(R.layout.activity_main);

        String json;
        try {
            InputStream is = getAssets().open("media.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                ItemList itemList = new ItemList();
                itemList.setImage(R.drawable.test_draw);
                if (object.has("title")) {
                    itemList.setTitle(object.getString("title"));
                }
                if (object.has("description")) {
                    itemList.setDescription(object.getString("description"));
                }
                if (object.has("id")) {
                    itemList.setId(object.getString("id"));
                }
                if (object.has("account")) {
                    itemList.setAccountID(object.getString("account"));
                }
                if (object.has("isLive")) {
                    itemList.setLive(object.getBoolean("isLive"));
                } else {
                    itemList.setLive(false);
                }
                if (object.has("isLocal")) {
                    itemList.setLocal(object.getBoolean("isLocal"));
                } else {
                    itemList.setLocal(false);
                }

                if (object.has("isDvr")) {
                    itemList.setDVR(object.getBoolean("isDvr"));
                } else {
                    itemList.setDVR(false);
                }

                if (object.has("isService")) {
                    itemList.setService(object.getBoolean("isService"));
                } else {
                    itemList.setService(false);
                }

                if (object.has("type")) {
                    itemList.setType(object.getString("type"));
                    if (object.getString("type").equals("video")) {
                        itemList.setImage(R.drawable.ic_baseline_ondemand_video_24);
                    } else {
                        itemList.setImage(R.drawable.ic_baseline_audiotrack_24);
                    }
                } else {
                    itemList.setType("video");
                    itemList.setImage(R.drawable.ic_baseline_ondemand_video_24);
                }

                configList.add(itemList);
            }

            listView = findViewById(R.id.listView);
            ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.row_list_view, configList);
            listView.setAdapter(itemAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ItemList item = (ItemList) parent.getItemAtPosition(position);
                    Intent intent = new Intent(MainActivity.this, Player.class);
                    if (item.getId() != null) {
                        intent.putExtra("id", item.getId());
                    }
                    if (item.getAccountID() != null) {
                        intent.putExtra("accountID", item.getAccountID());
                    }
                    if (item.getLive() != null) {
                        intent.putExtra("isLive", item.getLive());
                    }
                    if (item.getType() != null) {
                        intent.putExtra("type", item.getType());
                    }
                    if (item.getService() != null) {
                        intent.putExtra("isService", item.getService());
                    } else {
                        intent.putExtra("isService", false);
                    }
                    if (item.getDVR() != null) {
                        intent.putExtra("isDvr", item.getDVR());
                    } else {
                        intent.putExtra("isDvr", false);
                    }
                    if (item.getLocal() != null) {
                        intent.putExtra("isLocal", item.getLocal());
                    } else {
                        intent.putExtra("isLocal", false);
                    }

                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
