package com.example.jonnathanbruno.suapproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class DisciplinaListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Disciplina> mDisciplinaList;

    //Constructor
    public DisciplinaListAdapter(Context mContext, List<Disciplina> mDisciplinaList) {
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
        View v = View.inflate(mContext, R.layout.item_nota, null);
        TextView tvDisciplina = (TextView)v.findViewById(R.id.disciplina);
        TextView tvNota1 = (TextView)v.findViewById(R.id.nota1);
        TextView tvNota2 = (TextView)v.findViewById(R.id.nota2);
        TextView tvSituacao = (TextView)v.findViewById(R.id.situacao);

        tvDisciplina.setText(mDisciplinaList.get(position).getDisciplina());
        tvNota1.setText("Nota1: " + String.valueOf(mDisciplinaList.get(position).getNota1()));
        tvNota2.setText("Nota2: " + String.valueOf(mDisciplinaList.get(position).getNota2()));
        tvSituacao.setText("Situação: "+ mDisciplinaList.get(position).getSituacao());
        //Save product id to tag
        v.setTag(mDisciplinaList.get(position).getCodigo_diario());
        return v;
    }
}
