package vn.edu.tdtu.lab04.exercise04;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import vn.edu.tdtu.lab04.R;

public class ComputerArrayAdapter extends ArrayAdapter<Computer> {

  private Context context;
  private int layoutToBeInflated;
  private List<Computer> computers;

  public ComputerArrayAdapter(@NonNull Context context,
      int resource,
      @NonNull List<Computer> computers) {
    super(context, resource, computers);
    this.computers = computers;
    this.layoutToBeInflated = resource;
    this.context = context;
  }

  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ComputerViewHolder holder;
    View row = convertView;

    if (row == null) {
      LayoutInflater inflater = ((Activity) context).getLayoutInflater();
      row = inflater.inflate(layoutToBeInflated, null);
      holder = new ComputerViewHolder();
      holder.tvComputerName = row.findViewById(R.id.tv_computer_name);
      holder.ivComputer = row.findViewById(R.id.imv_computer);
      row.setTag(holder);
    } else {
      // row was already created- no need to inflate and invoke findViewById
      // getTag() returns the object originally stored in this view
      holder = (ComputerViewHolder) row.getTag();
    }

    Computer computer = computers.get(position);
    holder.tvComputerName.setText(computer.getName());
    if (computer.isSelected()) {
      holder.ivComputer.setImageResource(R.drawable.monitor_icon_focus);
    } else {
      holder.ivComputer.setImageResource(R.drawable.monitor_icon);
    }

    // row listener (user clicks on any other part of the row)
    row.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Computer computer = computers.get(position);
        computer.setSelected(!computer.isSelected());
        Toast.makeText(context,
            "COMPUTER CLICKED - " + computer.getName(), Toast.LENGTH_SHORT).show();

        notifyDataSetChanged();
      }
    });

    return row;
  }

  private class ComputerViewHolder {

    TextView tvComputerName;
    ImageView ivComputer;
  }
}
