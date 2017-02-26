package davidurbina.disasterrelief;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by davidurbina on 25/02/17.
 */


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final Context mContext;
    public String cardType;
    public Integer currentPosition = 0;
    Boolean animate;
    JSONArray shelters;

    public CardAdapter(Context context, JSONArray shelters, String cardType, Boolean Animate){
        super();
        mContext = context;
        this.cardType = cardType;
        this.shelters = shelters;
        animate = Animate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v,cardType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        JSONObject obj = new JSONObject();

        if (cardType =="Shelter" ) {
            try {
                obj = shelters.getJSONObject(i);
                Log.i("Object",obj.toString());
                viewHolder.title.setText(obj.getString("name"));
                viewHolder.location.setText("Address: "+obj.getString("street") + "," + obj.getString("city") + "," + obj.getString("state") + "," + obj.getString("zip"));
                viewHolder.capacity.setText("Capacity: "+obj.getString("capacity"));
                viewHolder.vname.setText("Point of Contact: "+obj.getString("vname"));
                viewHolder.phonenumber.setText("Phone: "+obj.getString("phonenumber"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(cardType == "Necessities" || cardType == "General"){
            try {
                obj = shelters.getJSONObject(i);
                Log.i("Object",obj.toString());
                viewHolder.title.setText(obj.getString("name"));
                viewHolder.location.setText("Address:" + obj.getString("city") + "," + obj.getString("state"));
                viewHolder.capacity.setText("Details: "+obj.getString("details"));
                viewHolder.vname.setVisibility(View.GONE);
                viewHolder.phonenumber.setText("Phone: "+obj.getString("phonenumber"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if (i > currentPosition){
            animate(viewHolder,true);
        } else {
            animate(viewHolder,false);
        }
        currentPosition = i;
    }

    public void animate (RecyclerView.ViewHolder holder, Boolean goesDown) {
        if(animate == true) {
            //if (cardType == "Featured" || cardType == "Blog") {
                YoYo.with(Techniques.ZoomIn)
                        .duration(400)
                        .playOn(holder.itemView);

        }
    }

    @Override
    public int getItemCount() {
        return shelters.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView vname;
        TextView phonenumber;
        TextView capacity;
        TextView location;

        public ViewHolder(View itemView, String cardTypeCheck) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            vname =(TextView) itemView.findViewById(R.id.vname);
            phonenumber = (TextView) itemView.findViewById(R.id.phonenumber);
            capacity = (TextView) itemView.findViewById(R.id.capacity);
            location = (TextView) itemView.findViewById(R.id.location);
        }

    }
}
