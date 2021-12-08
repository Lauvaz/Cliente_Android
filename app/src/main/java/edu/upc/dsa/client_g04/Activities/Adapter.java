package edu.upc.dsa.client_g04.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.client_g04.Models.Object;
import edu.upc.dsa.client_g04.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Object> objects;
    Context context;

    public Adapter(Context context, List<Object> objectList) {
        objects = objectList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            image = (ImageView) v.findViewById(R.id.icon);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.object, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object object = objects.get(position);
        holder.txtHeader.setText(object.getName());
        holder.txtFooter.setText(object.getDescription());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
}
