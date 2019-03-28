package com.mgoes.supermercado.listadesupermercado

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Implementacao do adapter
        //val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        val produtosAdapter = ProdutoAdapter(this)

        // Definindo o adaptador na lista
        list_view_produtos.adapter = produtosAdapter


        // Clique no Botao Adicionar
        btn_adicionar.setOnClickListener {

            // Criando Intent explicita e iniciando-a
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }


        // Clique Longo no item da Lista
        list_view_produtos.setOnItemLongClickListener { parent, view, position, id ->

            // Buscando o item clicado
            val item = produtosAdapter.getItem(position)

            // Removendo o item clicado
            produtosGlobal.remove(item)
//            produtosAdapter.remove(item)

            // Atualiza soma
            atualizaSoma()

            // Retorno indicando click com sucesso
            true
        }
    }

    override fun onResume() {
        super.onResume()
        atualizaSoma()
    }

    fun atualizaSoma(){
        val produtosAdapter = list_view_produtos.adapter as ProdutoAdapter
        produtosAdapter.clear()
        produtosAdapter.addAll(produtosGlobal)

        val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        val soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }
        txt_total.text = "TOTAL: ${formatter.format(soma)}"
    }


}
