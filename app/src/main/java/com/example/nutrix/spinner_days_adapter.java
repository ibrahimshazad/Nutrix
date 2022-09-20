package com.example.nutrix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;

public class spinner_days_adapter extends ArrayAdapter<String> {
    private String dayarray[];
    private Context context;
    public spinner_days_adapter(@NonNull Context context, int resource, String[] dayarray) {
        super(context, resource,dayarray);
        this.context = context;
        this.dayarray=dayarray;
    }

    @Override
    public View getDropDownView(final int position, View convertView,
                                ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.days_layout, null);
        }
        final CheckBox label = (CheckBox) convertView.findViewById(R.id.checkbox);//fix
        label.setText(dayarray[position]);

        label.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    NewMealPlanActivity.daysarraylist.add(dayarray[position]);
                }else {
                  NewMealPlanActivity.daysarraylist.remove(dayarray[position]);
                }
            }
        });
        return convertView;
    }
}
