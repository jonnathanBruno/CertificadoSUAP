package com.example.jonnathanbruno.suapproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AlunoListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Aluno> mAlunoList;

    //Constructor
    public AlunoListAdapter(Context mContext, List<Aluno> mAlunoList) {
        this.mContext = mContext;
        this.mAlunoList = mAlunoList;
    }

    @Override
    public int getCount() {
        return mAlunoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAlunoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_aluno, null);
        TextView tvMatricula = (TextView)v.findViewById(R.id.matricula);
        TextView tvNome = (TextView)v.findViewById(R.id.nome);
        TextView tvEmail = (TextView)v.findViewById(R.id.email);
        ImageView tvFoto = (ImageView)v.findViewById(R.id.foto);

        tvMatricula.setText("Matrícula: " + mAlunoList.get(position).getMatricula());
        tvNome.setText("Nome: " + mAlunoList.get(position).getNome());
        tvEmail.setText("Email: " + mAlunoList.get(position).getEmail());
        Drawable drw =LoadImageFromWebOperations("https://suap.ifrn.edu.br" + mAlunoList.get(position).getFoto());
        tvFoto.setImageDrawable(drw);
        //Save product id to tag
        v.setTag(mAlunoList.get(position).getMatricula());
        return v;
    }

    private Drawable LoadImageFromWebOperations(String strPhotoUrl) {
        try {
            InputStream is = (InputStream) new URL(strPhotoUrl).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }
    }

}
