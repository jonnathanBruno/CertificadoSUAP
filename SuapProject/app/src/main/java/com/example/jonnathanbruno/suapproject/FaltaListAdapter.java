package com.example.jonnathanbruno.suapproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FaltaListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Disciplina> mDisciplinaList;

    //Constructor
    public FaltaListAdapter (Context mContext, List<Disciplina> mDisciplinaList) {
        this.mContext = mContext;
        this.mDisciplinaList = mDisciplinaList;
    }

    @Override
    public int getCount() {
        return mDisciplinaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDisciplinaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_falta, null);
        TextView tvDisciplina = (TextView)v.findViewById(R.id.disciplina);
        TextView tvFaltas = (TextView)v.findViewById(R.id.numero_faltas);
        TextView tvPerFaltas = (TextView)v.findViewById(R.id.percentualF);

        tvDisciplina.setText(mDisciplinaList.get(position).getDisciplina());
        tvFaltas.setText("Faltas: " + String.valueOf(mDisciplinaList.get(position).getFaltas()));
        tvPerFaltas.setText("Percentual Frequência: " + String.valueOf(mDisciplinaList.get(position).getPerFaltas() + "%"));
        //Save product id to tag
        v.setTag(mDisciplinaList.get(position).getCodigo_diario());
        return v;
    }
}
