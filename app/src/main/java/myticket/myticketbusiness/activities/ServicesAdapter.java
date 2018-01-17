package myticket.myticketbusiness.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import myticket.myticketbusiness.R;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesHolder> {

    private List<Service> serviceList;

    public class ServicesHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView actualTicket;
        TextView ticketsLine;

        public ServicesHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.service_name);
            actualTicket = itemView.findViewById(R.id.actual_ticket);
            ticketsLine = itemView.findViewById(R.id.ticket_line);
        }

    }

    public ServicesAdapter(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public ServicesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServicesHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.services_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ServicesHolder holder, int position) {
        Service service = serviceList.get(position);

        holder.title.setText(service.getService());
        holder.actualTicket.setText(String.valueOf(service.getActualTicket()));
        holder.ticketsLine.setText(String.valueOf(service.getUsersInLine().size() - 1));
    }

    @Override
    public int getItemCount() {
        return serviceList != null ? serviceList.size() : 0;
    }
}
