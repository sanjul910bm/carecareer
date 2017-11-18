package au.com.carecareers.android.loginModule.register.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import au.com.carecareers.android.R;

/**
 * Created by Sanjay on 03/04/2017.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflate;
    private int resourceId;
    private ArrayList<String> spinnerList;
    private Context context;
    private int spinnerid;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<String> spinnerList, int spinnerId) {
        super(context, resource, spinnerList);
        this.context = context;
        this.inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceId = resource;
        this.spinnerList = spinnerList;
        this.spinnerid = spinnerId;
    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        Typeface externalFont = Typeface.createFromAsset(context.getAssets(), "fonts/sofiapro-light.otf");
        if (convertView == null) {
            convertView = inflate.inflate(resourceId, null);
            Holder holder = new Holder();

            holder.textView1 = convertView.findViewById(R.id.tv_default_first);
            holder.textView1.setText(spinnerList.get(position));
            holder.textView1.setTextSize(18);
//            holder.textView1.setTypeface(externalFont);
            holder.textView1.setHintTextColor(Color.BLACK);
            holder.textView1.setTextColor(Color.BLACK);
            convertView.setTag(holder);
        } else {
            convertView = inflate.inflate(resourceId, null);
            Holder holder = new Holder();

            holder.textView1 = convertView.findViewById(R.id.tv_default_first);
            holder.textView1.setText(spinnerList.get(position));
            holder.textView1.setTextSize(18);
//            holder.textView1.setTypeface(externalFont);
            holder.textView1.setHintTextColor(Color.BLACK);
            holder.textView1.setTextColor(Color.BLACK);
        }

        return convertView;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        Typeface externalFont = Typeface.createFromAsset(context.getAssets(), "fonts/sofiapro-light.otf");
        if (convertView == null) {
            convertView = inflate.inflate(resourceId, null);
            Holder holder = new Holder();

            holder.textView1 = convertView.findViewById(R.id.tv_default_first);
            holder.textView1.setText(spinnerList.get(position));
//            holder.textView1.setTypeface(externalFont);
            holder.textView1.setHintTextColor(Color.BLACK);
            holder.textView1.setTextColor(Color.BLACK);
            holder.textView1.setTextSize(18);
            convertView.setTag(holder);
        } else {
            convertView = inflate.inflate(resourceId, null);
            Holder holder = new Holder();
            holder.textView1 = convertView.findViewById(R.id.tv_default_first);
            holder.textView1.setText(spinnerList.get(position));
//            holder.textView1.setTypeface(externalFont);
            holder.textView1.setTextSize(18);
            holder.textView1.setHintTextColor(Color.BLACK);
            holder.textView1.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    private class Holder {
        TextView textView1;
    }
}