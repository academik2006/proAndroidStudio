package com.filmsdata.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.filmsdata.HeadersClass;
import com.filmsdata.MainActivity;
import com.filmsdata.R;

public class HeadersAdapter extends RecyclerView.Adapter<HeadersAdapter.HeaderViewHolder> {

    String header;
    private final LayoutInflater inflater;
    private final MainActivity.TypeOfViewHolder typeOfViewHolder;

        public HeadersAdapter(Context context, String header, MainActivity.TypeOfViewHolder typeOfViewHolder){
        this.header = header;
        this.inflater = LayoutInflater.from(context);
        this.typeOfViewHolder = typeOfViewHolder;
        }


    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.header_layout,parent,false);
        return new HeaderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {

        HeadersClass header_of_title = new HeadersClass(header, typeOfViewHolder);
        holder.changeHolder(header_of_title.getTitle());
        Log.d ("MOY", "меняем тест заголовка разделов");

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView header_text;

        public HeaderViewHolder (@NonNull View itemView) {
            super(itemView);
            header_text = itemView.findViewById(R.id.textViewHeaders);
        }

        void changeHolder (String header) {
            header_text.setText(header);
        }
    }



}
