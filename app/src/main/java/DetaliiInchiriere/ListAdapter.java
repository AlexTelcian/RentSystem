package DetaliiInchiriere;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.rentsystem.R;

import java.util.ArrayList;

import DetaliiInchiriere.NouVehiculInchiriat;

public class ListAdapter extends ArrayAdapter<NouVehiculInchiriat> {
    public ListAdapter(Context context, ArrayList<NouVehiculInchiriat> listVehiculInchiriat ){
        super(context, R.layout.list_item,listVehiculInchiriat);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NouVehiculInchiriat detaliiVehiculInchiriat = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        ImageView imgView = convertView.findViewById(R.id.vehiculIMG);
        TextView numeVehicul = convertView.findViewById(R.id.numeVehicul);
        TextView anFabVehicul = convertView.findViewById(R.id.anVehicul);

        imgView.setImageDrawable(detaliiVehiculInchiriat.imgDrawable);
        numeVehicul.setText(detaliiVehiculInchiriat.brand);
        anFabVehicul.setText(detaliiVehiculInchiriat.anFab);


        return convertView;
    }
}
