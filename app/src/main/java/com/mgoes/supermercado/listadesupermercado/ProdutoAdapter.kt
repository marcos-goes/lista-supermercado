package com.mgoes.supermercado.listadesupermercado

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

class ProdutoAdapter (context: Context) : ArrayAdapter<Produto>(context, 0){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{

        val view: View

        if(convertView != null)
            view = convertView
        else
            view = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)

        val item = getItem(position)

        val txt_produto = view.findViewById<TextView>(R.id.txt_item_produto)
        val txt_qtd = view.findViewById<TextView>(R.id.txt_item_qtd)
        val txt_valor = view.findViewById<TextView>(R.id.txt_item_valor)
        val img_produto = view.findViewById<ImageView>(R.id.img_item_foto)

        txt_produto.text = item.nome
        txt_qtd.text = item.quantidade.toString()

        val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txt_valor.text = formatter.format(item.valor)

        if(item.foto != null)
            img_produto.setImageBitmap(item.foto)

        return view
    }
}