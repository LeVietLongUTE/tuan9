package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    listviewbaseAdapter adapter;
    ArrayList<listviewbean> arr_bean;
    Vector<Integer> index=new Vector<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lê Việt Long");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Listview
        lv = (ListView) findViewById(R.id.listview);
        arr_bean = new ArrayList<listviewbean>();


        arr_bean = new ArrayList<listviewbean>();
        arr_bean.add(new listviewbean(R.drawable.sinhto, "Lê Việt Long", "2$"));
        arr_bean.add(new listviewbean(R.drawable.cafesua, "Cà Phê Sữa", "3$"));
        arr_bean.add(new listviewbean(R.drawable.trasua, "Trà Sữa", "25000đ"));
        arr_bean.add(new listviewbean(R.drawable.tradao, "Trà Đào", "32000đ"));
        arr_bean.add(new listviewbean(R.drawable.trasuakduong, "Trà Sữa không đường", "2000đ"));
        arr_bean.add(new listviewbean(R.drawable.nuoccarot, "Nước Cà Rốt", "12000đ"));
        arr_bean.add(new listviewbean(R.drawable.trachanh, "Trà Chanh", "22000đ"));
        arr_bean.add(new listviewbean(R.drawable.chanchauduongden, "Chân Châu Đường Đen", "32000đ"));
        adapter = new listviewbaseAdapter(arr_bean, this);
        lv.setAdapter(adapter);


        registerForContextMenu(lv);
        index.add(-1);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                try {
                    //chuẩn bị danh sách các phần tử được chọn
                    if (index.get(0) == -1)
                        index.set(0, position);
                    else {
                        int yn = 0, vt = -1;
                        for (int i = 0; i < index.size(); ++i) {
                            if (index.get(i) == position) {
                                yn = 1;
                                vt = i;
                                break;
                            }
                        }
                        //nếu phần tử đã chọn trc đó thì xóa khỏi danh sách
                        if (yn == 1) index.remove(vt);
                        else index.add(position);
                    }
                } catch (Exception e) {
                    index.add(position);
                }

                //thiết lập lại màu cho toàn bộ
                for (int i = 0; i < lv.getChildCount(); i++) {
                    lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                //thiết lập màu cho các phần tử đc chọn
                for (int i = 0; i < lv.getChildCount(); i++) {
                    for (int j = 0; j < index.size(); ++j)
                        if (index.get(j) == i) {
                            lv.getChildAt(i).setBackgroundColor(Color.YELLOW);
                        }
                }
                mode.setTitle(index.size() + " đã chọn");

            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case android.R.id.home:
                        index.clear();
                        //thiết lập lại màu cho toàn bộ
                        for (int i = 0; i < lv.getChildCount(); i++) {
                            lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                        return true;
                    case R.id.action_insert:
                        Toast.makeText(MainActivity.this, "Thêm", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_update:
                        Toast.makeText(MainActivity.this, "Sửa", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_delete:
                        try {
                            Collections.sort(index);
                            for (int i = index.size() - 1; i >= 0; --i) {
                                arr_bean.remove(Integer.parseInt(index.get(i).toString()));
                                adapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Bạn chưa chọn dòng nào cả", Toast.LENGTH_SHORT).show();
                        }

                        index.clear();
                        //thiết lập lại màu cho toàn bộ
                        for (int i = 0; i < lv.getChildCount(); i++) {
                            lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                        Log.d("Xoa mang", Integer.toString(index.size()));
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });
    }
}
