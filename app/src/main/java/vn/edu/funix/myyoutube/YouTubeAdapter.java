package vn.edu.funix.myyoutube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Duy on 05/31/17.
 */

public class YouTubeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<VideoYoutube> list;

    public YouTubeAdapter(Context context, int layout, List<VideoYoutube> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        try{
            return list.size();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgThumbnail;
        TextView txtTitle;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view==null){
            holder =new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            holder.txtTitle= (TextView) view.findViewById(R.id.txtTitle);
            holder.imgThumbnail= (ImageView) view.findViewById(R.id.imgThumbnail);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        VideoYoutube videoYoutube=list.get(position);
        holder.txtTitle.setText(videoYoutube.getTitle());
        Picasso.with(context).load(videoYoutube.getThumbnail()).into(holder.imgThumbnail);

        return view;
    }
}
